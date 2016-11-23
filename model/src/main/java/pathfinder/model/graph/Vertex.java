package pathfinder.model.graph;

import java.util.Objects;

/**
 * Created by TT on 2016-11-18.
 *
 * Modified by Ernestas for PathFinder compliance
 */
public class Vertex {

    private String id;
    private int x;
    private int y;

    public Vertex(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public int getXCoord() {
        return x;
    }

    public int getYCoord() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        Vertex vertex = (Vertex) o;
        return getId().equals(vertex.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id='" + id + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}' + "\n";
    }
}
