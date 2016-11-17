package pathfinder.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pathfinder.app.components.PathFinderMenu;
import pathfinder.app.context.PathFinderUiContext;

/**
 * @author Ernestas
 * @since 11/16/2016
 */
public class PathFinderScreensManager extends Game {

    private SpriteBatch batcher;

    @Override
    public void create() {
        batcher = new SpriteBatch();
        PathFinderUiContext.init();
        setScreen(new PathFinderMenu(this));
    }

    public SpriteBatch getBatcher() {
        return batcher;
    }

    @Override
    public void render() {
         super.render();
    }
}
