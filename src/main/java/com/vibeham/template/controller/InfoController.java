package com.vibeham.template.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/info")
@Tag(name = "Info")
public class InfoController {

    @GetMapping
    @Operation(summary = "Returns backend technology info")
    public Map<String, String> info() {
        return Map.of("technology", "Java 21 / Spring Boot / Maven / PostgreSQL");
    }
}
