package bl.test;

import com.google.common.collect.Iterables;

import org.apache.log4j.BasicConfigurator;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.util.Collections;
import java.util.Set;

import command.impl.DjikstraCommand;
import graph.GraphInitializer;
import pathfinder.model.graph.Edge;
import pathfinder.model.graph.Graph;

/**
 * @author Ernestas
 * @since 2016.11.23
 */
public class BaseTest {

    protected static Graph graph;
    protected static Set<Edge> fullPathEdges;

    public BaseTest() {
        BasicConfigurator.configure();
    }

    @BeforeClass
    public static void setUpGlobally() {
        BaseTest.graph = GraphInitializer.initialize();
        DjikstraCommand command = new DjikstraCommand(graph);
        command.execute(graph.getVertices().iterator().next());
        BaseTest.fullPathEdges = Collections.unmodifiableSet(command.getEdges(Iterables.getLast(graph.getVertices())));
    }

    @AfterClass
    public static void cleanUp() {
        BaseTest.graph = null;
        BaseTest.fullPathEdges = null;
    }
}
