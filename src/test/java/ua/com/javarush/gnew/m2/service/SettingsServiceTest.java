package ua.com.javarush.gnew.m2.service;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.javarush.gnew.m2.configuration.PhoneBookContext;
import ua.com.javarush.gnew.m2.repository.SettingsRepository;

@ExtendWith(MockitoExtension.class)
class SettingsServiceTest {
  SettingsServiceInterface settingsService;
  @Mock SettingsRepository mockSettingsRepository;

  @BeforeEach
  void setup() {
    try (MockedStatic<PhoneBookContext> phone = Mockito.mockStatic(PhoneBookContext.class)) {
      phone
          .when(
              () -> {
                PhoneBookContext.getBean(SettingsRepository.class);
              })
          .thenReturn(mockSettingsRepository);
      settingsService = new SettingsService();
    }
  }

  @Test
  void setUser() throws IOException {
    String testUserName = "tester";
    settingsService.setUser(testUserName);
    Mockito.verify(mockSettingsRepository, Mockito.times(1))
        .saveSingleSetting("user", testUserName);
  }

  @ParameterizedTest
  @CsvSource({
    "ua, uk, UA",
    "ukr, uk, UA",
    "ukrainian, uk, UA",
    "ru, ru, RU",
    "rus, ru, RU",
    "orc, ru, RU",
    "orcian, ru, RU",
    "fr, en, US",
    "de, en, US"
  })
  void testSetLocale(String inputLanguage, String expectedLanguage, String expectedCountry)
      throws IOException {
    settingsService.setLocale(inputLanguage);
    Mockito.verify(mockSettingsRepository).saveSingleSetting("language", expectedLanguage);
    Mockito.verify(mockSettingsRepository).saveSingleSetting("country", expectedCountry);
  }
}
