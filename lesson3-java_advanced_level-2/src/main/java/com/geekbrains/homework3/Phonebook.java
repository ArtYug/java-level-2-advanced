package com.geekbrains.homework3;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Phonebook {

    private final HashMap<String, ArrayList<String>> map;

    public Phonebook(HashMap<String, ArrayList<String>> map) {
        this.map = map;
    }

    public void addContact(String surname, String number) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (onlyDigits(number)) {
            if (!map.containsKey(surname) || map.containsValue(number)) {
                arrayList.add(number);
                map.put(surname, arrayList);
                System.out.println("added to phonebook " + number + " " + surname);
            } else {
                arrayList = map.get(surname);
                arrayList.add(number);
                System.out.println("added to phonebook " + surname + " " + number + " ");
            }
        } else {
            System.out.println("phone numbers must contain only numbers" + " " + number + " " + surname);
        }
    }

    public String getContact(String surname) {
        return surname + " " + map.get(surname);
    }

    public static boolean onlyDigits(String str) {
        String regex = "[0-9-]+";
        Pattern p = Pattern.compile(regex);
        if (str == null) {
            return false;
        }

        Matcher m = p.matcher(str);

        return m.matches();
    }
}

