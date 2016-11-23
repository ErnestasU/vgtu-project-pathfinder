package pathfinder.model;

/**
 * @author Ernestas
 * @since 2016.11.23
 */
public class CompetitionResult {

    public int caloriesToExaust;

    public CompetitionResult(int caloriesToExaust) {
        this.caloriesToExaust = caloriesToExaust;
    }

    public int getCaloriesToExaust() {
        return caloriesToExaust;
    }

    public void setCaloriesToExaust(int caloriesToExaust) {
        this.caloriesToExaust = caloriesToExaust;
    }
}
