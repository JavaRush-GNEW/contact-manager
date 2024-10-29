package ua.com.javarush.gnew.m2.config;

import ua.com.javarush.gnew.m2.service.PhoneBookInterface;

public interface PhoneBookContext {
     PhoneBookInterface getPhoneBookService();
    String getUser();
}
