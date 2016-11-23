package pathfinder.model;

/**
 * @author Ernestas
 * @since 2016.11.23
 */
public class CompetitionLostResult extends CompetitionResult {

    private int caloriesDiff;

    public CompetitionLostResult(int caloriesDiff, int caloriesToExaust) {
        super(caloriesToExaust);
        this.caloriesDiff = caloriesDiff;
    }

    public int getCaloriesDiff() {
        return caloriesDiff;
    }
}
