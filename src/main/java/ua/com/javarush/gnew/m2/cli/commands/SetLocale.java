package ua.com.javarush.gnew.m2.cli.commands;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JacksonInject;
import picocli.CommandLine;
import ua.com.javarush.gnew.m2.cli.CliCommand;
import ua.com.javarush.gnew.m2.configuration.PhoneBookContext;
import ua.com.javarush.gnew.m2.service.LocalizationService;
import ua.com.javarush.gnew.m2.service.SettingsServiceInterface;

import javax.inject.Inject;

@CommandLine.Command(
    name = "locale",
    aliases = {"-l", "--locale"},
    mixinStandardHelpOptions = true,
    description = "Please, choose yours language")
public class SetLocale implements CliCommand {

  private final SettingsServiceInterface settingsService =
          PhoneBookContext.getBean(SettingsServiceInterface.class);

LocalizationService localizationService = new LocalizationService(Locale.getDefault());

  @CommandLine.Parameters(index = "0", description = "language", arity = "1")
  private String newLocale;


    @Override
    public Integer call() {
      try {
        Locale locale = new Locale(newLocale);
        if (!Arrays.asList(Locale.getAvailableLocales()).contains(locale)) {
          System.err.println(localizationService.getMessage("error.dontUse.notfound").replace("{0}", newLocale));
          return 1;
        }
        settingsService.setLocale(newLocale);
        localizationService.setLocale(new Locale(newLocale));
        System.out.println(localizationService.getMessage("success.locale.changed").replace("{0}", newLocale));
        System.out.println(localizationService.getMessage("locale.current").replace("{0}", newLocale));
        return 0;

      } catch (IOException e) {
        System.err.println(localizationService.getMessage("error.command.notfound").replace("{0}", newLocale));
        return 1;
      }
    }
}
