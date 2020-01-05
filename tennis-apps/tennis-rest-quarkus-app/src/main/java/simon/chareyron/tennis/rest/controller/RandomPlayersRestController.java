package simon.chareyron.tennis.rest.controller;

import simon.chareyron.tennis.controller.SelectRandomPlayersController;
import simon.chareyron.tennis.usecase.model.actors.PlayerModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/randomPlayers")
public class RandomPlayersRestController {

    private final SelectRandomPlayersController selectRandomPlayersController;

    public RandomPlayersRestController(SelectRandomPlayersController selectRandomPlayersController) {
        this.selectRandomPlayersController = selectRandomPlayersController;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlayerModel> getRandomPlayers(@QueryParam("nbPlayers") int nbPlayers) {
        return selectRandomPlayersController.selectRandomPlayers(nbPlayers);
    }

}
