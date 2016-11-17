package pathfinder.app.context;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import pathfinder.app.attributes.TextureName;

/**
 * @author Ernestas
 * @since 11/16/2016
 */
public interface UiContext {

    TextureRegion getTextureRegionByName(TextureName name);
}
