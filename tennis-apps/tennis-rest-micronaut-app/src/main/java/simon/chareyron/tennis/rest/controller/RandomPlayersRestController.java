package simon.chareyron.tennis.rest.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import simon.chareyron.tennis.controller.SelectRandomPlayersController;
import simon.chareyron.tennis.usecase.model.actors.PlayerModel;

import java.util.List;

@Controller("/randomPlayers")
public class RandomPlayersRestController {

    private final SelectRandomPlayersController selectRandomPlayersController;

    public RandomPlayersRestController(SelectRandomPlayersController selectRandomPlayersController) {
        this.selectRandomPlayersController = selectRandomPlayersController;
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    public List<PlayerModel> getRandomPlayers(@QueryValue("nbPlayers") int nbPlayers) {
        return selectRandomPlayersController.selectRandomPlayers(nbPlayers);
    }

}
