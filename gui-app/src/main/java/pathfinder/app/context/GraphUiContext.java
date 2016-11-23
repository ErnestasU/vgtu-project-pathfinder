package pathfinder.app.context;

import java.util.Set;

import pathfinder.model.graph.Edge;
import pathfinder.model.graph.Graph;
import pathfinder.model.graph.Vertex;

/**
 * @author Ernestas
 * @since 2016.11.22
 */
public interface GraphUiContext extends UiContext, PropsUiContext {

    Graph getGraph();

    Set<Vertex> getVertices();

    Set<Edge> getEdges();

    Vertex getLastVertex();

    Edge getLastEdge();

    Vertex getFirstVertex();

    Edge getFirstEdge();
}
