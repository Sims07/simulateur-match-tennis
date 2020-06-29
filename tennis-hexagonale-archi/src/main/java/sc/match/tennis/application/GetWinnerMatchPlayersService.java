package sc.match.tennis.application;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import sc.match.tennis.application.port.in.GetWinnerMatchPlayersQuery;
import sc.match.tennis.application.port.out.LoadWinnerMatchPlayersPort;

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
