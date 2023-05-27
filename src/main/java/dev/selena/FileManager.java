package dev.selena;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;

public class FileManager {


    private static FileManager instance;

    public FileManager() {
        FileManager.instance = this;
    }


    public static File file(String parent, String file) {

        return new File(folderPath(parent) + file);
    }

    public static String folderPath(String path) {
        File file = new File("plugins" + File.separator + GlitchAPI.getPlugin().getName(), path);
        if (!file.exists())
            file.mkdirs();
        return file.getPath() + File.separator;
    }


    public static <T> T loadFile(Class<T> clazz, File file) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return ConfigLoader.loadConfig(clazz, file);
    }

    public static FileManager get() {
        return instance;
    }
}
