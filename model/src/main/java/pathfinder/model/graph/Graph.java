package pathfinder.model.graph;

import com.google.common.collect.Iterables;
import java.util.Set;

/**
 * Created by TT on 2016-11-18.
 */
public class Graph {

    private Set<Vertex> vertices;
    private Set<Edge> edges;
    private String startPointId;
    private Edge lastEdge;
    private Vertex lastVertex;

    public Graph(Set<Vertex> vertices, Set<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
        this.lastEdge = Iterables.getLast(edges);
        this.lastVertex = Iterables.getLast(vertices);
    }

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void setEdges(Set<Edge> edgeSet) {
        this.edges = edgeSet;
    }

    public String getStartPointId() {
        return startPointId;
    }

    public void setStartPointId(String startPointId) {
        this.startPointId = startPointId;
    }

    public Edge getLastEdge() {
        return lastEdge;
    }

    public Vertex getLastVertex() {
        return lastVertex;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "vertices=" + vertices +
                ", edges=" + edges +
                '}';
    }
}
