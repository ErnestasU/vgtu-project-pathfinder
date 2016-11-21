package pathfinder.app.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
        gl.glClearColor(213, 218, 223, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();

        Stage stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        TextButton button = new TextButton("Paskaiciuoti", createBasicSkin());
        button.setPosition(0, 700);
        stage.addActor(button);
        stage.draw();

        game.getBatcher().setProjectionMatrix(guiCam.combined);
        game.getBatcher().enableBlending();
        game.getBatcher().begin();
        game.getBatcher().draw(context.getTextureRegionByName(TextureName.PATHMAP), 0, 0, 655, 542);
        game.getBatcher().end();

        gl.glLineWidth(6);
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(guiCam.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.line(65, 337, 210, 430);
        shapeRenderer.line(210, 430, 310, 470);
        shapeRenderer.end();


    }

    @Override
    public void render(float delta) {
        draw();
    }

    private Skin createBasicSkin(){
        //Create a font
        BitmapFont font = new BitmapFont();
        Skin skin = new Skin();
        skin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background",new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
        return skin;
    }
}
