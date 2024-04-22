package studioTG.minesweeper.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record NewGameRequest(@NotNull @Range(min = 1, max = 30, message = "Width must be between 1 and 30") int width,
                             @NotNull @Range(min = 1, max = 30, message = "Height must be between 1 and 30") int height,
                             @NotNull @Range(min = 1, message = "Mines count must be at least 1") int mines_count) {
}
