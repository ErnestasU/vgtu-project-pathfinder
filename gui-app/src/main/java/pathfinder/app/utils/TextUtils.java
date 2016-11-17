package pathfinder.app.utils;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static pathfinder.app.constants.GeneralConstant.DEFAULT_SYSTEM_SEPARATOR;

/**
 * @author Ernestas
 * @since 11/16/2016
 */
public class TextUtils {

    public static String joinPath(String first, String second) {
        return first + DEFAULT_SYSTEM_SEPARATOR + second;
    }

    public static String joinPath(String first, String second, String third) {
        StringJoiner joiner = new StringJoiner(DEFAULT_SYSTEM_SEPARATOR);
        return joiner.add(first).add(second).add(third).toString();
    }

    public static String joinPath(String first, String... parts) {
        return first + DEFAULT_SYSTEM_SEPARATOR + Arrays.stream(parts).collect(Collectors.joining(DEFAULT_SYSTEM_SEPARATOR));
    }
}
