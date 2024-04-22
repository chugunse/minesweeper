package studioTG.minesweeper.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record GameTurnRequest(@NotBlank String game_id,
                              @NotNull @Range(min = 0, max = 29, message = "Width must be between 0 and 29") int col,
                              @NotNull @Range(min = 0, max = 29, message = "Height must be between 0 and 29") int row) {
}
