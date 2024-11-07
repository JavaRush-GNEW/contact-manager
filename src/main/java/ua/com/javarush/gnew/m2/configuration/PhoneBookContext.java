package ua.com.javarush.gnew.m2.configuration;

import java.util.HashMap;
import java.util.Map;

public class PhoneBookContext {
    private static Map<Class<?>,Object> context = new HashMap<>();

    private static void create(){

    }
    private static void addBean(Object object){
        context.put(object.getClass(),object);
    }


}
