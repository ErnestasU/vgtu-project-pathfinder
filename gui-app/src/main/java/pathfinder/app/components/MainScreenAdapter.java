package pathfinder.app.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;
import command.impl.DjikstraCommand;
import pathfinder.app.PathFinderScreensManager;
import pathfinder.app.attributes.TextureName;
import pathfinder.app.context.GraphUiContext;
import pathfinder.app.context.ScreensContextHolder;
import pathfinder.app.context.UiContext;
import pathfinder.model.graph.Edge;
import pathfinder.model.graph.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * @author Ernestas
 * @since 11/16/2016
 */
public class MainScreenAdapter extends ScreenAdapter {

    private PathFinderScreensManager pathFinderScreensManager;
    private Stage stage;
    private ShapeRenderer shapeRenderer;

    private GraphUiContext ctx;
    private Node node;
    private EdgeAttributeDrawable edgeAttributeDrawable;
    private Array<String> selectBoxItems;

    private String selected;
    private String firstNode;
    private String lastNode;
    private boolean drawPath;
    private boolean drawSelectedEdge;

    private TextButton buttonCalc;
    private TextButton buttonClear;
    private SelectBox<String> selectBox;
    private CheckBox checkBoxDrawSelected;
    private Label infoLabel;

    public MainScreenAdapter(PathFinderScreensManager pathFinderScreensManager) {
        this.pathFinderScreensManager = pathFinderScreensManager;
        stage = new Stage();
        shapeRenderer = new ShapeRenderer();
        ctx = ScreensContextHolder.get();
        node = new Node();

        firstNode = node.getNodes().get(0).getNodeId();
        lastNode = node.getNodes().get(node.getNodes().size()-1).getNodeId();

        edgeAttributeDrawable = new EdgeAttributeDrawable();
        selectBoxItems = new Array<>();
        for (Edge edge : ctx.getEdges()) {
            selectBoxItems.add(edge.getId());
        }
        selected = selectBoxItems.first();
        initButtons();
    }

    public void draw () {
        UiContext context = ScreensContextHolder.get();
        GL20 gl = Gdx.gl;
        gl.glClearColor(138/255f, 138/255f, 92/255f, 0);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glLineWidth(6);

        // add buttons
        stage.addActor(buttonCalc);
        stage.addActor(buttonClear);
        stage.addActor(selectBox);
        stage.addActor(checkBoxDrawSelected);

        // add edge attributes
        for (CheckBox edgeAttribute : edgeAttributeDrawable.getAttributes()){
            stage.addActor(edgeAttribute);
        }

        //draw map
        pathFinderScreensManager.getBatcher().enableBlending();
        pathFinderScreensManager.getBatcher().begin();
        pathFinderScreensManager.getBatcher().draw(context.getTextureRegionByName(TextureName.PATHMAP), 11, 11, 655, 542);
        pathFinderScreensManager.getBatcher().end();


        //add nodes to map
        for (NodeImageButton nodeImageButton : node.getNodes()){
            stage.addActor(nodeImageButton.getImageButton());
        }

        stage.act();
        stage.draw();

        //draw results
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

        //draw selected edge
        if (drawSelectedEdge) {
            edgeAttributeDrawable.drawSelectedEdge(shapeRenderer);
        }

        //show info label
        stage.addActor(infoLabel);

    }

    @Override
    public void render(float delta) {
        draw();
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(stage);
    }

    private void initButtons() {
        I18NBundle props = ScreensContextHolder.get().getUiProps();

        buttonCalc = new TextButton(props.get("button.calculate"), ctx.getSkin());
        buttonCalc.setPosition(11, 658);
        buttonCalc.setSize(310, 60);
        buttonCalc.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String startPointId = ctx.getGraph().getStartPointId();
                if (!(startPointId == null || startPointId.equals(firstNode) || startPointId.equals(lastNode))) {
                    drawPath = true;
                }
            }
        });

        buttonClear = new TextButton(props.get("button.clear"), ctx.getSkin());
        buttonClear.setPosition(330, 658);
        buttonClear.setSize(310, 60);
        buttonClear.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                drawPath = false;
                node.clear();
                ctx.getGraph().setStartPointId(null);
            }
        });

        selectBox = new SelectBox<String>(ctx.getSkin());
        selectBox.setPosition(660, 658);
        selectBox.setSize(200, 60);
        selectBox.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                selected = selectBox.getSelected();
                edgeAttributeDrawable.setSelected(selected);
                edgeAttributeDrawable.refresh();
            }
        });
        selectBox.setItems(selectBoxItems);
        selectBox.setSelected(selected);
        edgeAttributeDrawable.setSelected(selected);
        edgeAttributeDrawable.refresh();

        checkBoxDrawSelected = new CheckBox(props.get("checkBox.drawSelected"), ctx.getSkin());
        checkBoxDrawSelected.setPosition(880, 675);;
        checkBoxDrawSelected.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                drawSelectedEdge = checkBoxDrawSelected.isChecked();
                checkBoxDrawSelected.setChecked(drawSelectedEdge);
            }
        });

        String text = "Prasome pasirinkti antro dviratininko pradzios vieta (Negalima rinktis " + firstNode + " ir " + lastNode + ")";
        infoLabel = new Label(text, ctx.getSkin());
        infoLabel.setColor(Color.WHITE);
        infoLabel.setPosition(11, 600);
    }

}
