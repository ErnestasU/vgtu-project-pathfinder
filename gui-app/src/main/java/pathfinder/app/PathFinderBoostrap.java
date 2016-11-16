package pathfinder.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Ernestas
 * @since 11/16/2016
 */
public class PathFinderBoostrap extends Game {

    private SpriteBatch batcher;

    @Override
    public void create() {
        batcher = new SpriteBatch();
        Assets.load();
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
