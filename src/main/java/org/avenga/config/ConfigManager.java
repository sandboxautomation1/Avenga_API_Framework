package org.avenga.config;

public class ConfigManager {

    public static String getBaseUrl() {
        return getProperty("BASE_URL", "https://fakerestapi.azurewebsites.net");
    }

    public static String getProperty(String key, String defaultValue) {

        // 1. Command line (highest priority)
        String value = System.getProperty(key);

        if (value == null || value.isEmpty()) {
            // 2. Environment variable
            value = System.getenv(key);
        }

        if (value == null || value.isEmpty()) {
            // 3. Default fallback
            value = defaultValue;
        }

        return value;
    }
}
