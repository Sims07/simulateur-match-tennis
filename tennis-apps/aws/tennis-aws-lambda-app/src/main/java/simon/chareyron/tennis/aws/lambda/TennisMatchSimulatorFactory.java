package simon.chareyron.tennis.aws.lambda;

import simon.chareyron.tennis.controller.TennisMatchSimulatorController;

public interface TennisMatchSimulatorFactory {

    TennisMatchSimulatorController build();
}
