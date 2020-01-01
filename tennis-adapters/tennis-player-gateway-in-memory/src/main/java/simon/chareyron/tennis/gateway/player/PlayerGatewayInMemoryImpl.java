package simon.chareyron.tennis.gateway.player;

import simon.chareyron.tennis.usecase.gateway.PlayerGateway;
import simon.chareyron.tennis.usecase.model.actors.PlayerModel;

import java.util.Arrays;
import java.util.List;

public class PlayerGatewayInMemoryImpl implements PlayerGateway {
    @Override
    public List<PlayerModel> loadRandomPlayers(int nbPlayersToLoad) {
        return Arrays.asList(new PlayerModel()
                        .withName("Rafael")
                        .withSurname("Nadal")
                        .withNationality("SPA")
                        .withRank(1),
                new PlayerModel()
                        .withName("Roger")
                        .withSurname("Federer")
                        .withNationality("SWS")
                        .withRank(2)
        ).subList(0, nbPlayersToLoad);
    }
}
