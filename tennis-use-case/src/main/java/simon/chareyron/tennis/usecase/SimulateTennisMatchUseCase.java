package simon.chareyron.tennis.usecase;

/**
 * @author djz4712
 */
public interface SimulateTennisMatchUseCase<T> {

    T playRandomMatch(int nbWinningSet);
}
