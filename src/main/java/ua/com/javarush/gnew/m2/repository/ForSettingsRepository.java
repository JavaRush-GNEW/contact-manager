package ua.com.javarush.gnew.m2.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ForSettingsRepository implements SettingsRepository {

    @Override
    public void save(Map<String, String> settings) throws IOException {
    }

    @Override
    public Map<String, String> load() throws IOException {
        return new HashMap<>();
    }

    @Override
    public void saveSingleSetting(String key, String value) throws IOException {
    }

    @Override
    public String loadSingleSetting(String key) throws IOException {
        return "";
    }
}
