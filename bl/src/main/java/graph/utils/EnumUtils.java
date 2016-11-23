package graph.utils;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.stream.Collectors;

/**
 * @author Ernestas
 * @since 2016.11.23
 */
public class EnumUtils {

    public static <T extends Enum<T>> EnumSet<T> toSet(Class<T> clazz, String... values) {
        return Arrays.stream(values).map(s -> Enum.valueOf(clazz, s))
                                    .collect(Collectors.toCollection(() -> EnumSet.noneOf(clazz)));
    }
}
