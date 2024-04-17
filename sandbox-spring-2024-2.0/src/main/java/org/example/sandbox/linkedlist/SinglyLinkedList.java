package org.example.sandbox.linkedlist;

import java.util.StringJoiner;

public class SinglyLinkedList<E> implements LinkedList<E> {

    private Node head;
    private Node tail;

    public SinglyLinkedList() {
        head = null;
        tail = null;
    }

    // O(1)
    @Override
    public void addFirst(E element) {

        Node node = new Node(element, head);

        if (head == null) {
            head = node;
            tail = head;
        } else {
            head = node;
        }

    }

    // O(1)
    @Override
    public void addLast(E element) {

        Node node = new Node(element, null);
        if (tail == null) {
            tail = node;
            head = node;
        } else {
            tail.next = node; // 3
            tail = node; // 4
        }

    }

    // O(1)
    @Override
    public E pollFirst() {

        E element;
        if (head == null) {
            element = null;
        } else {

            element = head.element;

            if (head == tail) {
                head = null;
                tail = null;
            } else {
                Node next = head.next;
                head.next = null;
                head = next;
            }
        }

        return element;
    }

    // O(n)
    @Override
    public E pollLast() {

        E element;
        if (tail == null) {
            element = null;
        } else {

            element = tail.element;

            if (head == tail) {
                head = null;
                tail = null;
            } else {
                Node current = head;
                Node previous = head;
                while (current.next != null) {
                    previous = current;
                    current = current.next;
                }
                tail = previous;
                tail.next = null;
            }
        }

        return element;
    }

    // O(1)
    @Override
    public E peekFirst() {
        return head.element;
    }

    // O(1)
    @Override
    public E peekLast() {
        return tail.element;
    }

    // O(n)
    @Override
    public void clear() {

        Node current = head;
        while (current.next != null) { // O(n)
            Node next = current.next;
            current.next = null;
            current = next;
        }

        head = null;
        tail = null;
    }

    // O(n)
    @Override
    public boolean contains(E element) {

        boolean contains = false;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            if (current.element == element) {
                contains = true;
                break;
            }
            current = next;
        }
        return contains;
    }

    // O(n)
    public int size() {

        int counter = 0;
        if (head != null) {

            if (head == tail) {
                counter = 1;
            } else {
                Node current = head;
                while (current != null) {
                    counter++;
                    current = current.next;
                }
            }
        }
        return counter;
    }

    // O(n)
    public String toString() {

        StringBuilder builder = new StringBuilder("[");
        StringJoiner joiner = new StringJoiner(", ");
        Node current = head;
        if (current != null) {

            while (current != null) {
                joiner.add(current.element.toString());
                current = current.next;
            }
        }
        builder.append(joiner);
        builder.append("]");
        return builder.toString();
    }

    // O(1) - creating a new class
    private class Node {

        E element;
        Node next;

        public Node(E element, Node next) {
            this.element = element;
            this.next = next;
        }

    }
}
