package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * Односвязный список
 */
public class MyLinkedList extends List implements Stack, Queue {

    private Node head;
    private Node tail;


    /**
     * private - используется для сокрытия этого класса от других.
     * Класс доступен только изнутри того, где он объявлен
     * <p>
     * static - позволяет использовать Node без создания экземпляра внешнего класса
     */
    private static class Node {
        Node prev;
        Node next;
        int val;

        Node(Node prev, Node next, int val) {
            this.prev = prev;
            this.next = next;
            this.val = val;
        }
    }

    @Override
    void add(int item) {

        if (head == null) {
            head = new Node(null, null, item);
            tail = head;
        } else {
            tail = new Node(tail, null, item);
        }

        positionOfLastElement++;
    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        checkIndex(idx);
        Node node = getNodeByIndex(idx);
        int value = node.val;
        deleteNode(idx);
        return value;
    }

    private void deleteNode(int idx) {
        Node node = getNodeByIndex(idx);
        if (idx == 0) {
            head = node.next;
            head.prev = null;
        }

        if (idx == positionOfLastElement) {
            tail = node.prev;
            tail.next = null;
        }

        if ((0 < idx) && (idx < positionOfLastElement)) {
            getNodeByIndex(idx - 1).next = getNodeByIndex(idx + 1);
            getNodeByIndex(idx + 1).prev = getNodeByIndex(idx - 1);
        }
        positionOfLastElement--;
    }


    @Override
    int get(int idx) throws NoSuchElementException {
        checkIndex(idx);
        return getNodeByIndex(idx).val;
    }

    @Override
    public void push(int value) {
        this.add(value);
    }

    @Override
    public int pop() {
        positionOfLastElement--;
        int value = tail.val;
        tail.prev.next = null;
        tail = tail.prev;
        return value;
    }

    @Override
    public void enqueue(int value) {
        head.prev = new Node(null, head, value);
        head = head.prev;
        positionOfLastElement++;

    }

    @Override
    public int dequeu() {
        positionOfLastElement--;
        int value = tail.val;
        tail.prev.next = null;
        tail = tail.prev;
        return value;
    }

    private Node getNodeByIndex(int idx) {
        Node result = head;
        for (int i = 0; i < idx; i++) {
            result = result.next;
        }
        return result;
    }
}
