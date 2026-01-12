package ru.urfu.storagecustomizerservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.urfu.storagecustomizerservice.dto.ThemeDto;
import ru.urfu.storagecustomizerservice.repository.ThemeRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository themeRepository;

    public ThemeDto getActiveTheme() {
        return themeRepository.getActiveTheme();
    }

    public List<ThemeDto> getAllThemes() {
        return themeRepository.findAllThemes();
    }

    public void chooseById(UUID themeId) {
        themeRepository.chooseThemeById(themeId);
    }
}
