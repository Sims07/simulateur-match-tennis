package simon.chareyron.tennis.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import simon.chareyron.tennis.controller.SelectRandomPlayersController;
import simon.chareyron.tennis.usecase.model.actors.PlayerModel;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/randomPlayers")
public class RandomPlayersRestController {

    private final SelectRandomPlayersController selectRandomPlayersController;

    public RandomPlayersRestController(SelectRandomPlayersController selectRandomPlayersController) {
        this.selectRandomPlayersController = selectRandomPlayersController;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PlayerModel> getRandomPlayers(@RequestParam(name = "nbPlayers") int nbPlayers) {
        return selectRandomPlayersController.selectRandomPlayers(nbPlayers);
    }

}
