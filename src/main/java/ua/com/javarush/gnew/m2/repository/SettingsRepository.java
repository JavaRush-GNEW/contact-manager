package ua.com.javarush.gnew.m2.repository;

import java.io.IOException;
import java.util.Map;

public interface SettingsRepository {
    void save(Map<String, String> settings) throws IOException;
    Map<String, String> load() throws IOException;
    void saveSingleSetting(String key, String value) throws IOException;
    String loadSingleSetting(String key) throws IOException;
}
