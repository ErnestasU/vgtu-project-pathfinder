package math;

import java.util.List;

import pathfinder.model.Edge;
import pathfinder.model.EdgeAttribute;
import pathfinder.model.FeaturedEdge;

/**
 * @author Ernestas
 * @since 2016.11.23
 */
public class CaloriesCalculator {

    private List<Edge> target;

    public CaloriesCalculator(List<Edge> target) {
        this.target = target;
    }

    public int calculate() {
        int calories = 0;
        for (Edge edge : target) {
            int dist = edge.getDistance() * 100;
            if (edge instanceof FeaturedEdge) {
                FeaturedEdge featuredEdge = (FeaturedEdge)edge;
                int factor = 1;
                for (EdgeAttribute attr: featuredEdge.getAttributes()) {
                    factor += attr.getFactor();
                }
                dist *= factor;
            }
            calories += dist;
        }
        return calories;
    }
}
