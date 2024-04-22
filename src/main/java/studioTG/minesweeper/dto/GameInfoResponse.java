package studioTG.minesweeper.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameInfoResponse {
    private String game_id;
    private int width;
    private int height;
    private int mine_count;
    private String[][] field;
    private boolean completed;
}
