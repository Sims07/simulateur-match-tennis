package sc.match.tennis.match.simulator.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TennisScore {
    private Map<Integer, SetScore> setScores;
    private int currentSet;
    private GameScore gameScore;

    public TennisScore() {
        this.currentSet = 1;
        this.gameScore = new GameScore();
        this.setScores = new HashMap<>();
        this.setSetScorePlayer(Player._1, 0, 1);
        this.setSetScorePlayer(Player._2, 0, 1);
        this.setGameScorePlayer(Player._1, "0");
        this.setGameScorePlayer(Player._2, "0");
    }

    public Map<Integer, SetScore> getSetScores() {
        return setScores;
    }

    public void setSetScores(Map<Integer, SetScore> setScores) {
        this.setScores = setScores;
    }

    public int getCurrentSet() {
        return currentSet;
    }

    public void setCurrentSet(int currentSet) {
        this.currentSet = currentSet;
    }

    public void setGameScore(GameScore gameScore) {
        this.gameScore = gameScore;
    }

    public void addNewSet() {
        this.currentSet++;
        setScores.put(currentSet, new SetScore());
    }

    public void setSetScorePlayer(Player player, int setScorePlayer, int setNumber) {
        SetScore setScoreToUpdate = getOrCreateSetScore(setNumber);
        setScoreToUpdate.setScorePlayer(player, setScorePlayer);
        this.currentSet = setNumber;
    }

    public boolean nextSetScoreForPlayer(Player winnerPointPlayer) {
        return getCurrentSetScore().nextScoreForPlayer(winnerPointPlayer);
    }

    public void resetGameScore() {
        gameScore.reset();
    }

    public TieBreakScore getTieBreakScore() {
        return getCurrentSetScore().getTieBreakScore();
    }

    public boolean isInTieBreak() {
        return getCurrentSetScore().isInTieBreak();
    }

    public void setTieBreakScorePlayer(Player player, int gameScorePlayer) {
        TieBreakScore tieBreakScoreToUpdate = Optional.ofNullable(getCurrentSetScore()).map(SetScore::getTieBreakScore).orElse(new TieBreakScore());
        tieBreakScoreToUpdate.setScorePlayer(player, gameScorePlayer);
        getCurrentSetScore().setTieBreakScore(tieBreakScoreToUpdate);
    }

    public void beginTieBreak() {
        getCurrentSetScore().setTieBreakScore(new TieBreakScore());
    }

    public void setGameScorePlayer(Player player, String gameScorePlayer) {
        getGameScore().setScorePlayer(player, gameScorePlayer);
    }

    public Collection<SetScore> getSetsScore() {
        return setScores.values();
    }

    private SetScore getOrCreateSetScore(int setNumber) {
        SetScore setScoreToUpdate = setScores.get(setNumber);
        if (setScoreToUpdate == null) {
            setScoreToUpdate = new SetScore();
            setScores.put(setNumber, setScoreToUpdate);
        }
        return setScoreToUpdate;
    }

    private SetScore getCurrentSetScore() {
        return setScores.get(currentSet);
    }

    public SetScore getSetScore(int setNumber) {
        return setScores.get(setNumber);
    }

    public GameScore getGameScore() {
        return this.gameScore;
    }

    @Override
    public String toString() {
        return "TennisScore{" + "setScores=" + setScores + ", currentSet=" + currentSet + ", gameScore=" + gameScore + '}';
    }
}
