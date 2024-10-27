package ua.com.javarush.gnew.m2.entity;

import lombok.Data;

import java.util.Arrays;
import java.util.Date;

@Data
public class Contact {
    /**
     * TODO: Add normal id generator.
     */

    private String id = String.valueOf((long) (Math.random()*10000));
    private String fullName;
    private String[] phones = new String[3];
    private String[] emails = new String[3];

    public Contact(String fullName, String[] phones, String[] emails) {
        this.fullName = fullName;
        this.phones = phones;
        this.emails = emails;
    }

    public Contact(String id, String fullName, String[] phones, String[] emails) {
        this.id = id;
        this.fullName = fullName;
        this.phones = phones;
        this.emails = emails;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phones=" + Arrays.toString(phones) +
                ", emails=" + Arrays.toString(emails) +
                '}';
    }
}
