package simon.chareyron.tennis.presenter;

import reactor.core.publisher.Flux;
import simon.chareyron.tennis.usecase.SimulateTennisMatchOutput;
import simon.chareyron.tennis.usecase.model.score.TennisScoreModel;

import java.util.List;

public class ReactiveSimulateMatchPresenterImpl implements SimulateTennisMatchOutput<Flux<TennisScoreModel>> {

    @Override
    public Flux<TennisScoreModel> presentMatchScores(List<TennisScoreModel> tennisScoreModel) {
        return Flux.fromIterable(tennisScoreModel);
    }
}
