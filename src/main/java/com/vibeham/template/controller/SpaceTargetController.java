package com.vibeham.template.controller;

import com.vibeham.template.model.SpaceTarget;
import com.vibeham.template.repository.SpaceTargetRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/targets")
@Tag(name = "Space Targets", description = "Vesmírné cíle a jejich vzdálenost od Země")
public class SpaceTargetController {

    private final SpaceTargetRepository repository;

    public SpaceTargetController(SpaceTargetRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Vrátí seznam všech vesmírných cílů")
    public List<SpaceTarget> getAll() {
        return repository.findAll();
    }
}
