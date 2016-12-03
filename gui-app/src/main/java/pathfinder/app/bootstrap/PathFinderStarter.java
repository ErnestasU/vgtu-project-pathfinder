package pathfinder.app.bootstrap;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pathfinder.app.PathFinderScreensManager;
import pathfinder.app.utils.PropertiesLoader;

import java.util.Properties;

/**
 * @author Ernestas
 * @since 11/16/2016
 */
class PathFinderStarter {

    private static Properties props = PropertiesLoader.load("desktop.properties", PathFinderStarter.class);

    public static void start() {
        new LwjglApplication(new PathFinderScreensManager(), config());

    }

    public static LwjglApplicationConfiguration config() {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.useGL30 = false;
        config.title = props.getProperty("title");
        config.width = Integer.valueOf(props.getProperty("width"));
        config.height =  Integer.valueOf(props.getProperty("height"));
        return config;
    }
}
