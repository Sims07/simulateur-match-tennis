package simon.chareyron.coding.tennisrules.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class Score<T> {

    private T[] orderedScores = initOrderedScores();

    protected abstract T[] initOrderedScores();

    private Map<Player, T> score = new HashMap();

    public Score() {
        reset();
    }

    public boolean nextScoreForPlayer(Player winnerPointPlayer) {
        boolean winnerPointPlayerWon = false;
        T currentScore = getScorePlayer(winnerPointPlayer);
        int nextScoreIndex = Arrays.asList(orderedScores).indexOf(currentScore);
        if (isNextScoreAvailable(nextScoreIndex)) {
            setScorePlayer(winnerPointPlayer, orderedScores[nextScoreIndex + 1]);
            winnerPointPlayerWon = isPlayerWon(winnerPointPlayer);
        } else {
            winnerPointPlayerWon = true;
        }
        return winnerPointPlayerWon;
    }

    private boolean isNextScoreAvailable(int nextScoreIndex) {
        return orderedScores.length > nextScoreIndex + 1;
    }


    public void previousScoreForPlayer(Player player) {
        T currentScore = getScorePlayer(player);
        int nextScoreIndex = Arrays.asList(orderedScores).indexOf(currentScore);
        setScorePlayer(player, orderedScores[nextScoreIndex - 1]);
    }

    public void reset() {
        setScorePlayer(Player._1, orderedScores[0]);
        setScorePlayer(Player._2, orderedScores[0]);
    }

    protected T getScoreLooserPlayer(Player winnerPointPlayer) {
        return getScorePlayer(getLooserPointPlayer(winnerPointPlayer));
    }

    protected Player getLooserPointPlayer(Player winnerPointPlayer) {
        return Player._1 == winnerPointPlayer ? Player._2 : Player._1;
    }

    protected abstract boolean isPlayerWon(Player winnerPointPlayer);

    public T getScorePlayer1() {
        return score.get(Player._1);
    }

    public void setScorePlayer(Player player, T scorePlayer) {
        score.put(player, scorePlayer);
    }

    public T getScorePlayer(Player player) {
        return score.get(player);
    }

    public T getScorePlayer2() {
        return score.get(Player._2);
    }


}
