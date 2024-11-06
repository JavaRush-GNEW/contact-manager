package ua.com.javarush.gnew.m2.service;

import java.io.IOException;

public interface SettingsServiceInterface {

  void setUser(String user) throws IOException;

  void setLocale(String language) throws IOException;
}
