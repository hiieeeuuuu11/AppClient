package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigBTL {
    private static final String CONFIG_FILE_NAME = "E:/FinalApp/AppClient (1)/config.properties";
    private static Properties configProperties;

    static {
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_NAME)) {
            configProperties = new Properties();
            configProperties.load(fis);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public static int getSkPortLogin() {
        return Integer.parseInt(configProperties.getProperty("sk.portlogin"));
    }

    public static int getSkPortSignup() {
        return Integer.parseInt(configProperties.getProperty("sk.portsignup"));
    }

    public static int getSkPortChat() {
        return Integer.parseInt(configProperties.getProperty("sk.portchat"));
    }
}