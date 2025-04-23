package classes;

import interfaces.I_Node;

public class Node<T> implements I_Node<T> {

    private T content;
    private Node<T> prev;
    private Node<T> next;

    public Node() {
        this.content = null;
        this.prev = null;
        this.next = null;
    }

    public Node(T content) {
        this.content = content;
        this.prev = null;
        this.next = null;
    }

    public T getContent() {
        return this.content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Node<T> getPrev() {
        return this.prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
