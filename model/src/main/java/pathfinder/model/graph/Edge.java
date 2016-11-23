package pathfinder.model.graph;

import java.util.Objects;

/**
 * @author Ernestas
 * @since 11/14/2016
 */
public class Edge {

    private String id;
    private Vertex source;
    private Vertex destination;
    private int distance;

    public Edge( Vertex source, Vertex destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.distance = weight;
        this.id = source.getId() + "_" + destination.getId();
    }

    public String getId() {
        return id;
    }

    public Vertex getSourceNode() {
        return source;
    }

    public Vertex getDestNode() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o.getClass().isAssignableFrom(this.getClass()))) {
            return false;
        }
        Edge edge = (Edge) o;
        return edge.getId().equals(getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return  "\n" + "Edge{" +
                "id='" + id + '\'' +
                ", source=" + source +
                ", destination=" + destination +
                ", distance=" + distance +
                '}';
    }

}
