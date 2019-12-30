module tennis.controller {
    requires tennis.usecase;
    exports simon.chareyron.tennis.controller;
    exports simon.chareyron.tennis.controller.impl to tennis.console.app;
}