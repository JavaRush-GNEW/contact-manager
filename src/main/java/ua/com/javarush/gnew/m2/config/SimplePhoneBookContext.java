package ua.com.javarush.gnew.m2.config;

import lombok.Data;
import ua.com.javarush.gnew.m2.service.MocPhoneBookService;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;

@Data
public class SimplePhoneBookContext implements PhoneBookContext{
    String user;
    public SimplePhoneBookContext() {
        this.user = "user";
    }

    public PhoneBookInterface getPhoneBookService(){
        return new MocPhoneBookService(this);
    }

}
