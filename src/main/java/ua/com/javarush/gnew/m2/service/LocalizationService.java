package ua.com.javarush.gnew.m2.service;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationService {

    private ResourceBundle resourceBundle;

    public LocalizationService(Locale locale) {
        setLocale(locale);
    }


    public void setLocale(Locale locale) {
        this.resourceBundle = ResourceBundle.getBundle("Localization", locale);
    }


    public String getMessage(String key) {
        try {
            String message = resourceBundle.getString(key);
            return new String(message.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "Message not found for key: " + key;
        }
    }
}