package ua.com.javarush.gnew.m2.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileSettingsRepository implements SettingsRepository {

  ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

  @Override
  public void save(Map<String, String> settings) throws IOException {
    objectMapper.writeValue(new File("settings.st"), settings);
  }

  @Override
  public Map<String, String> load() {
    try {
      return objectMapper.readValue(
          new File("settings.st"), new TypeReference<Map<String, String>>() {});
    } catch (IOException e) {
      return new HashMap<>();
    }
  }

  @Override
  public void saveSingleSetting(String key, String value) throws IOException {
    Map<String, String> settings = load();
    settings.put(key, value);
    save(settings);
  }

  @Override
  public String loadSingleSetting(String key) throws IOException {
    return load().get(key);
  }
}
