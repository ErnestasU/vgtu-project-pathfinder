package command.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import pathfinder.model.graph.RestrictedGraph;
import pathfinder.model.graph.Vertex;

/**
 * @author Ernestas
 * @since 2016.11.24
 */
public class DjikstraKthPathCommand {

    private RestrictedGraph graph;
    private List<Set<Vertex>> paths = new ArrayList<>();

    public DjikstraKthPathCommand(RestrictedGraph graph, Set<Vertex> shortestPath) {
        this.graph = graph;
        this.paths.add(shortestPath);
    }

    public Set<Vertex> nextPath(Vertex source, Vertex dest) {
        for (Set<Vertex> path : paths) {
            int pathLength = path.size();
            List<Vertex> vertices = new ArrayList<>(path);
            for(int i=0; i< pathLength-1; ++i) {
                graph.excludeVertex(vertices.get(i).getId());
                graph.excludeEdge(vertices.get(i).getId(), vertices.get(i+1).getId());
            }
        }
        Set<Vertex> candidate = DjikstraCommand.getShortestPath(source, dest, graph);
        if (candidate != null) {
            paths.add(candidate);
        }
        graph.recoverVertices();
        graph.recoverEdges();
        return candidate;
    }
}
