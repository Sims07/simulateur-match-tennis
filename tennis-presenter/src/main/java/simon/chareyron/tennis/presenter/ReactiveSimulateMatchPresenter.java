package simon.chareyron.tennis.presenter;

import reactor.core.publisher.FluxSink;
import simon.chareyron.tennis.usecase.SimulateTennisMatchOutput;
import simon.chareyron.tennis.usecase.model.TennisScoreModel;

public interface ReactiveSimulateMatchPresenter extends SimulateTennisMatchOutput {

    void setCurrentFluxSink(FluxSink<TennisScoreModel> fluxSink);
}
