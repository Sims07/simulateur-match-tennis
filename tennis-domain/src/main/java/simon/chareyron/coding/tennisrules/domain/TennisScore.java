package simon.chareyron.coding.tennisrules.domain;

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
    }

    public void addNewSet() {
        this.currentSet++;
        setScores.put(currentSet, new SetScore());
    }

    public void setSetScorePlayer(Player player, int setScorePlayer, int setNumber) {
        SetScore setScoreToUpdate = getOrCreateSetScore(setNumber);
        setScoreToUpdate.setScorePlayer(player, setScorePlayer);
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

}
