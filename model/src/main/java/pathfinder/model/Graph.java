package pathfinder.model;

import java.util.Set;

/**
 * Created by TT on 2016-11-18.
 */
public class Graph {

    private Set<Vertex> vertices;
    private Set<Edge> edges;

    public Graph(Set<Vertex> vertices, Set<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "vertices=" + vertices +
                ", edges=" + edges +
                '}';
    }
}
