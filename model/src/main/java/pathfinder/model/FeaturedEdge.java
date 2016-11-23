package pathfinder.model;

import java.util.Arrays;
import java.util.Set;

/**
 * @author Ernestas
 * @since 2016.11.22
 */
public class FeaturedEdge extends Edge {

    private Set<EdgeAttribute> attributes;

    public FeaturedEdge(Vertex source, Vertex destination, int weight) {
        super(source, destination, weight);
    }
    public FeaturedEdge ofAttributes(Set<EdgeAttribute> attrs) {
        this.attributes = attrs;
        return this;
    }

    public Set<EdgeAttribute> getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return super.toString() + " \n FeaturedEdge{" +
                "attributes=" + Arrays.toString(attributes.toArray(new EdgeAttribute[attributes.size()])) +
                '}';
    }
}
