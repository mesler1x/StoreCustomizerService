package ru.urfu.storagecustomizerservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.urfu.storagecustomizerservice.dto.BaseCustomizationDto;
import ru.urfu.storagecustomizerservice.dto.CustomizationType;
import ru.urfu.storagecustomizerservice.service.CustomizationService;

import java.util.List;
import java.util.UUID;

@Tag(name = "Сервис кастомизации магазина")
@CrossOrigin(originPatterns = {"http://localhost:3000", "*"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/customization")
@Slf4j
public class CustomizationController {

    private final CustomizationService customizationService;

    @Operation(summary = "Получить активированную кастомизацию по типу кастомизации")
    @GetMapping("/active")
    public BaseCustomizationDto getActiveTheme(
            @RequestParam CustomizationType customizationType
    ) {
        log.info("Получен запрос на активацию кастомизации");
        return customizationService.getActiveCustomization(customizationType);
    }

    @Operation(summary = "Получить список всех кастомизаций по типу кастомизации")
    @GetMapping
    public List<BaseCustomizationDto> getAllCustomizations(
            @RequestParam CustomizationType customizationType
    ) {
        log.info("Получен запрос на получение всех кастомизаций");
        return customizationService.getAllCustomizations(customizationType);
    }

    @Operation(summary = "Применить выбор кастомизации учитывая тип кастомизации")
    @PutMapping("/{customizationId}")
    public void chooseCustomization(@PathVariable UUID customizationId,
                                    @RequestParam CustomizationType customizationType
    ) {
        log.info("Получен запрос на кастомизации");
        customizationService.chooseById(customizationId, customizationType);
    }

    @Operation(summary = "Изменить название сайта")
    @PutMapping("/site")
    public void changeSiteName(@RequestParam String siteName) {
        log.info("Получен запрос на изменение сайта");
        customizationService.changeSiteName(siteName);
    }

    @Operation(summary = "Получить название сайта")
    @GetMapping("/site")
    public BaseCustomizationDto getSiteName() {
        log.info("Получен запрос на получение сайта");
        return customizationService.getSiteName();
    }
}
