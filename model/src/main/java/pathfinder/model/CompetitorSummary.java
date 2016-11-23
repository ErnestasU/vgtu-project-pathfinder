package pathfinder.model;

import java.util.Set;

import pathfinder.model.graph.Vertex;

/**
 * @author Ernestas
 * @since 2016.11.23
 */
public class CompetitorSummary {

    public int caloriesToExaust;
    public Set<Vertex> shortestPathToComplete;

    public CompetitorSummary(Set<Vertex> shortestPathToComplete, int caloriesToExaust) {
        this.caloriesToExaust = caloriesToExaust;
        this.shortestPathToComplete = shortestPathToComplete;
    }

    public Set<Vertex> getShortestPathToComplete() {
        return shortestPathToComplete;
    }

    public int getCaloriesToExaust() {
        return caloriesToExaust;
    }
}
