package pathfinder.app.context;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import pathfinder.model.graph.Edge;
import pathfinder.model.graph.EdgeAttribute;
import pathfinder.model.graph.Graph;
import pathfinder.model.graph.Vertex;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author Ernestas
 * @since 2016.11.22
 */
public interface GraphUiContext extends UiContext, PropsUiContext {

    Graph getGraph();

    Set<Vertex> getVertices();

    Set<Edge> getEdges();

    void setEdges(Set<Edge> edgeSet);

    Vertex getFirstVertex();

    Edge getFirstEdge();

    ArrayList<EdgeAttribute> getAttributes();

    Skin getSkin();
}
