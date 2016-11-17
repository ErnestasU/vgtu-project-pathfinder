package pathfinder.app.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Ernestas
 * @since 11/16/2016
 */
public class TextureUtils {

    public static Texture loadTexture(String fileName) {
        return new Texture(Gdx.files.internal(fileName));
    }

    public static TextureRegion buildTextureRegion(String textureFileName, int x, int y, int width, int height) {
        return new TextureRegion(loadTexture(textureFileName), x, y, width, height);
    }

}
