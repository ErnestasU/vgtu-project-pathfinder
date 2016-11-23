package pathfinder.model.graph;

/**
 * @author Ernestas
 * @since 2016.11.22
 */
public enum EdgeAttribute {

    RAINY(0.1f),
    SNOWY(0.2f),
    WINDY(0.1f),
    HIGHTEMP(0.1f),
    OFFROAD(0.2f),
    MOUNTAINOUS(0.1f),
    HIGHTRAFFIC(0.2f);

    float factor;
    EdgeAttribute(float factor) {
        this.factor = factor;
    }

    public float getFactor() {
        return factor;
    }
}
