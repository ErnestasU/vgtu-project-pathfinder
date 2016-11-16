package pathfinder.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * @author Ernestas
 * @since 11/16/2016
 */
public class PathFinderMenu extends ScreenAdapter {

    private PathFinderBoostrap game;
    private OrthographicCamera guiCam;

    public PathFinderMenu(PathFinderBoostrap game) {
        this.game = game;
        guiCam = new OrthographicCamera(1024, 768);
        guiCam.position.set(320 / 2, 480 / 2, 0);
    }

    public void draw () {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        game.getBatcher().setProjectionMatrix(guiCam.combined);
        game.getBatcher().enableBlending();
        game.getBatcher().begin();
        game.getBatcher().draw(Assets.backgroundRegion, 0, 0, 655, 500);
        game.getBatcher().end();
    }

    @Override
    public void render(float delta) {
        draw();
    }
}
