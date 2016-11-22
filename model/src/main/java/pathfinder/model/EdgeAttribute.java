package pathfinder.model;

/**
 * @author Ernestas
 * @since 2016.11.22
 */
public enum EdgeAttribute {

    RAINY(0.1f),
    SNOWY(0.1f),
    WINDY(0.1f),
    HIGHTEMP(0.1f),
    OFFROAD(0.1f),
    MOUNTAINOUS(0.1f);

    float factor;
    EdgeAttribute(float factor) {
        this.factor = factor;
    }

    public float getFactor() {
        return factor;
    }
}
