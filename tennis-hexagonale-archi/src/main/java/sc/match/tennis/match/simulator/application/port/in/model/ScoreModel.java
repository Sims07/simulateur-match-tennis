package sc.match.tennis.match.simulator.application.port.in.model;

import java.util.HashMap;
import java.util.Map;

public abstract class ScoreModel<T> {

    private Map<Player, T> score = new HashMap();

    public Map<Player, T> getScore() {
        return score;
    }

    public void setScore(Map<Player, T> score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ScoreModel{" +
                "score=" + score +
                '}';
    }
}
