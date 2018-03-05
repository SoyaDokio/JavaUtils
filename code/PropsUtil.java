package cn.soyadokio.utility;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class PropsUtil {
    
    private static final String CONFIG_PATH = "config/configfile.properties";
    private static Properties props = new Properties();
    
    private static void loadProperty() {
        Reader reader = null;
        try {
            reader = new InputStreamReader(PropsUtil.class.getClassLoader().getResourceAsStream(CONFIG_PATH), "UTF-8");
            props.load(reader);
            String confPath = (String) props.get("CONFIG_FILE_PATH");
            reader = new InputStreamReader(PropsUtil.class.getClassLoader().getResourceAsStream(confPath), "UTF-8");
            props.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static String getString(String key) {
        if (props.get(key) == null) {
            loadProperty();
        }
        return (String) props.get(key);
    }
    
    public static boolean getBoolean(String key) {
        if (props.get(key) == null) {
            loadProperty();
        }
        String val = (String) props.get(key);
        return Boolean.parseBoolean(val);
    }
    
    public static int getInt(String key) {
        if (props.get(key) == null) {
            loadProperty();
        }
        String val = (String) props.get(key);
        return Integer.parseInt(val);
    }
    
}
