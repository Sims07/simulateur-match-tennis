package simon.chareyron.tennis.console;

import simon.chareyron.tennis.usecase.SimulateTennisMatchOutput;
import simon.chareyron.tennis.usecase.SimulateTennisMatchUseCaseImpl;
import simon.chareyron.tennis.usecase.model.TennisScoreModel;

/**
 * @author djz4712
 */
public class TennisMainConsole {

    public static void main(String[] args) {
        new SimulateTennisMatchUseCaseImpl(new SimulateTennisMatchOutput() {
            @Override
            public void onScoreChanged(int i, TennisScoreModel tennisScoreModel) {
                System.out.println("Player:" + i + " win the point");
            }

            @Override
            public void onPlayerWinTheMatch(int i, TennisScoreModel tennisScoreModel) {
                System.out.println("Player:" + i + " win the match");
            }
        }).playRandomMatch(2);
    }
}
