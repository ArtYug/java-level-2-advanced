package com.geekbrains.homework3;

import java.util.*;
//                HomeWork lesson 3
//        1 Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и
//        вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
//        Посчитать, сколько раз встречается каждое слово.
//        2 Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и
//        телефонных номеров. В этот телефонный справочник с помощью метода add() можно
//        добавлять записи, а с помощью метода get() искать номер телефона по фамилии. Следует
//        учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
//        тогда при запросе такой фамилии должны выводиться все телефоны. Желательно не добавлять
//        лишний функционал (дополнительные поля (имя, отчество, адрес), взаимодействие с пользователем
//        через консоль и т.д). Консоль использовать только для вывода результатов проверки телефонного
//        справочника.


public class MainApp {
    public static void main(String[] args) {
        String[] strings = {"Panda", "Dog", "Dog", "Dog", "Dog", "Form", "Form", "Form", "Panda", "Panda", "Panda", "Panda"};
        countEachWordInArray(strings);

        HashMap<String, ArrayList<String>> map = new HashMap<>();
        Phonebook phoneBook = new Phonebook(map);
        phoneBook.addContact("Johnson", "4558509056479");
        phoneBook.addContact("Johnson", "455850963479");
        phoneBook.addContact("Johnson", "666888b90614");
        phoneBook.addContact("Bruce", "55k5559647");
        phoneBook.addContact("Jackson", "855l8559647");
        phoneBook.addContact("Jackson", "855559647");
        phoneBook.addContact("Jackson", "85555b9647");
        phoneBook.addContact("Joker", "55585l59647");
        phoneBook.addContact("Bask", "777-880-0020");
        phoneBook.addContact("Jackson", "777-850-0420");

        System.out.println(phoneBook.getContact("Johnson"));
    }

    public static void countEachWordInArray(String[] strings) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : strings) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            System.out.printf("%s : %d %n ", entry.getKey(), entry.getValue());
        }
    }
}
