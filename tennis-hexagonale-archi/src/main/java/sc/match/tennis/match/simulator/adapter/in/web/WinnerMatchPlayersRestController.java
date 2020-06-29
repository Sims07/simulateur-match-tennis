package sc.match.tennis.match.simulator.adapter.in.web;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sc.match.tennis.match.simulator.application.port.in.GetWinnerMatchPlayersQuery;


@RestController
@CrossOrigin
@RequestMapping("/winnerMatchPlayer")
public class WinnerMatchPlayersRestController {

    private final GetWinnerMatchPlayersQuery getWinnerMatchPlayersQuery;

    public WinnerMatchPlayersRestController(GetWinnerMatchPlayersQuery getWinnerMatchPlayersQuery) {
        this.getWinnerMatchPlayersQuery = getWinnerMatchPlayersQuery;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> tennisMatchSimulation() {
        return getWinnerMatchPlayersQuery.getAllWinnerMatchPlayers();
    }

}
