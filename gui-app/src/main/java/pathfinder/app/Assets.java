package pathfinder.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Ernestas
 * @since 11/16/2016
 */
public class Assets {

    public static Texture background;
    public static TextureRegion backgroundRegion;

    public static Texture loadTexture(String file) {
        return new Texture(Gdx.files.internal(file));
    }

    public static void load() {
        background = loadTexture("data/map1.png");
        backgroundRegion = new TextureRegion(background, 0,0, 655, 500);
        backgroundRegion.scroll(100, 100);
    }
}
