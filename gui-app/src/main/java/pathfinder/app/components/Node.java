package pathfinder.app.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import pathfinder.app.context.GraphUiContext;
import pathfinder.app.context.ScreensContextHolder;
import pathfinder.model.graph.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TT on 2016-11-24.
 */
public class Node {

    private List<NodeImageButton> nodes;
    private GraphUiContext ctx;
    private String firstNode;
    private String lastNode;

    private ImageButton getPointButton(String nodeId, int x, int y){
        ImageButton button = new ImageButton(getRadioButtonRed());
        button.setSize(28, 28);
        button.setPosition(x, y);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!(nodeId.equals(firstNode) || nodeId.equals(lastNode))) {
                    ImageButton oldButton = getImageButtonById(ctx.getGraph().getStartPointId());
                    if (oldButton != null) {
                        oldButton.getStyle().imageUp = getRadioButtonRed();
                    }
                    ctx.getGraph().setStartPointId(nodeId);
                    ImageButton newButton = getImageButtonById(ctx.getGraph().getStartPointId());
                    newButton.getStyle().imageUp = getRadioButtonGreen();
                }
            }
        });
        button.toBack();
        return button;
    }

    private ImageButton getImageButtonById(String nodeId) {
      for (NodeImageButton nodeImageButton : nodes) {
          if (nodeImageButton.getNodeId().equals(nodeId)) {
              return nodeImageButton.getImageButton();
          }
      }
      return null;
    };

    private TextureRegionDrawable getRadioButtonRed(){
        return new TextureRegionDrawable(
                new TextureRegion(
                        new Texture(Gdx.files.internal("radio_button_red.png"))));
    };

    private TextureRegionDrawable getRadioButtonGreen(){
        return new TextureRegionDrawable(
                new TextureRegion(
                        new Texture(Gdx.files.internal("radio_button_green.png"))));
    };

    public Node() {
        ctx = ScreensContextHolder.get();
        nodes = new ArrayList<>();
        for (Vertex node : ScreensContextHolder.get().getVertices()) {
            nodes.add(new NodeImageButton(node.getId(), getPointButton(node.getId(), node.getXCoord(), node.getYCoord())));
        }
        firstNode = nodes.get(0).getNodeId();
        lastNode = nodes.get(nodes.size()-1).getNodeId();
    }

    public List<NodeImageButton> getNodes() {
        return nodes;
    }

    public void clear(){
        ImageButton oldButton = getImageButtonById(ctx.getGraph().getStartPointId());
        if (oldButton != null) {
            oldButton.getStyle().imageUp = getRadioButtonRed();
        }
    }

}
