package com.ll.surl.domain.surl.surl.controller;

import com.ll.surl.domain.permission.permission.service.PermissionService;
import com.ll.surl.domain.surl.surl.dto.SurlDto;
import com.ll.surl.domain.surl.surl.entity.Surl;
import com.ll.surl.domain.surl.surl.service.SurlService;
import com.ll.surl.domain.surl.surlDocument.document.SurlDocument;
import com.ll.surl.domain.surl.surlDocument.service.SurlDocumentService;
import com.ll.surl.global.app.AppConfig;
import com.ll.surl.global.exceptions.GlobalException;
import com.ll.surl.global.rq.Rq;
import com.ll.surl.global.rsData.RsData;
import com.ll.surl.standard.dto.KwTypeV3;
import com.ll.surl.standard.dto.PageDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/surls")
@RequiredArgsConstructor
public class ApiV1SurlController {
    private final SurlService surlService;
    private final SurlDocumentService surlDocumentService;
    private final PermissionService permissionService;
    private final Rq rq;

    public record GetSurlsResponseBody(@NonNull PageDto<SurlDto> itemPage) {
    }

    @GetMapping("")
    public RsData<GetSurlsResponseBody> getSurls(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "") String kw,
            @RequestParam(defaultValue = "ALL") KwTypeV3 kwType
    ) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("rating"));
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize(), Sort.by(sorts));
        Page<SurlDocument> itemPage = surlDocumentService.findByKw(kwType, kw, pageable);

        Page<SurlDto> _itemPage = itemPage.map(
                surlDocument -> new SurlDto(surlDocument)
        );

        return RsData.of(
                new GetSurlsResponseBody(
                        new PageDto<>(_itemPage)
                )
        );
    }


    public record GetSurlResponseBody(@NonNull SurlDto item) {
    }

    @GetMapping("/{id}")
    public RsData<GetSurlResponseBody> getSurl(
            @PathVariable long id
    ) {
        SurlDocument surlDocument = surlDocumentService.findById(id).orElseThrow(GlobalException.E404::new);
        SurlDto dto = new SurlDto(surlDocument);

        return RsData.of(
                new GetSurlResponseBody(dto)
        );
    }

    public record SurlCreateReqBody(@NotBlank String url, String title) {
    }

    public record SurlCreateRespBody(String shortUrl) {
    }


    public record CreateSurlRequestBody(
            @NotBlank String title,
            @NotBlank String body,
            @NotBlank String url
    ) {
    }

    public record CreateSurlResponseBody(
            String shortUrl
    ) {
    }

    @PostMapping("")
    public RsData<CreateSurlResponseBody> create(
            @Valid @RequestBody ApiV1SurlController.CreateSurlRequestBody requestBody
    ) {
        Surl surl = surlService.create(rq.getMember(), requestBody.url, requestBody.title, requestBody.body);

        return RsData.of(new CreateSurlResponseBody(surl.getShortUrl()));
    }


    public record ModifySurlRequestBody(
            @NotBlank String title,
            @NotBlank String body
    ) {
    }

    public record ModifySurlResponseBody(
            String shortUrl
    ) {
    }

    @PutMapping("/{id}")
    @Transactional
    public RsData<ModifySurlResponseBody> modify(
            @PathVariable long id,
            @Valid @RequestBody ApiV1SurlController.ModifySurlRequestBody requestBody
    ) {
        Surl surl = surlService.findById(id).orElseThrow(GlobalException.E404::new);

        permissionService.check(rq.getMember(), surl, PermissionService.Action.MODIFY);

        surlService.modify(surl, requestBody.title, requestBody.body);

        return RsData.of(new ModifySurlResponseBody(surl.getShortUrl()));
    }


    public record DeleteSurlResponseBody(
            String shortUrl
    ) {
    }

    @DeleteMapping("/{id}")
    @Transactional
    public RsData<DeleteSurlResponseBody> delete(
            @PathVariable long id
    ) {
        Surl surl = surlService.findById(id).orElseThrow(GlobalException.E404::new);

        permissionService.check(rq.getMember(), surl, PermissionService.Action.DELETE);

        surlService.delete(surl);

        return RsData.of(new DeleteSurlResponseBody(surl.getShortUrl()));
    }
}
