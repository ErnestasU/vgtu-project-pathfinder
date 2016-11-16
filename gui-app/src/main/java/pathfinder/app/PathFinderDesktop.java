package pathfinder.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * @author Ernestas
 * @since 11/16/2016
 */
public class PathFinderDesktop {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "pathfinder";
        config.useGL30 = false;
        config.width = 1024;
        config.height = 768;

        new LwjglApplication(new PathFinderBoostrap(), config);
    }
}
