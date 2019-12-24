/**
 * @author djz4712
 */
module tennis.console.app {
    exports simon.chareyron.tennis.console;
    requires tennis.controller;
    requires tennis.usecase;
    requires tennis.presenter;
    requires tennis.mapper.mapstruct;

    requires reactor.core;
    requires org.reactivestreams;
}
