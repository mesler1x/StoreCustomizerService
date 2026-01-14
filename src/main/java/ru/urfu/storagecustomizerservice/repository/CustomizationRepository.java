package ru.urfu.storagecustomizerservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.urfu.storagecustomizerservice.dto.BaseCustomizationDto;
import ru.urfu.storagecustomizerservice.dto.CustomizationType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustomizationRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public BaseCustomizationDto getActiveCustomization(CustomizationType customizationType) {
        return namedParameterJdbcTemplate.query(
                """
                        SELECT 
                            id,
                            title,
                            customization_type,
                            is_active 
                        FROM customization
                        WHERE is_active = TRUE 
                        AND customization_type = :customizationType
                    """,
                new MapSqlParameterSource()
                        .addValue("customizationType", customizationType.name()),
                rs -> {
                    if (rs.next()) {
                        return new BaseCustomizationDto(
                                rs.getObject("id", UUID.class),
                                rs.getString("title"),
                                rs.getString("customization_type"),
                                rs.getBoolean("is_active")
                        );
                    }

                    return null;
                }
        );
    }

    public List<BaseCustomizationDto> findAllCustomizations(CustomizationType customizationType) {
        return namedParameterJdbcTemplate.query(
                """
                        SELECT 
                            id,
                            title,
                            customization_type,
                            is_active 
                        FROM customization
                        WHERE customization_type = :customizationType
                    """,
                new MapSqlParameterSource()
                        .addValue("customizationType", customizationType.name()),
                rs -> {
                    var result = new ArrayList<BaseCustomizationDto>();
                    while (rs.next()) {
                        result.add(new BaseCustomizationDto(
                                rs.getObject("id", UUID.class),
                                rs.getString("title"),
                                rs.getString("customization_type"),
                                rs.getBoolean("is_active")
                        ));
                    }

                    return result;
                }
        );
    }

    @Transactional
    public void chooseCustomizationById(UUID customizationId, CustomizationType customizationType) {
        namedParameterJdbcTemplate.update(
                """
                        UPDATE customization 
                        SET is_active = FALSE 
                        WHERE id <> :customizationId
                        AND customization_type = :customizationType
                    """,
                new MapSqlParameterSource()
                        .addValue("customizationId", customizationId)
                        .addValue("customizationType", customizationType.name())
        );
        namedParameterJdbcTemplate.update(
                """
                        UPDATE customization
                        SET is_active = TRUE 
                        WHERE id = :customizationId
                        AND customization_type = :customizationType
                    """,
                new MapSqlParameterSource()
                        .addValue("customizationId", customizationId)
                        .addValue("customizationType", customizationType.name())
        );
    }
}
