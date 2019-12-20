package simon.chareyron.coding.tennisrules;

import static simon.chareyron.coding.tennisrules.BDDTennisAssertions.givenTennisRuleAndInitScore;

import java.util.Arrays;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TennisRulesTest {

    @Nested
    class GameRulesTest {

        @Test
        public void winAPointFrom0() {
            givenTennisRuleAndInitScore("0-0", "0-0")
                    .whenPlayerWinPoint("1")
                    .thenSetScoreIs("0-0")
                    .thenGameScoreIs("15-0");

            givenTennisRuleAndInitScore("0-0", "0-0")
                    .whenPlayerWinPoint("2")
                    .thenSetScoreIs("0-0")
                    .thenGameScoreIs("0-15");
        }

        @Test
        public void winAPointFrom15() {
            givenTennisRuleAndInitScore("0-0", "15-0")
                    .whenPlayerWinPoint("1")
                    .thenSetScoreIs("0-0")
                    .thenGameScoreIs("30-0");

            givenTennisRuleAndInitScore("0-0", "0-15")
                    .whenPlayerWinPoint("2")
                    .thenSetScoreIs("0-0")
                    .thenGameScoreIs("0-30");
        }

        @Test
        public void winAPointFrom30() {
            givenTennisRuleAndInitScore("0-0", "30-0")
                    .whenPlayerWinPoint("1")
                    .thenSetScoreIs("0-0")
                    .thenGameScoreIs("40-0");

            givenTennisRuleAndInitScore("0-0", "0-30")
                    .whenPlayerWinPoint("2")
                    .thenSetScoreIs("0-0")
                    .thenGameScoreIs("0-40");
        }

        @Test
        public void winAPointFrom4040() {
            givenTennisRuleAndInitScore("0-0", "40-40")
                    .whenPlayerWinPoint("1")
                    .thenSetScoreIs("0-0")
                    .thenGameScoreIs("Av-40");

            givenTennisRuleAndInitScore("0-0", "40-40")
                    .whenPlayerWinPoint("2")
                    .thenSetScoreIs("0-0")
                    .thenGameScoreIs("40-Av");
        }

        @Test
        public void winAPointFrom40Av_Equality() {
            givenTennisRuleAndInitScore("0-0", "40-Av")
                    .whenPlayerWinPoint("1")
                    .thenSetScoreIs("0-0")
                    .thenGameScoreIs("40-40");

            givenTennisRuleAndInitScore("0-0", "Av-40")
                    .whenPlayerWinPoint("2")
                    .thenSetScoreIs("0-0")
                    .thenGameScoreIs("40-40");
        }

        @Test
        public void winAPointAndAGame() {
            givenTennisRuleAndInitScore("0-0", "40-0")
                    .whenPlayerWinPoint("1")
                    .thenSetScoreIs("1-0")
                    .thenGameScoreIs("0-0");

            givenTennisRuleAndInitScore("0-0", "0-40")
                    .whenPlayerWinPoint("2")
                    .thenSetScoreIs("0-1")
                    .thenGameScoreIs("0-0");
        }

        @Test
        public void winAPointAndAGameInAdvantage() {
            givenTennisRuleAndInitScore("0-0", "Av-40")
                    .whenPlayerWinPoint("1")
                    .thenSetScoreIs("1-0")
                    .thenGameScoreIs("0-0");

            givenTennisRuleAndInitScore("0-0", "40-Av")
                    .whenPlayerWinPoint("2")
                    .thenSetScoreIs("0-1")
                    .thenGameScoreIs("0-0");
        }
    }

    @Nested
    class SetRulesTest {
        @Test
        public void winASetFromSet5ToSet6() {
            givenTennisRuleAndInitScore("5-4", "40-30")
                    .whenPlayerWinPoint("1")
                    .thenSetScoreIs("6-4", "0-0")
                    .thenGameScoreIs("0-0");

            givenTennisRuleAndInitScore("2-5", "30-40")
                    .whenPlayerWinPoint("2")
                    .thenSetScoreIs("2-6", "0-0")
                    .thenGameScoreIs("0-0");
        }

        @Test
        public void winASetFromSet6ToSet7() {
            givenTennisRuleAndInitScore("6-5", "40-30")
                    .whenPlayerWinPoint("1")
                    .thenSetScoreIs("7-5", "0-0")
                    .thenGameScoreIs("0-0");

            givenTennisRuleAndInitScore("5-6", "30-40")
                    .whenPlayerWinPoint("2")
                    .thenSetScoreIs("5-7", "0-0")
                    .thenGameScoreIs("0-0");
        }
    }

    @Nested
    class TieBreakRulesTest {


        @Test
        public void winPointBeginTieBreak() {
            givenTennisRuleAndInitScore("6-5", "30-40")
                    .whenPlayerWinPoint("2")
                    .thenSetScoreIs("6-6")
                    .thenTieBreakScoreIs("0-0")
                    .thenGameScoreIs("0-0");
        }

        @Test
        public void winPointTieBreak() {
            givenTennisRuleAndInitScore("6-6", "0-0", "0-0")
                    .whenPlayerWinPoint("1")
                    .thenSetScoreIs("6-6")
                    .thenTieBreakScoreIs("1-0")
                    .thenGameScoreIs("0-0");
        }

        @Test
        public void winPointTieBreak2PointDifferenceSupPoint7() {
            givenTennisRuleAndInitScore("6-6", "0-0", "6-0")
                    .whenPlayerWinPoint("1")
                    .thenSetScoreIs("7-6", "0-0")
                    .thenGameScoreIs("0-0");
        }
    }

    @Nested
    class MatchEndRulesTest {
        @Test
        public void winAMatchPlayer1() {
            givenTennisRuleAndInitScore(Arrays.asList("7-5", "5-2"),2, "40-0")
                    .whenPlayerWinPoint("1")
                    .thenSetScoreIs("7-5", "6-2")
                    .thenWinningPlayerIs("1");
        }

        @Test
        public void winAMatchPlayer2() {
            givenTennisRuleAndInitScore(Arrays.asList("0-6", "0-5"),2, "15-40")
                    .whenPlayerWinPoint("2")
                    .thenSetScoreIs("0-6", "0-6")
                    .thenWinningPlayerIs("2");
        }

        @Test
        public void winAMatchPlayer1Set3() {
            givenTennisRuleAndInitScore(Arrays.asList("0-6", "0-6","0-5"),3, "15-40")
                    .whenPlayerWinPoint("2")
                    .thenSetScoreIs("0-6", "0-6", "0-6")
                    .thenWinningPlayerIs("2");
        }
    }

}
