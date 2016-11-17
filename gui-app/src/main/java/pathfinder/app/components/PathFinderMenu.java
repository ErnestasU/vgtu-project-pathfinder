package pathfinder.app.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import pathfinder.app.PathFinderScreensManager;
import pathfinder.app.attributes.TextureName;
import pathfinder.app.context.ScreensContextHolder;
import pathfinder.app.context.UiContext;

/**
 * @author Ernestas
 * @since 11/16/2016
 */
public class PathFinderMenu extends ScreenAdapter {

    private PathFinderScreensManager game;
    private OrthographicCamera guiCam;

    public PathFinderMenu(PathFinderScreensManager game) {
        this.game = game;
        guiCam = new OrthographicCamera(1024, 768);
        guiCam.position.set(320 / 2, 480 / 2, 0);
    }

    public void draw () {
        UiContext context = ScreensContextHolder.get();
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        game.getBatcher().setProjectionMatrix(guiCam.combined);
        game.getBatcher().enableBlending();
        game.getBatcher().begin();
        game.getBatcher().draw(context.getTextureRegionByName(TextureName.PATHMAP), 0, 0, 655, 500);
        game.getBatcher().end();
    }

    @Override
    public void render(float delta) {
        draw();
    }
}
