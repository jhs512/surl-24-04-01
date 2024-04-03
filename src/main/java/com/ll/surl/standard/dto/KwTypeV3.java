package com.ll.surl.standard.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum KwTypeV3 {
    ALL("all"),
    TITLE("title"),
    BODY("body"),
    URL("url");

    private final String value;
}