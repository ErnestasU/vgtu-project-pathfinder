package command;

/**
 * @author Ernestas
 * @since 2016.11.22
 */
public interface Command<T> {

    void execute(T target);
}
