package sc.match.tennis.match.simulator.application;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import sc.match.tennis.match.simulator.application.port.in.GetWinnerMatchPlayersQuery;
import sc.match.tennis.match.simulator.application.port.out.LoadWinnerMatchPlayersPort;

public class GetWinnerMatchPlayersService implements GetWinnerMatchPlayersQuery {

    private final LoadWinnerMatchPlayersPort loadWinnerMatchPlayersPort;

    public GetWinnerMatchPlayersService(LoadWinnerMatchPlayersPort loadWinnerMatchPlayersPort) {
        this.loadWinnerMatchPlayersPort = loadWinnerMatchPlayersPort;
    }

    @Override
    public List<String> getAllWinnerMatchPlayers() {
        return loadWinnerMatchPlayersPort.loadWinnerMatchPlayers().stream().map(Objects::toString).collect(Collectors.toList());
    }
}
