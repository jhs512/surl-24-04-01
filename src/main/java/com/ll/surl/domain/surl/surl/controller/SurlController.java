package com.ll.surl.domain.surl.surl.controller;

import com.ll.surl.domain.surl.surlDocument.document.SurlDocument;
import com.ll.surl.domain.surl.surlDocument.service.SurlDocumentService;
import com.ll.surl.global.exceptions.GlobalException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/surl")
@RequiredArgsConstructor
@Tag(name = "SurlController", description = "실제 URL 이동")
public class SurlController {
    private final SurlDocumentService surlDocumentService;

    @GetMapping("/go/{id}")
    @ResponseBody
    @Operation(summary = "실제 주소로 이동")
    public RedirectView go(
            @PathVariable long id
    ) {
        SurlDocument surlDocument = surlDocumentService.findById(id).orElseThrow(GlobalException.E404::new);

        RedirectView redirectView = new RedirectView(surlDocument.getUrl());
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY); // 301 상태 코드 설정
        return redirectView;
    }
}