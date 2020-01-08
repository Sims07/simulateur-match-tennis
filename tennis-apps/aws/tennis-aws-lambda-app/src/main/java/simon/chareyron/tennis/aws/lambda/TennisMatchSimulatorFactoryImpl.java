package simon.chareyron.tennis.aws.lambda;

import simon.chareyron.tennis.controller.TennisMatchSimulatorController;
import simon.chareyron.tennis.controller.impl.TennisMatchSimulatorControllerImpl;
import simon.chareyron.tennis.mapper.mapstruct.TennisScoreMapStrutMapperImpl;
import simon.chareyron.tennis.usecase.impl.SimulateTennisMatchUseCaseImpl;

public class TennisMatchSimulatorFactoryImpl implements TennisMatchSimulatorFactory {

    @Override
    public TennisMatchSimulatorController build() {
        return new TennisMatchSimulatorControllerImpl
                (new SimulateTennisMatchUseCaseImpl(
                        new TennisScoreMapStrutMapperImpl()
                ));
    }
}
