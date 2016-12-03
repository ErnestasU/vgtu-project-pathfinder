package pathfinder.app.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import pathfinder.app.context.GraphUiContext;
import pathfinder.app.context.ScreensContextHolder;
import pathfinder.model.graph.Edge;
import pathfinder.model.graph.EdgeAttribute;
import pathfinder.model.graph.FeaturedEdge;

/**
 * Created by TT on 2016-11-30.
 */
public class EdgeAttributeDrawable {

    private List<CheckBox> attributes;
    private GraphUiContext ctx;
    private int coordY = 535;
    private int sourceX = 0;
    private int sourceY = 0;
    private int destX = 0;
    private int destY = 0;
    private String selected = "";

    private CheckBox getNewCheckBox(String name, EdgeAttribute edgeAttribute){
        CheckBox attribute = new CheckBox(name, ctx.getSkin());
        attribute.setPosition(880, coordY);
        attribute.setChecked(false);
        attribute.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String name = attribute.getLabel().getText().toString();
                if (attribute.isChecked()) {
                    // add attribute
                    for (Edge edge : ctx.getEdges()) {
                        if (edge.getId().equals(selected)) {
                            try {
                                FeaturedEdge featuredEdge = (FeaturedEdge) edge;
                                featuredEdge.getAttributes().add(getEdgeAttribute(name));
                            } catch (ClassCastException e) {
                                Set<EdgeAttribute> edgeAttributeSet = new HashSet<EdgeAttribute>();
                                edgeAttributeSet.add(getEdgeAttribute(name));
                                FeaturedEdge newFeaturedEdge = new FeaturedEdge(edge.getSourceNode(),
                                                        edge.getDestNode(),
                                                        edge.getDistance()).ofAttributes(edgeAttributeSet);

                                List<Edge> list = new ArrayList<Edge>();
                                list.addAll(ctx.getEdges());
                                Iterator<Edge> iterator = list.iterator();
                                int index = 0;
                                while(iterator.hasNext()){
                                    Edge ed = iterator.next();
                                    if (ed.getId().equals(selected)){
                                        iterator.remove();
                                        break;
                                    }
                                    index++;
                                }
                                list.add(index, newFeaturedEdge);
                                Set<Edge> newEdgeSet = new LinkedHashSet<Edge>();
                                newEdgeSet.addAll(list);
                                ctx.setEdges(newEdgeSet);
                            }
                        }
                    }
                } else {
                    // remove attribute
                    for (Edge edge : ctx.getEdges()) {
                        if (edge.getId().equals(selected)) {
                            try {
                                FeaturedEdge featuredEdge = (FeaturedEdge) edge;
                                for (EdgeAttribute eAttr : featuredEdge.getAttributes()){
                                    if (eAttr.name().equals(name)) {
                                        featuredEdge.getAttributes().remove(eAttr);
                                    }
                                }
                            } catch (ClassCastException e) {
                                // do nothing
                            }
                        }
                    }
                }
            }
        });
        coordY -= 50;
        return attribute;
    }

    private EdgeAttribute getEdgeAttribute(String name){
        for (EdgeAttribute e : ctx.getAttributes()) {
            if (e.name().equals(name)) {
                return e;
            }
        }
        return null;
    }

    private void unCheckAll() {
        for (CheckBox checkBox : attributes) {
            checkBox.setChecked(false);
        }
    }

    public EdgeAttributeDrawable() {
        this.attributes = new ArrayList<>();
        ctx = ScreensContextHolder.get();
        for (EdgeAttribute edgeAttribute : ctx.getAttributes()) {
            this.attributes.add(getNewCheckBox(edgeAttribute.name(), edgeAttribute));
        }
    }

    public void drawSelectedEdge(ShapeRenderer shapeRenderer){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.valueOf("69D6FA"));
        shapeRenderer.line(sourceX+11, sourceY+11, destX+11, destY+11);
        shapeRenderer.end();
    }

    public List<CheckBox> getAttributes() {
        return this.attributes;
    }

    public void refresh() {
        unCheckAll();
        for (Edge edge : ctx.getEdges()) {
            if (edge.getId().equals(selected)) {
                sourceX = edge.getSourceNode().getXCoord();
                sourceY = edge.getSourceNode().getYCoord();
                destX = edge.getDestNode().getXCoord();
                destY = edge.getDestNode().getYCoord();
                for (CheckBox checkBox : this.attributes) {
                    try {
                        FeaturedEdge featuredEdge = (FeaturedEdge) edge;
                        for (EdgeAttribute attr : featuredEdge.getAttributes()) {
                            String checkboxName = checkBox.getLabel().getText().toString();
                            String attrName = attr.name();
                            if (checkboxName.equals(attrName)) {
                                checkBox.setChecked(true);
                            }
                        }
                    } catch (ClassCastException e) {
                        checkBox.setChecked(false);
                    }
                }
            }
        }
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

}
