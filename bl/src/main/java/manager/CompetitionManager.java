package manager;

import com.google.common.collect.Iterables;

import java.util.Set;

import command.impl.DjikstraCommand;
import math.CaloriesCalculator;
import pathfinder.model.CompetitionLostResult;
import pathfinder.model.CompetitionResult;
import pathfinder.model.CompetitionWinResult;
import pathfinder.model.CompetitorSummary;
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

    public CompetitorSummary summaryCompetitor(Set<Vertex> completedPath) {
        Vertex root = Iterables.getLast(completedPath);
        DjikstraCommand command = new DjikstraCommand(graph);
        command.execute(root);
        Set<Vertex> verticesToComplete = command.getPath(graph.getLastVertex());
        Set<Edge> edgesToComplete = command.getEdges(verticesToComplete);
        CaloriesCalculator caloriesCalculator = new CaloriesCalculator(edgesToComplete);
        final int caloriesToExaust = caloriesCalculator.calculate();
        return new CompetitorSummary(command.getPath(graph.getLastVertex()), caloriesToExaust);
    }

    public CompetitionResult compete(CompetitorSummary againstCompetitor) {
        final DjikstraCommand command = new DjikstraCommand(graph);
        command.execute(graph.getVertices().iterator().next());
        CaloriesCalculator caloriesCalculator = new CaloriesCalculator(command.getEdges(graph.getLastVertex()));
        final int caloriesToExaust  = caloriesCalculator.calculate();
        final int caloriesToCompete = againstCompetitor.getCaloriesToExaust();

        if (caloriesToCompete < caloriesToExaust) {
            return new CompetitionLostResult(caloriesToExaust - caloriesToCompete, caloriesToExaust);
        }
        return new CompetitionWinResult(command.getPath(graph.getLastVertex()), caloriesToExaust);

    }
}
