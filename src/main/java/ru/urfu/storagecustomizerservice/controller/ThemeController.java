package ru.urfu.storagecustomizerservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.urfu.storagecustomizerservice.dto.ThemeDto;
import ru.urfu.storagecustomizerservice.service.ThemeService;

import java.util.List;
import java.util.UUID;

@Tag(name = "Сервис цветовых тем магазина")
@CrossOrigin(originPatterns = {"http://localhost:3000", "*"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/theme")
@Slf4j
public class ThemeController {

    private final ThemeService themeService;

    @Operation(summary = "Получить активированную тему")
    @GetMapping("/active")
    public ThemeDto getActiveTheme() {
        log.info("Получен запрос на активацию темы");
        return themeService.getActiveTheme();
    }

    @Operation(summary = "Получить список всех тем")
    @GetMapping
    public List<ThemeDto> getAllThemes() {
        log.info("Получен запрос на получение всех тем");
        return themeService.getAllThemes();
    }

    @Operation(summary = "Применить выбор темы")
    @PutMapping("/{themeId}")
    public void chooseTheme(@PathVariable UUID themeId) {
        log.info("Получен запрос на установку темы");
        themeService.chooseById(themeId);
    }
}
