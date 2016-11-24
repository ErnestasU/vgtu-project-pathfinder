package bl.test.djikstra;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import bl.test.BaseTest;
import command.impl.DjikstraCommand;
import command.impl.DjikstraKthPathCommand;
import math.CaloriesCalculator;
import pathfinder.model.graph.Vertex;

/**
 * @author Ernestas
 * @since 2016.11.24
 */
public class DjikstraKthPathCommandTest extends BaseTest {

    @Test
    public void testSecondShortestPath() {
        final Vertex source = graph.getVertices().iterator().next();
        final Vertex dst = graph.getLastVertex();
        DjikstraCommand command = new DjikstraCommand(graph);
        command.execute(source);
        Set<Vertex> shortestPath = command.getPath(dst);
        CaloriesCalculator firstPathCalc = new CaloriesCalculator(command.getEdges(shortestPath));
        final int firstPathWeight = firstPathCalc.calculate();
        DjikstraKthPathCommand djikstraKthCommand = new DjikstraKthPathCommand(graph, shortestPath);
        Set<Vertex> secondShortestPath = djikstraKthCommand.nextPath(source, dst);
        CaloriesCalculator secondPathCalc = new CaloriesCalculator(command.getEdges(secondShortestPath));
        final int secondPathWeight = secondPathCalc.calculate();
        Assert.assertTrue(firstPathWeight < secondPathWeight);
    }
}
