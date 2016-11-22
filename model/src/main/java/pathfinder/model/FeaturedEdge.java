package pathfinder.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ernestas
 * @since 2016.11.22
 */
public class FeaturedEdge extends Edge {

    private Set<EdgeAttribute> attributes = new HashSet<>();

    public FeaturedEdge(Vertex source, Vertex destination, int weight) {
        super(source, destination, weight);
    }
    public FeaturedEdge addAttributes(EdgeAttribute attr) {
        this.attributes.add(attr);
        return this;
    }

    public Set<EdgeAttribute> getAttributes() {
        return attributes;
    }
}
