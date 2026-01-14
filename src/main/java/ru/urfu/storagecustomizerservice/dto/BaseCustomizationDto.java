package ru.urfu.storagecustomizerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseCustomizationDto {
    private UUID id;
    private String title;
    private String customizationName;
    private Boolean isActive;
}
