package com.geekbrains;

public class OwnLinkedList<T> {
    private long size;
    private Node head;
    private Node tail;

    public OwnLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public void addNode(T data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public void display() {
        Node current = head;
        if (head == null) {
            System.out.println("Односвязный список пуст");
            return;
        }
        while (current != null) {
            System.out.println(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void display(int index) {
        if (index > size) {
            System.out.println("Размер списка больше чем заданный индекс");
            return;
        }
        Node current = head;
        for (int i = 1; i <= index; i++) {
            current = current.next;
        }
        System.out.println(current.data);
    }

    public void deleteByIndex(int index) {
        if (index > size) {
            System.out.println("Размер списка больше чем заданный индекс");
            return;
        }
        Node current = head;
        if (index != 0) {
            for (int i = 1; i <= index - 1; i++) {
                current = current.next;
            }
        } else {
            head = current.next;
        }
        current.next = current.next.next;
    }

    public void addNumberByIndex(int index, T data) {
        Node newNode = new Node(data);
        newNode.data = data;
        newNode.next = null;

        if (index > size) {
            System.out.println("Размер списка больше чем заданный индекс");
            return;
        }
        if (head != null && index == 0) {
            newNode.next = this.head;
            this.head = newNode;
            return;
        }
        Node current = this.head;
        Node previous = null;

        int i = 0;
        while (i < index) {
            previous = current;
            current = current.next;
            if (current == null) {
                break;
            }
            i++;

        }
        newNode.next = current;
        assert previous != null;
        previous.next = newNode;
    }

    private class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}
