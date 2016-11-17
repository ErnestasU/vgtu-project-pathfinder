package pathfinder.app.bootstrap;

/**
 * @author Ernestas
 * @since 11/16/2016
 *
 * <p>
 *     Main class which start bootstrap class
 * </p>
 * @see PathFinderStarter
 */
public class PathFinderDesktop {

    static {
        System.setProperty("separator", "/");
    }

    public static void main(String[] args) {
        PathFinderStarter.start();
    }
}
