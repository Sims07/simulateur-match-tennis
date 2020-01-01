package simon.chareyron.coding.tennisrules.domain;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Referee {

    private final TennisScore tennisScore;
    private final int nbWinningSet;

    public Referee(TennisScore tennisScore, int nbWinningSet) {
        this.tennisScore = tennisScore;
        this.nbWinningSet = nbWinningSet;
    }

    public void winPoint(Player winnerPointPlayer) {
        Score<?> scoreToUpdate = selectGameOrTieBreakScoreToUpdate();
        boolean winTheGame = scoreToUpdate.nextScoreForPlayer(winnerPointPlayer);
        if (winTheGame) {
            applyWinningGameRule(winnerPointPlayer);
        }
    }

    public Player getWiningPlayer() {
        Player winningPlayer = null;
        Optional<Map.Entry<Player, Long>> playerWithMaxSetWonOpt = tennisScore.getSetsScore()
                                                                              .stream()
                                                                              .map(SetScore::getWinninPlayer)
                                                                              .filter(Objects::nonNull)
                                                                              .collect(Collectors.groupingBy(Function.identity(),
                                                                                                             Collectors.counting()))
                                                                              .entrySet()
                                                                              .stream()
                                                                              .max(Map.Entry.comparingByValue());
        if (playerWithMaxSetWonOpt.isPresent() && playerWithMaxSetWonOpt.map(Map.Entry::getValue).orElse(0L) == nbWinningSet) {
            winningPlayer = playerWithMaxSetWonOpt.get().getKey();
        }
        return winningPlayer;
    }

    private Score<?> selectGameOrTieBreakScoreToUpdate() {
        return tennisScore.isInTieBreak() ? tennisScore.getTieBreakScore() : tennisScore.getGameScore();
    }

    private void applyWinningGameRule(Player winnerPointPlayer) {
        boolean winTheSet = tennisScore.nextSetScoreForPlayer(winnerPointPlayer);
        if (winTheSet) {
            tennisScore.addNewSet();
        } else {
            if (tennisScore.isInTieBreak()) {
                tennisScore.beginTieBreak();
            }
        }
        tennisScore.resetGameScore();
    }
}
