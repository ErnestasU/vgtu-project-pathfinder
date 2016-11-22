package pathfinder.app.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import graph.GraphIntializer;
import pathfinder.app.PathFinderScreensManager;
import pathfinder.app.attributes.TextureName;
import pathfinder.app.context.ScreensContextHolder;
import pathfinder.app.context.UiContext;
import pathfinder.model.Vertex;

import java.util.List;
import java.util.Set;

/**
 * @author Ernestas
 * @since 11/16/2016
 */
public class PathFinderMenu extends ScreenAdapter {

    private PathFinderScreensManager game;
    private OrthographicCamera guiCam;
    private Stage stage;
    private ShapeRenderer shapeRenderer;
    private boolean drawPath;
    private List<Vertex> path;
    private Set<Vertex> nodes;

    public PathFinderMenu(PathFinderScreensManager game) {
        this.game = game;
        guiCam = new OrthographicCamera(1024, 768);
        guiCam.position.set(150, 165, 0);
        stage = new Stage();
        shapeRenderer = new ShapeRenderer();
        nodes = new GraphIntializer().initialize().getVertices();
    }

    public void draw () {
        UiContext context = ScreensContextHolder.get();
        GL20 gl = Gdx.gl;
        gl.glClearColor(138/255f, 138/255f, 92/255f, 0);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glLineWidth(6);
        guiCam.update();
        TextButton button = new TextButton("Paskaiciuoti", createBasicSkin());
        button.setPosition(5, 685);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                drawPath = true;
                path = GraphIntializer.getPath();
            }
        });
        stage.addActor(button);
        stage.draw();

        game.getBatcher().setProjectionMatrix(guiCam.combined);
        game.getBatcher().enableBlending();
        game.getBatcher().begin();
        game.getBatcher().draw(context.getTextureRegionByName(TextureName.PATHMAP), 0, 0, 655, 542);
        game.getBatcher().end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setProjectionMatrix(guiCam.combined);
        shapeRenderer.setColor(Color.BLUE);
        for (Vertex node : nodes) {
            shapeRenderer.circle(node.getXCoord(), node.getYCoord(), 8);
        }
        shapeRenderer.end();

        if (drawPath) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setProjectionMatrix(guiCam.combined);
            shapeRenderer.setColor(Color.RED);
            int x1, x2, y1, y2;
            for (int i = 0; i < path.size() - 1 ; i++) {
                x1 = path.get(i).getXCoord();
                y1 = path.get(i).getYCoord();
                x2 = path.get(i + 1).getXCoord();
                y2 = path.get(i + 1).getYCoord();
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
