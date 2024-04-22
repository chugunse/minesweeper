package studioTG.minesweeper;

import studioTG.minesweeper.exception.BadRequestException;
import studioTG.minesweeper.dto.GameInfoResponse;
import studioTG.minesweeper.dto.GameTurnRequest;
import studioTG.minesweeper.dto.NewGameRequest;

import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Service
public class Service {
    private final Map<String, Game> games = new HashMap<>();

    public GameInfoResponse newGame(NewGameRequest request) {
        if (request.mines_count() > request.height() * request.width() - 1) {
            throw new BadRequestException("Too many mines");
        }
        Game game = new Game(request.width(), request.height(), request.mines_count());
        games.put(game.getId(), game);
        return GameInfoResponse.builder()
                .game_id(game.getId())
                .width(game.getRows())
                .height(game.getCols())
                .mine_count(game.getMines())
                .field(game.getField())
                .completed(game.isCompleted())
                .build();
    }

    public GameInfoResponse turn(GameTurnRequest request) {
        if (!games.containsKey(request.game_id())) {
            throw new BadRequestException("Game not found");
        }
        Game game = games.get(request.game_id());
        if (game.isCompleted()) {
            throw new BadRequestException("Game is already completed");
        }
        if (!game.getField()[request.row()][request.col()].equals(" ")) {
            throw new BadRequestException("Cell is already opened");
        }
        if (request.col() > game.getCols() - 1 || request.row() > game.getRows() - 1) {
            throw new BadRequestException("Cell is out of bounds");
        }
        return GameInfoResponse.builder()
                .game_id(game.getId())
                .width(game.getRows())
                .height(game.getCols())
                .mine_count(game.getMines())
                .field(game.turn(request.row(), request.col()))
                .completed(game.isCompleted())
                .build();
    }
}
