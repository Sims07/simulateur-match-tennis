package simon.chareyron.tennis.presenter;

import reactor.core.publisher.FluxSink;
import simon.chareyron.tennis.usecase.model.TennisScoreModel;

public class ReactiveSimulateMatchPresenterImpl implements ReactiveSimulateMatchPresenter {

    private FluxSink<TennisScoreModel> tennisScoreFlux;

    @Override
    public void onScoreChanged(int winningPlayer, TennisScoreModel tennisScoreModel) {
        tennisScoreFlux.next(tennisScoreModel);
    }

    @Override
    public void onPlayerWinTheMatch(int winningPlayer, TennisScoreModel tennisScoreModel) {
        tennisScoreFlux.next(tennisScoreModel);
        tennisScoreFlux.complete();
    }

    @Override
    public void setCurrentFluxSink(FluxSink<TennisScoreModel> fluxSink) {
        this.tennisScoreFlux = fluxSink;
    }
}
