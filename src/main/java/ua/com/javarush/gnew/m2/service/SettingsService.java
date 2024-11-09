package ua.com.javarush.gnew.m2.service;

import java.io.IOException;
import ua.com.javarush.gnew.m2.configuration.PhoneBookContext;
import ua.com.javarush.gnew.m2.repository.SettingsRepository;

public class SettingsService implements SettingsServiceInterface {
  private final SettingsRepository settingsRepository =
      PhoneBookContext.getBean(SettingsRepository.class);
  ;

  @Override
  public void setUser(String user) throws IOException {

    settingsRepository.saveSingleSetting("user", user);
  }

  @Override
  public void setLocale(String language) throws IOException {
    if (language.matches("(?i)\\b(?:ua|ukr|ukrainian)\\b")) {
      settingsRepository.saveSingleSetting("language", "uk");
      settingsRepository.saveSingleSetting("country", "UA");
    } else if (language.matches("(?i)\\b(?:ru|rus|orc|orcian)\\b")) {
      settingsRepository.saveSingleSetting("language", "ru");
      settingsRepository.saveSingleSetting("country", "RU");
    } else {
      settingsRepository.saveSingleSetting("language", "en");
      settingsRepository.saveSingleSetting("country", "US");
    }
  }
}
