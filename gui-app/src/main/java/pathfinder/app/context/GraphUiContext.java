package pathfinder.app.context;

import java.util.Set;

import pathfinder.model.Edge;
import pathfinder.model.Graph;
import pathfinder.model.Vertex;

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
