/**
 * @author djz4712
 */
module tennis.usecase {
    exports simon.chareyron.tennis.usecase;
    exports simon.chareyron.tennis.usecase.model;
    exports simon.chareyron.tennis.usecase.mapper;

    requires org.mapstruct;
    requires java.sql;
    requires tennis.domain;
}
