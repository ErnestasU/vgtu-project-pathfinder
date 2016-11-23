package pathfinder.app.context;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import graph.GraphInitializer;
import pathfinder.app.attributes.TextureName;
import pathfinder.app.utils.TextUtils;
import pathfinder.app.utils.TextureUtils;
import pathfinder.model.graph.Edge;
import pathfinder.model.graph.Graph;
import pathfinder.model.graph.Vertex;

import static pathfinder.app.constants.GeneralConstant.MAP_DATA_DIR;

/**
 * @author Ernestas
 * @since 11/16/2016
 */
public class PathFinderUiContext implements GraphUiContext {

    private Map<TextureName, TextureRegion> texturesRegions = new HashMap<>();
    private Graph graph;
    private I18NBundle uiProps;

    private boolean created = false;

    private PathFinderUiContext() {
        if (created) {
            throw new IllegalArgumentException("cannot create context twice!");
        }
        this.created = true;
    }

    @Override
    public TextureRegion getTextureRegionByName(TextureName name) {
        return texturesRegions.get(name);
    }

    public static void init() {
        final PathFinderUiContext context = new PathFinderUiContext();
        ScreensContextHolder.hold(context);
        context.graph = GraphInitializer.initialize();
        FileHandle baseFileHandle = Gdx.files.internal("localization");
        context.uiProps = I18NBundle.createBundle(baseFileHandle, Locale.getDefault());
        context.initTextures();
    }

    private void initTextures() {
        // initial path map
        texturesRegions.put(TextureName.PATHMAP, TextureUtils.buildTextureRegion(TextUtils.joinPath(MAP_DATA_DIR,"map1.png"),
                                                                                    0, 0, 655, 542));
        // the rest textures....
    }

    @Override
    public Graph getGraph() {
        return graph;
    }

    @Override
    public Set<Vertex> getVertices() {
        return graph.getVertices();
    }

    @Override
    public Set<Edge> getEdges() {
        return graph.getEdges();
    }

    @Override
    public Vertex getFirstVertex() {
        return graph.getVertices().iterator().next();
    }

    @Override
    public Edge getFirstEdge() {
        return graph.getEdges().iterator().next();
    }

    @Override
    public I18NBundle getUiProps() {
        return uiProps;
    }
}
