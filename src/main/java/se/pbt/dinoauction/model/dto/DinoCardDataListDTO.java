package se.pbt.dinoauction.model.dto;

import java.util.List;

/**
 * Holds a list of {@link DinoCardDataDTO} objects for batch display or processing.
 */
public record DinoCardDataListDTO (
        List<DinoCardDataDTO> dinoCardDataList
) {
}
