package com.ll.surl.domain.surl.surl.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/surls")
public class ApiV1SurlController {
    public record SurlCreateRequestBody(
            @NotBlank String title,
            @NotBlank String body,
            @NotBlank String url
    ) {
    }

    public record SurlCreateResponseBody(
    ) {
    }

    @PostMapping("")
    public SurlCreateResponseBody create(
            @Valid @RequestBody SurlCreateRequestBody requestBody
    ) {
        return new SurlCreateResponseBody();
    }
}
