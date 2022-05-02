package textEditor.core.structures;

import java.util.EmptyStackException;
import java.util.Stack;

public class LeakyStack<E> extends AbstractStack<E> {

    private Node<E> flist; // head
    private Node<E> llist;  // tail
    private int nodesCount; 

    public LeakyStack() {
        super();
        flist = new Node<E>();
        llist = new Node<E>(); 
        nodesCount = 0;
    }

    @Override
    public void push(E e) {
        Node<E> toBePushed = new Node<E>(e);
        if (nodesCount >= super.maxUndos) {
            Node<E> temporaryNode = flist;
            while(temporaryNode.getNext()!=null){
                temporaryNode.setElement(temporaryNode.getNext().getElement());
                temporaryNode = temporaryNode.getNext();
            }
            temporaryNode.getPrevious().setNext(null);
            temporaryNode.setPrevious(null);
            
        } 

        if (flist.getElement() == null) {
            flist.setElement(e);
        } else {
            Node<E> temporary = flist;
            while (temporary.getNext() != null) {
                temporary = temporary.getNext();
            }
            temporary.setNext(toBePushed);
            toBePushed.setPrevious(temporary);
            llist = toBePushed;
        } 

        nodesCount++;
    }

    @Override
    public E top() throws EmptyStackException {
        if (llist.getElement() == null) {
            return flist.getElement();
        }
        return llist.getElement();
    }

    @Override
    public E pop() throws EmptyStackException {

        if (llist.getElement() == null) {
            Node<E> toBePopped = new Node<E>();
            toBePopped.setElement(flist.getElement());
            flist.setElement(null);
            nodesCount--;
            return toBePopped.getElement();
        } else {
            Node<E> toBePopped = new Node<E>();
            toBePopped.setElement(llist.getElement());
            llist = llist.getPrevious();
            llist.getNext().setPrevious(null);
            llist.setNext(null);
            nodesCount--;

            return toBePopped.getElement();

        }

    }

    @Override
    public int size() {
        return nodesCount;
    }

    @Override
    public boolean isEmpty() {
        return flist.getElement() == llist.getElement();
    }

}

// Extra stuff for me

            // toBePopped.setElement(llist.getElement());
            // llist = llist.getPrevious();
            // nodesCount--;