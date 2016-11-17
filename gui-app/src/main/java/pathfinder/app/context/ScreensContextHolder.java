package pathfinder.app.context;

/**
 * @author Ernestas
 * @since 11/16/2016
 */
public class ScreensContextHolder {

    private static UiContext context;

    public static synchronized UiContext get() {
        return context;
    }

    static synchronized void hold(UiContext context) {
        if (ScreensContextHolder.context != null) {
            throw new IllegalStateException("already holds!");
        }
        ScreensContextHolder.context = context;
    }
}
