package manager;

import java.util.Set;

import command.impl.DjikstraCommand;
import math.CaloriesCalculator;
import pathfinder.model.CompetitionResult;
import pathfinder.model.CompetitionResult.Competitor;
import pathfinder.model.graph.Edge;
import pathfinder.model.graph.Graph;
import pathfinder.model.graph.Vertex;

/**
 * @author Ernestas
 * @since 2016.11.23
 */
public class CompetitionManager {

    public Graph graph;

    public CompetitionManager(Graph graph) {
        this.graph = graph;
    }

    public Competitor summaryCompetitor(Vertex currentPos) {
        final Vertex root = currentPos;
        DjikstraCommand command = new DjikstraCommand(graph);
        command.execute(root);
        Set<Vertex> verticesToComplete = command.getPath(graph.getLastVertex());
        Set<Edge> edgesToComplete = command.getEdges(verticesToComplete);
        CaloriesCalculator caloriesCalculator = new CaloriesCalculator(edgesToComplete);
        final int caloriesToExaust = caloriesCalculator.calculate();
        return new Competitor(caloriesToExaust, command.getPath(graph.getLastVertex()));
    }

    public CompetitionResult compete(Competitor againstCompetitor) {
        final DjikstraCommand command = new DjikstraCommand(graph);
        command.execute(graph.getVertices().iterator().next());
        CaloriesCalculator caloriesCalculator = new CaloriesCalculator(command.getEdges(graph.getLastVertex()));

        final Competitor ally = new Competitor(caloriesCalculator.calculate(), command.getPath(graph.getLastVertex()));
        final CompetitionResult result = new CompetitionResult();
        result.setAllyCompetitorWinner(ally.getCaloriesToExaust() < againstCompetitor.getCaloriesToExaust());
        result.setEnemyCompetitor(againstCompetitor);
        result.setAllyCompetitor(ally);
        return result;
    }
}
