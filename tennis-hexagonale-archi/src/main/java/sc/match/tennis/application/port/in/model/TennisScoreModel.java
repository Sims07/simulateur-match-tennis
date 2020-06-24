package sc.match.tennis.application.port.in.model;

import java.util.Map;

public class TennisScoreModel {

    private Map<Integer, SetScoreModel> setScores;
    private int currentSet;
    private GameScoreModel gameScore;

    public int getCurrentSet() {
        return currentSet;
    }

    public void setCurrentSet(int currentSet) {
        this.currentSet = currentSet;
    }

    public GameScoreModel getGameScore() {
        return gameScore;
    }

    public void setGameScore(GameScoreModel gameScore) {
        this.gameScore = gameScore;
    }

    public Map<Integer, SetScoreModel> getSetScores() {
        return setScores;
    }

    public void setSetScores(Map<Integer, SetScoreModel> setScores) {
        this.setScores = setScores;
    }

    @Override
    public String toString() {
        return "TennisScoreModel{" +
                "setScores=" + setScores +
                ", currentSet=" + currentSet +
                ", gameScore=" + gameScore +
                '}';
    }

}
