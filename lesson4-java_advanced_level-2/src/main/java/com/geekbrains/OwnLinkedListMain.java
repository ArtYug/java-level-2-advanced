package com.geekbrains;
//                 HomeWork 4 java advanced level
//  1. Сделать добавление и удаление из односвязного списка по индексу

public class OwnLinkedListMain {
    public static void main(String[] args) {
        OwnLinkedList<Integer> integerOwnLinkedList = new OwnLinkedList<>();
        integerOwnLinkedList.addNode(150);
        integerOwnLinkedList.addNode(347);
        integerOwnLinkedList.addNode(111);
        integerOwnLinkedList.addNode(789);
        integerOwnLinkedList.addNode(222);
        integerOwnLinkedList.addNode(345);
        integerOwnLinkedList.addNode(346);


        Integer[] arr = {150, 347, 111};
        System.out.println(arr[1]);
        System.out.println();
        integerOwnLinkedList.deleteByIndex(1);
        integerOwnLinkedList.display();


        integerOwnLinkedList.addNumberByIndex(5, 5);
        integerOwnLinkedList.display();
    }
}
