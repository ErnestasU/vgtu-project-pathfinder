package bl.test.manager;

import org.junit.Assert;
import org.junit.Test;

import bl.test.BaseTest;
import command.impl.DjikstraCommand;
import manager.CompetitionManager;
import pathfinder.model.CompetitionResult;
import pathfinder.model.CompetitionResult.Competitor;

/**
 * @author Ernestas
 * @since 2016.11.23
 */
public class CompetitionManagerTest extends BaseTest {

    @Test
    public void testCompetitorWin() {
        CompetitionManager manager = new CompetitionManager(graph);
        DjikstraCommand command = new DjikstraCommand(graph);
        command.execute(graph.getVertices().iterator().next());
        Competitor summary = manager.summaryCompetitor(graph.getVertices().iterator().next());
        CompetitionResult result = manager.compete(summary);

        Assert.assertTrue(result.isAllyCompetitorWinner());
    }

}
