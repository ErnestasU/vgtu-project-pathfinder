package pathfinder.model.graph;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ernestas
 * @since 2016.11.24
 */
public class RestrictedGraph extends Graph {

    private Set<String> edgesToExclude = new HashSet<>();
    private Set<String> verticesToExclude = new HashSet<>();

    public RestrictedGraph(Set<Vertex> vertices, Set<Edge> edges) {
        super(vertices, edges);
    }

    public void excludeVertex(String id) {
        verticesToExclude.add(id);
    }

    public void excludeEdge(String sourceId, String dstId) {
        edgesToExclude.add(String.format(Edge.ID_FORMAT, sourceId, dstId));
    }

    public void recoverEdges() {
        edgesToExclude.clear();
    }

    public void recoverVertices() {
        verticesToExclude.clear();
    }

    @Override
    public Set<Vertex> getVertices() {
        Set<Vertex> vertices = super.getVertices();
        if (!verticesToExclude.isEmpty()) {
            return vertices.stream().filter(v -> !verticesToExclude.contains(v.getId())).collect(Collectors.toCollection(() -> new LinkedHashSet<>()));
        }
        return vertices;
    }

    @Override
    public Set<Edge> getEdges() {
        Set<Edge> edges = super.getEdges();
        if (!edgesToExclude.isEmpty()) {
            return edges.stream().filter(v -> !edgesToExclude.contains(v.getId())).collect(Collectors.toCollection(() -> new LinkedHashSet<>()));
        }
        return edges;
    }
}
