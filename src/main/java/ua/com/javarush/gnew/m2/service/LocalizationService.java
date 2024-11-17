package ua.com.javarush.gnew.m2.service;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationService {

    private ResourceBundle resourceBundle;

    public LocalizationService(Locale locale) {
        setLocale(locale);
    }

    public void setLocale(Locale locale) {
        this.resourceBundle = ResourceBundle.getBundle("messages", locale);
    }

    public String getMessage(String key) {
        try {
            return resourceBundle.getString(key);
        } catch (Exception e) {
            return "Message not found for key: " + key;
        }
    }
}