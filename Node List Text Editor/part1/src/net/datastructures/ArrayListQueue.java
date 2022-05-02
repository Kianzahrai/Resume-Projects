package net.datastructures;

import java.util.ArrayList;

public class ArrayListQueue<E> implements Queue<E> {

    private ArrayList<E> container;  
    private int end;

    public ArrayListQueue() {
        container = new ArrayList<E>();
        end = -1; 
    }

    @Override
    public int size() {
        return container.size();
    }

    @Override
    public boolean isEmpty() {
        return container.size() == 0;
    }

    @Override
    public E front() throws EmptyQueueException {
        if(isEmpty()){
            throw new EmptyQueueException("The Queue Is Empty!");
        }
        return container.get(end);
    }

    @Override
    public void enqueue(E element) {
        container.add(element);
        end++;

    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if(isEmpty()){
            throw new EmptyQueueException("The Queue Is Empty!");
        }
        E toBeReturned = container.get(0);
        container.remove(0);
        end--;
        return toBeReturned;
    }

}
