package graph.utils;

import java.util.HashSet;
import java.util.Set;

import pathfinder.model.Edge;
import pathfinder.model.Vertex;

/**
 * @author Ernestas
 * @since 2016.11.23
 */
public class GraphUtils {

    /**
     * <p> Returns edges from start to end node</p>
     */
    public static Set<Edge> getEdgesFromStartEnd(final Vertex start, final Vertex end, Set<Edge> edges) {
        if (start.equals(end)) {
            throw new IllegalArgumentException("Start and end nodes should be different!");
        }
        final Set<Edge> result = new HashSet();
        Vertex startNode = start;
        for (Edge edge : edges) {
            if (edge.getSourceNode().equals(start)) {
                result.add(edge);
                startNode = edge.getDestNode();
            }
            if (startNode.equals(end)) {
                break;
            }
        }
        return result;
    }

}
