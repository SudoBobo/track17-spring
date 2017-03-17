package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * Односвязный список
 */
public class MyLinkedList extends List implements Stack, Queue {

    private Node head = null;
    private Node tail = null;


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
        } else if ((tail == null) && (head != null)) {
            tail = new Node(head, null, item);
            head.next = tail;
        } else if ((tail != null) && (head != null)) {
            Node newTail = new Node(tail, null, item);
            tail.next = newTail;
            tail = newTail;
        }
        positionOfLastElement++;
    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        checkIndex(idx);
        Node node = getNode(idx);
        int value = node.val;
        deleteNode(node);
        return value;
    }

    private void deleteNode(Node node) {
        if ((node == head) && (tail == null)) {
            head = null;
        } else if ((node == head) && (tail != null) && (positionOfLastElement == 1)) {
            head = tail;
            tail = null;
        } else if ((node == head) && (tail != null) && (1 < positionOfLastElement)) {
            head = head.next;
            head.prev = null;
        } else if ((node != head) && (node != tail)) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        } else if ((node == tail) && (positionOfLastElement == 1)) {
            tail = null;
        } else if ((node == tail) && (1 < positionOfLastElement)) {
            tail = tail.prev;
            tail.next = null;
        }
        positionOfLastElement--;
    }


    @Override
    int get(int idx) throws NoSuchElementException {
        checkIndex(idx);
        return getNode(idx).val;
    }

    @Override
    public void push(int value) {
        this.add(value);
    }

    @Override
    public int pop() {
        positionOfLastElement--;
        int value = tail.val;
        tail = tail.prev;
        tail.next = null;
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
        tail = tail.prev;
        tail.next = null;
        return value;
    }

    private Node getNode(int idx) {
        checkIndex(idx);
        Node result = head;
        for (int i = 0; i < idx; i++) {
            result = result.next;
        }
        return result;
    }
}
