package studioTG.minesweeper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import studioTG.minesweeper.dto.GameInfoResponse;
import studioTG.minesweeper.dto.GameTurnRequest;
import studioTG.minesweeper.dto.NewGameRequest;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class Controller {
    private final Service minesweeperService;

    @PostMapping("/new")
    public GameInfoResponse newGame(@RequestBody @Valid NewGameRequest request) {
        log.info("newGame: {}", request);
        return minesweeperService.newGame(request);
    }

    @PostMapping("/turn")
    public GameInfoResponse turn(@RequestBody @Valid GameTurnRequest request) {
        log.info("turn: {}", request);
        return minesweeperService.turn(request);
    }
}