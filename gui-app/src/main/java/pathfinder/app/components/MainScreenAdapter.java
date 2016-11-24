package pathfinder.app.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import command.impl.DjikstraCommand;
import pathfinder.app.PathFinderScreensManager;
import pathfinder.app.attributes.TextureName;
import pathfinder.app.context.GraphUiContext;
import pathfinder.app.context.ScreensContextHolder;
import pathfinder.app.context.UiContext;
import pathfinder.model.graph.Vertex;

import static javax.swing.text.html.HTML.Tag.HEAD;


/**
 * @author Ernestas
 * @since 11/16/2016
 */
public class MainScreenAdapter extends ScreenAdapter {

    private PathFinderScreensManager pathFinderScreensManager;
    private Stage stage;
    private ShapeRenderer shapeRenderer;
    private GraphUiContext ctx;
    private boolean drawPath;
    private boolean add = true;
    private Node node;
    public MainScreenAdapter(PathFinderScreensManager pathFinderScreensManager) {
        this.pathFinderScreensManager = pathFinderScreensManager;
        stage = new Stage();
        shapeRenderer = new ShapeRenderer();
        ctx = ScreensContextHolder.get();
        node = new Node();
    }

    public void draw () {
        UiContext context = ScreensContextHolder.get();
        GL20 gl = Gdx.gl;
        gl.glClearColor(138/255f, 138/255f, 92/255f, 0);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glLineWidth(6);
        I18NBundle props = ScreensContextHolder.get().getUiProps();
        TextButton buttonCalc = new TextButton(props.get("button.calculate"), anotherSkin());
        buttonCalc.setPosition(11, 658);
        buttonCalc.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                drawPath = true;
            }
        });
        stage.addActor(buttonCalc);

        TextButton buttonClear = new TextButton(props.get("button.clear"), anotherSkin());
        buttonClear.setPosition(340, 658);
        buttonClear.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                drawPath = false;
                node.clear();
                ctx.getGraph().setStartPointId(null);
            }
        });
        stage.addActor(buttonClear);

        pathFinderScreensManager.getBatcher().enableBlending();
        pathFinderScreensManager.getBatcher().begin();
        pathFinderScreensManager.getBatcher().draw(context.getTextureRegionByName(TextureName.PATHMAP), 11, 11, 655, 542);
        pathFinderScreensManager.getBatcher().end();

        for (NodeImageButton nodeImageButton : node.getNodes()){
            stage.addActor(nodeImageButton.getImageButton());
        }
        stage.draw();

        if (drawPath) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.RED);
            final GraphUiContext ctx = ScreensContextHolder.get();
            Set<Vertex> shortestPath = DjikstraCommand.getShortestPath(ctx.getFirstVertex(), ctx.getGraph().getLastVertex(), ctx.getGraph());
            List<Vertex> shortestPathList = new ArrayList<>(shortestPath);
            int x1, x2, y1, y2;
            for (int i = 0; i < shortestPathList.size() - 1 ; i++) {
                x1 = shortestPathList.get(i).getXCoord();
                y1 = shortestPathList.get(i).getYCoord();
                x2 = shortestPathList.get(i + 1).getXCoord();
                y2 = shortestPathList.get(i + 1).getYCoord();
                shapeRenderer.line(x1+11, y1+11, x2+11, y2+11);
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

    private Skin anotherSkin(){
        // A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
        // recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
        Skin skin = new Skin();
        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(323, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));

        // Store the default libgdx font under the name "default".
        BitmapFont bfont=new BitmapFont();
        bfont.scale(1);
        skin.add("default",bfont);

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        return skin;
    }

}
