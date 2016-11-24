package pathfinder.app.components;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

/**
 * Created by TT on 2016-11-24.
 */
public class NodeImageButton {

    private String nodeId;
    private ImageButton imageButton;

    public NodeImageButton(String nodeId, ImageButton imageButton) {
        this.nodeId = nodeId;
        this.imageButton = imageButton;
    }

    public String getNodeId() {
        return nodeId;
    }

    public ImageButton getImageButton() {
        return imageButton;
    }

}
