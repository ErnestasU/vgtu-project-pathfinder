package pathfinder.app.utils;

import com.google.common.base.Throwables;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static pathfinder.app.constants.GeneralConstant.PROP_NOT_LOADED;

/**
 * @author Ernestas
 * @since 11/16/2016
 */
public class PropertiesLoader {

    public static Properties load(String fileName, Class<?> clazz) {
        return load(fileName, clazz, false);
    }

    public static Properties load(String fileName, Class<?> clazz, boolean propagateException) {
        Properties props = new Properties();
        InputStream in = clazz.getClassLoader().getResourceAsStream(fileName);
        try {
            props.load(in);
        } catch (IOException e) {
            if (propagateException) {
                throw Throwables.propagate(e);
            }
            props.setProperty(PROP_NOT_LOADED, Boolean.TRUE.toString());
        }
        return props;
    }
}
