package bl.test.manager;

import org.junit.Assert;
import org.junit.Test;

import bl.test.BaseTest;
import command.impl.DjikstraCommand;
import manager.CompetitionManager;
import pathfinder.model.CompetitionLostResult;
import pathfinder.model.CompetitionResult;
import pathfinder.model.CompetitionWinResult;
import pathfinder.model.CompetitorSummary;
import pathfinder.model.graph.Vertex;

/**
 * @author Ernestas
 * @since 2016.11.23
 */
public class CompetitionManagerTest extends BaseTest {

    @Test
    public void testCompetitorLost() {
        CompetitionManager manager = new CompetitionManager(graph);
        DjikstraCommand command = new DjikstraCommand(graph);
        Vertex endVertex = graph.getVertices().stream().filter(v -> v.getId().equals("E272")).findFirst().get();
        command.execute(graph.getVertices().iterator().next());
        CompetitorSummary summary = manager.summaryCompetitor(command.getPath(endVertex));
        CompetitionResult result = manager.compete(summary);

        Assert.assertTrue(result instanceof CompetitionWinResult);
        Assert.assertTrue(result.getCaloriesToExaust() < summary.getCaloriesToExaust());
    }

    @Test
    public void testCompetitorWin() {
        CompetitionManager manager = new CompetitionManager(graph);
        DjikstraCommand command = new DjikstraCommand(graph);
        Vertex endVertex = graph.getVertices().stream().filter(v -> v.getId().equals("MOLETAI")).findFirst().get();
        command.execute(graph.getVertices().iterator().next());
        CompetitorSummary summary = manager.summaryCompetitor(command.getPath(endVertex));
        CompetitionResult result = manager.compete(summary);

        Assert.assertTrue(result instanceof CompetitionLostResult);
        Assert.assertTrue(result.getCaloriesToExaust() > summary.getCaloriesToExaust());
    }

}
