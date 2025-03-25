package org.firstinspires.ftc.teamcode.tests.StealTheMoon.EDITH;

public class QueueFast<E> {
    E[] queue;
    int first = 0;
    int last = 0;

    public QueueFast(int maxSize) {
        queue = (E[]) new Object[maxSize];
    }

    public boolean isEmpty() {
        return first == last;
    }

    public boolean isFull() {
        return first == ((last + 1) % queue.length);
    }

    public int enqueue(E item) {
        if (isFull()) {
            return -1;
        }
        queue[last] = item;
        last++;
        last %= queue.length;
        return 0;
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E ret = queue[first];
        first++;
        first %= queue.length;
        return ret;
    }

    public static void main(String[] args) {
        QueueFast<Integer> queue = new QueueFast<Integer>(10);
        // Some testing to make sure wrap-around works properly in the circular queue
        for (int i = 0; i < 9; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 9; i++) {
            System.out.println(queue.dequeue());
        }

        for (int i = 0; i < 9; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 9; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
