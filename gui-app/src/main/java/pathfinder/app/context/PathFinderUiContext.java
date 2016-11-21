package pathfinder.app.context;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

import pathfinder.app.attributes.TextureName;
import pathfinder.app.utils.TextUtils;
import pathfinder.app.utils.TextureUtils;

import static pathfinder.app.constants.GeneralConstant.MAP_DATA_DIR;

/**
 * @author Ernestas
 * @since 11/16/2016
 */
public class PathFinderUiContext implements UiContext {

    private Map<TextureName, TextureRegion> texturesRegions = new HashMap<>();

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
        context.initTextures();
    }

    private void initTextures() {
        // initial path map
        texturesRegions.put(TextureName.PATHMAP, TextureUtils.buildTextureRegion(TextUtils.joinPath(MAP_DATA_DIR,"map1.png"),
                                                                                    0, 0, 655, 542));


        // the rest textures....
    }

}
