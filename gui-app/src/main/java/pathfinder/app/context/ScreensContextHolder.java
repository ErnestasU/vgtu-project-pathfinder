package pathfinder.app.context;

/**
 * @author Ernestas
 * @since 11/16/2016
 */
public class ScreensContextHolder {

    private static GraphUiContext context;

    public static synchronized GraphUiContext get() {
        return context;
    }

    static synchronized void hold(GraphUiContext context) {
        if (ScreensContextHolder.context != null) {
            throw new IllegalStateException("already holds!");
        }
        ScreensContextHolder.context = context;
    }
}
