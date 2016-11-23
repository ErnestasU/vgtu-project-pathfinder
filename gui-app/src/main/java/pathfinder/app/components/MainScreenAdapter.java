package pathfinder.app.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.List;

import command.impl.DjikstraCommand;
import pathfinder.app.PathFinderScreensManager;
import pathfinder.app.attributes.TextureName;
import pathfinder.app.context.GraphUiContext;
import pathfinder.app.context.ScreensContextHolder;
import pathfinder.app.context.UiContext;
import pathfinder.model.graph.Vertex;


/**
 * @author Ernestas
 * @since 11/16/2016
 */
public class MainScreenAdapter extends ScreenAdapter {

    private PathFinderScreensManager pathFinderScreensManager;
    private OrthographicCamera guiCam;
    private Stage stage;
    private ShapeRenderer shapeRenderer;
    private boolean drawPath;

    public MainScreenAdapter(PathFinderScreensManager pathFinderScreensManager) {
        this.pathFinderScreensManager = pathFinderScreensManager;
        guiCam = new OrthographicCamera(1024, 768);
        guiCam.position.set(150, 165, 0);
        stage = new Stage();
        shapeRenderer = new ShapeRenderer();
    }

    public void draw () {
        UiContext context = ScreensContextHolder.get();
        GL20 gl = Gdx.gl;
        gl.glClearColor(138/255f, 138/255f, 92/255f, 0);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glLineWidth(6);
        guiCam.update();
        I18NBundle props = ScreensContextHolder.get().getUiProps();
        TextButton button = new TextButton(props.get("button.calculate"), createBasicSkin());
        button.setPosition(5, 685);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                drawPath = true;
            }
        });
        stage.addActor(button);
        stage.draw();

        pathFinderScreensManager.getBatcher().setProjectionMatrix(guiCam.combined);
        pathFinderScreensManager.getBatcher().enableBlending();
        pathFinderScreensManager.getBatcher().begin();
        pathFinderScreensManager.getBatcher().draw(context.getTextureRegionByName(TextureName.PATHMAP), 0, 0, 655, 542);
        pathFinderScreensManager.getBatcher().end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setProjectionMatrix(guiCam.combined);
        shapeRenderer.setColor(Color.BLUE);
        for (Vertex node : ScreensContextHolder.get().getVertices()) {
            shapeRenderer.circle(node.getXCoord(), node.getYCoord(), 8);
        }
        shapeRenderer.end();

        if (drawPath) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setProjectionMatrix(guiCam.combined);
            shapeRenderer.setColor(Color.RED);
            final GraphUiContext ctx = ScreensContextHolder.get();
            List<Vertex> shortestPath = DjikstraCommand.ofShortestPath(ctx.getFirstVertex(), ctx.getLastVertex(), ctx.getGraph());
            int x1, x2, y1, y2;
            for (int i = 0; i < shortestPath.size() - 1 ; i++) {
                x1 = shortestPath.get(i).getXCoord();
                y1 = shortestPath.get(i).getYCoord();
                x2 = shortestPath.get(i + 1).getXCoord();
                y2 = shortestPath.get(i + 1).getYCoord();
                shapeRenderer.line(x1, y1, x2, y2);
            }
            shapeRenderer.end();
        }

    }

    @Override
    public void render(float delta) {
        draw();
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(stage);
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

        //Create a button style;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", new Color(26/255f, 26/255f, 26/255f, 1));
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
        return skin;
    }
}
