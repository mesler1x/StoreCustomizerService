package ru.urfu.storagecustomizerservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.urfu.storagecustomizerservice.dto.ThemeDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ThemeRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ThemeDto getActiveTheme() {
        return namedParameterJdbcTemplate.query(
                """
                        SELECT 
                            id,
                            title,
                            metadata,
                            is_active 
                        FROM theme
                        WHERE is_active = TRUE 
                    """,
                rs -> {
                    if (rs.next()) {
                        return new ThemeDto(
                                rs.getObject("id", UUID.class),
                                rs.getString("title"),
                                rs.getString("metadata"),
                                rs.getBoolean("is_active")
                        );
                    }

                    return null;
                }
        );
    }

    public List<ThemeDto> findAllThemes() {
        return namedParameterJdbcTemplate.query(
                """
                        SELECT 
                            id,
                            title,
                            metadata,
                            is_active 
                        FROM theme
                    """,
                rs -> {
                    var result = new ArrayList<ThemeDto>();
                    while (rs.next()) {
                        result.add(new ThemeDto(
                                rs.getObject("id", UUID.class),
                                rs.getString("title"),
                                rs.getString("metadata"),
                                rs.getBoolean("is_active")
                        ));
                    }

                    return result;
                }
        );
    }

    @Transactional
    public void chooseThemeById(UUID themeId) {
        namedParameterJdbcTemplate.update(
                """
                        UPDATE theme 
                        SET is_active = FALSE 
                        WHERE id <> :themeId
                    """,
                new MapSqlParameterSource("themeId", themeId)
        );
        namedParameterJdbcTemplate.update(
                """
                        UPDATE theme 
                        SET is_active = TRUE 
                        WHERE id = :themeId
                    """,
                new MapSqlParameterSource("themeId", themeId)
        );
    }
}
