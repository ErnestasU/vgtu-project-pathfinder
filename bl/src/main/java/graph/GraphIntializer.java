package graph;

import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import algorithm.Djikstra;
import pathfinder.model.Edge;
import pathfinder.model.Graph;
import pathfinder.model.Vertex;

import static graph.attributes.MapInitialDataMetaData.MAP1_DATA_FILE_NAME;
import static graph.attributes.MapInitialDataMetaData.MAP_VERTEX_DATA_REGEXP;

/**
 * @author Ernestas
 * @since 11/20/2016
 */
public class GraphIntializer {

    private static final Logger LOGGER = Logger.getLogger(GraphIntializer.class);

    public Graph initialize() {
        Set<Vertex> nodes = new LinkedHashSet<>();
        Set<Edge> edges = new LinkedHashSet<>();
        GraphReader.readAndBuild(nodes, edges);
        final Graph graph = new Graph(nodes, edges);
        LOGGER.debug(graph);
        return graph;
    }

    private static class GraphReader {

        public static void readAndBuild(Set<Vertex> nodes, Set<Edge> edges) {
            try {
                Files.lines(Paths.get(ClassLoader.getSystemResource(MAP1_DATA_FILE_NAME).toURI())).forEach(s -> {
                    final String normalized = s.replaceAll("\\s+","");
                    if (normalized.contains("-")) {
                        String[] result = normalized.split("-");
                        Vertex current;
                        if (result[1].matches(MAP_VERTEX_DATA_REGEXP)) {
                            current = createVertex(result[1]);
                        } else {
                            current = resolveVertex(result[1], nodes);
                        }
                        edges.add(createEdge(nodes, current, result[0]));
                        nodes.add(current);
                    } else {
                        nodes.add(createVertex(normalized));
                    }
                });
            } catch (IOException | URISyntaxException e) {
                LOGGER.error(e);
                throw Throwables.propagate(e);
            }
        }

        private static Vertex resolveVertex(String id, Set<Vertex> nodes) {
            return nodes.stream().filter(v -> v.getId().equals(id)).findFirst().get();
        }

        private static Edge createEdge(Set<Vertex> nodes, Vertex dest, String expr) {
            String[] result = expr.split(":");
            final String vertexNameToAttachWith = result[0];
            final int distanceBetweenVertexes = Integer.valueOf(result[1]);
            Optional<Vertex> srcOpt = nodes.stream().filter(v -> v.getId().equals(vertexNameToAttachWith)).findFirst();
            Vertex src = srcOpt.orElseThrow(IllegalArgumentException::new);
            return new Edge(src, dest, distanceBetweenVertexes);

        }

        private static Vertex createVertex(String expr) {
            final String[] result = expr.split(":|;");
            final String name = result[0];
            final int x = Integer.valueOf(result[1]);
            final int y = Integer.valueOf(result[2]);
            return new Vertex(name, x, y);
        }
    }

    /** <b>NOTE </b>: For debugging purposes */
    /**TODO: delete when pathfinder will be completed*/
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Djikstra djikstra = new Djikstra(new GraphIntializer().initialize());
        Graph graph = new GraphIntializer().initialize();
        Vertex head = graph.getVertices().iterator().next();
        djikstra.execute(head);
        Vertex last = Iterables.getLast(graph.getVertices());
        List<Vertex> result = djikstra.getPath(last);
        LOGGER.debug("RESULT: \n");
        LOGGER.debug(result.stream().map(Vertex::toString).collect(Collectors.joining(",")));

    }

}
