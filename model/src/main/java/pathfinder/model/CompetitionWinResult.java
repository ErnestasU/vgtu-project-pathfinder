package pathfinder.model;

import java.util.Set;

import pathfinder.model.graph.Vertex;

/**
 * @author Ernestas
 * @since 2016.11.23
 */
public class CompetitionWinResult extends CompetitionResult {

    Set<Vertex> vertexToComplete;

    public CompetitionWinResult(Set<Vertex> vertexToComplete, int caloriesToExaust) {
        super(caloriesToExaust);
        this.vertexToComplete = vertexToComplete;
    }

    public Set<Vertex> getVertexToComplete() {
        return vertexToComplete;
    }
}
