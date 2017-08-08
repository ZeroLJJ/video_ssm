package util;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 配置文件加载工具类（单例）
 * Created by Zero on 2017/4/13.
 */
public final class ResourceLoaderUtil {

    private static ResourceLoaderUtil loader = new ResourceLoaderUtil();
    private static Map<String, Properties> loaderMap = new HashMap<String, Properties>();

    private ResourceLoaderUtil() {
    }

    public static ResourceLoaderUtil getInstance() {
        return loader;
    }

    public Properties getPropFromProperties(String fileName) throws Exception {

        Properties prop = loaderMap.get(fileName);
        if (prop != null) {
            return prop;
        }
        String filePath = null;
        String configPath = System.getProperty("configurePath");

        if (configPath == null) {
            filePath = this.getClass().getClassLoader().getResource(fileName).getPath();
        } else {
            filePath = configPath + "/" + fileName;
        }
        prop = new Properties();
        prop.load(new FileInputStream(new File(filePath)));

        loaderMap.put(fileName, prop);
        return prop;
    }
}