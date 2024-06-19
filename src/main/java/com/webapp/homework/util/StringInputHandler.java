package com.webapp.homework.util;

import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Component
public class StringInputHandler {
    public String processPhraseInput(String input) {
        return input.replaceAll("_", " ");
    }

    public boolean checkInputExistence(String input) {
        return input != null && !input.isEmpty();
    }

    public String getColumnHeader(String input) {
        return input.split(":")[0].trim().toLowerCase();
    }

    public String getSortPattern(String input) {
        return input.split(":")[1].trim().toLowerCase();
    }

    public String decodeUrlParams(String urlParams) {
        if (urlParams != null && !urlParams.isEmpty()) {
            return URLDecoder.decode(urlParams, StandardCharsets.UTF_8);
        }
        return urlParams;
    }
}
