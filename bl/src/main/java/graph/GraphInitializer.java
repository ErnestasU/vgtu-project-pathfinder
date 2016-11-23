package graph;

import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import command.impl.DjikstraCommand;
import graph.utils.EnumUtils;
import pathfinder.model.Edge;
import pathfinder.model.EdgeAttribute;
import pathfinder.model.FeaturedEdge;
import pathfinder.model.Graph;
import pathfinder.model.Vertex;

import static graph.attributes.MapInitialDataMetaData.MAP1_DATA_FILE_NAME;
import static graph.attributes.MapInitialDataMetaData.MAP_FEATURED_EDGE_DATA_REGEXP;
import static graph.attributes.MapInitialDataMetaData.MAP_VERTEX_DATA_REGEXP;

/**
 * @author Ernestas
 * @since 11/20/2016
 */
public class GraphInitializer {

    private static final Logger LOGGER = Logger.getLogger(GraphInitializer.class);

    public static Graph initialize() {
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
            if (expr.matches(MAP_FEATURED_EDGE_DATA_REGEXP)) {
                return createFeaturedEdge(nodes, dest, expr);
            }
            String[] result = expr.split(":");
            final String vertexNameToAttachWith = result[0];
            Optional<Vertex> srcOpt = nodes.stream().filter(v -> v.getId().equals(vertexNameToAttachWith)).findFirst();
            final Vertex src = srcOpt.orElseThrow(IllegalArgumentException::new);
            final int distanceBetweenVerteces = Integer.valueOf(result[1]);
            return new Edge(src, dest, distanceBetweenVerteces);
        }

        private static FeaturedEdge createFeaturedEdge(Set<Vertex> nodes, Vertex dest, String expr) {
            Pattern p = Pattern.compile(MAP_FEATURED_EDGE_DATA_REGEXP);
            Matcher m = p.matcher(expr);
            m.find();
            String vertexNameToAttachWith = m.group(1);
            Optional<Vertex> srcOpt = nodes.stream().filter(v -> v.getId().equals(vertexNameToAttachWith)).findFirst();
            final Vertex src = srcOpt.orElseThrow(IllegalArgumentException::new);
            int distanceBetweenVerteces = Integer.valueOf(m.group(2));
            EnumSet<EdgeAttribute> vertexAttributes = EnumUtils.toSet(EdgeAttribute.class, m.group(3).split(","));
            return new FeaturedEdge(src, dest, distanceBetweenVerteces)
                    .ofAttributes(vertexAttributes);
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
        Graph graph = new GraphInitializer().initialize();
        Vertex head = graph.getVertices().iterator().next();
        Vertex last = Iterables.getLast(graph.getVertices());
        List<Vertex> result = DjikstraCommand.ofShortestPath(head, last, graph);
        LOGGER.debug("RESULT: \n");
        LOGGER.debug(result.stream().map(Vertex::toString).collect(Collectors.joining(",")));
    }
}
