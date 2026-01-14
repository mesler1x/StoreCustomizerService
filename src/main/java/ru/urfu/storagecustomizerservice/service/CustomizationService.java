package ru.urfu.storagecustomizerservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.urfu.storagecustomizerservice.dto.BaseCustomizationDto;
import ru.urfu.storagecustomizerservice.dto.CustomizationType;
import ru.urfu.storagecustomizerservice.repository.CustomizationRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomizationService {

    private final CustomizationRepository customizationRepository;

    public BaseCustomizationDto getActiveCustomization(CustomizationType customizationType) {
        return customizationRepository.getActiveCustomization(customizationType);
    }

    public List<BaseCustomizationDto> getAllCustomizations(CustomizationType customizationType) {
        return customizationRepository.findAllCustomizations(customizationType);
    }

    public void chooseById(UUID themeId, CustomizationType customizationType) {
        customizationRepository.chooseCustomizationById(themeId, customizationType);
    }

    public void changeSiteName(String siteName) {
        customizationRepository.changeSiteName(siteName);
    }

    public BaseCustomizationDto getSiteName() {
        return customizationRepository.getSiteName();
    }
}
