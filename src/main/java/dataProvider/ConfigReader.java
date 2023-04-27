package dataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties propertiesObject;

    public static String getURL() {
        String configFilePath = System.getProperty("user.dir");
        Properties configProperties = ConfigReader.readPropertiesFile(configFilePath + "//configuration.properties");
        return configProperties.getProperty("baseURL");
    }

    public static Properties readPropertiesFile(String fileName) {

        try {
            FileInputStream fileStream = new FileInputStream(fileName);
            propertiesObject = new Properties();
            propertiesObject.load(fileStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertiesObject;
    }
}
