package pathfinder.app.bootstrap;

import com.badlogic.gdx.backends.lwjgl.LwjglApplet;
import pathfinder.app.PathFinderScreensManager;

/**
 * Created by TT on 2016-12-03.
 */
public class PathFinderApplet extends LwjglApplet {

    public PathFinderApplet() {
        super(new PathFinderScreensManager(), PathFinderStarter.config());
    }
}
