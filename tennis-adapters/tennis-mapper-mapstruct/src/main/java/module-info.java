module tennis.mapper.mapstruct {
    exports simon.chareyron.tennis.mapper.mapstruct;

    requires org.mapstruct;
    requires java.sql;
    requires tennis.usecase;
    requires tennis.domain;
}