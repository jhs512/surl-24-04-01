package com.ll.surl.domain.surl.surlComment.controller;


import com.ll.surl.domain.permission.permission.service.PermissionService;
import com.ll.surl.domain.surl.surl.entity.Surl;
import com.ll.surl.domain.surl.surl.service.SurlService;
import com.ll.surl.domain.surl.surlComment.dto.SurlCommentDto;
import com.ll.surl.domain.surl.surlComment.entity.SurlComment;
import com.ll.surl.domain.surl.surlComment.service.SurlCommentService;
import com.ll.surl.global.exceptions.GlobalException;
import com.ll.surl.global.rq.Rq;
import com.ll.surl.global.rsData.RsData;
import com.ll.surl.standard.dto.Empty;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/surlComments", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@Tag(name = "ApiV1SurlCommentController", description = "댓글 CRUD 컨트롤러")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiV1SurlCommentController {
    private final Rq rq;
    private final SurlService surlService;
    private final SurlCommentService surlCommentService;
    private final PermissionService permissionService;

    @PersistenceContext
    private EntityManager entityManager;


    public record GetSurlCommentsResponseBody(
            @NonNull List<SurlCommentDto> items
    ) {
    }

    @GetMapping(value = "/{surlId}", consumes = ALL_VALUE)
    @Operation(summary = "댓글 다건조회")
    public RsData<GetSurlCommentsResponseBody> getSurlComments(
            @PathVariable long surlId
    ) {
        Surl surl = surlService.findById(surlId).orElseThrow(GlobalException.E404::new);

        List<SurlComment> items = surlCommentService.findBySurlAndPublishedOrderByIdDesc(
                surl,
                true
        );

        List<SurlCommentDto> _items = items.stream()
                .map(this::surlCommentToDto)
                .collect(Collectors.toList());

        return RsData.of(
                new GetSurlCommentsResponseBody(
                        _items
                )
        );
    }


    public record GetSurlSubCommentsResponseBody(
            @NonNull List<SurlCommentDto> items
    ) {
    }


    @DeleteMapping(value = "/{surlId}/{surlCommentId}", consumes = ALL_VALUE)
    @Operation(summary = "댓글 삭제")
    @Transactional
    public RsData<Empty> delete(
            @PathVariable long surlId,
            @PathVariable long surlCommentId
    ) {
        Surl surl = surlService.findById(surlId).orElseThrow(GlobalException.E404::new);

        SurlComment surlComment = surlCommentService.findById(surlCommentId)
                .orElseThrow(GlobalException.E404::new);

        permissionService.check(rq.getMember(), surlComment, PermissionService.Action.DELETE);

        surlCommentService.delete(surl, surlComment);

        return RsData.of(
                "댓글이 삭제되었습니다."
        );
    }


    public record EditCommentRequestBody(@NotBlank String body) {
    }

    public record EditCommentResponseBody(@NonNull SurlCommentDto item) {
    }

    @PutMapping("/{surlId}/{surlCommentId}")
    @Operation(summary = "댓글 수정")
    @Transactional
    public RsData<EditCommentResponseBody> edit(
            @PathVariable long surlId,
            @PathVariable long surlCommentId,
            @Valid @RequestBody EditCommentRequestBody body
    ) {
        Surl surl = surlService.findById(surlId).orElseThrow(GlobalException.E404::new);

        SurlComment surlComment = surlCommentService.findById(surlCommentId)
                .orElseThrow(GlobalException.E404::new);

        permissionService.check(rq.getMember(), surlComment, PermissionService.Action.MODIFY);

        surlCommentService.edit(surl, surlComment, body.body);
        entityManager.flush(); // modifyDate 가 이 컨트롤러 액션이 끝나고 변경되지 않고, 지금 즉시 변경되도록 커밋을 지금 수행하도록 한다.

        return RsData.of(
                "댓글이 수정되었습니다.",
                new EditCommentResponseBody(surlCommentToDto(surlComment))
        );
    }


    private SurlCommentDto surlCommentToDto(SurlComment surlComment) {
        SurlCommentDto dto = new SurlCommentDto(surlComment);
        dto.setActorCanDelete(permissionService.can(rq.getMember(), surlComment, PermissionService.Action.DELETE));
        dto.setActorCanEdit(permissionService.can(rq.getMember(), surlComment, PermissionService.Action.MODIFY));

        return dto;
    }
}

