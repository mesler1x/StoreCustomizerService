package ru.urfu.storagecustomizerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ThemeDto {
    private UUID id;
    private String title;
    private String themeMetadata;
    private Boolean isActive;
}
