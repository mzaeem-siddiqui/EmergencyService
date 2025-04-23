package classes;

import interfaces.I_List;

public class List<T> implements I_List<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    // Constructor to create an empty list
    public List() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Constructor to create a list with an initial head node
    public List(Node<T> head) {
        this.head = head;
        this.tail = head;
        this.size = head != null ? 1 : 0;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    public boolean append(Node<T> node) {
        if (node == null) return false;
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        }
        size++;
        return true;
    }

    public boolean insert(int index, Node<T> node) {
        if (node == null || index < 0 || index > size) return false;
        
        if (index == size) {
            return append(node);
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        node.setNext(current);
        node.setPrev(current.getPrev());
        
        if (current.getPrev() != null) {
            current.getPrev().setNext(node);
        } else {
            head = node;
        }
        
        current.setPrev(node);
        size++;
        return true;
    }

    public boolean remove(int index) {
        if (index < 0 || index >= size) return false;
        
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        
        if (current.getPrev() != null) {
            current.getPrev().setNext(current.getNext());
        } else {
            head = current.getNext();
        }
        
        if (current.getNext() != null) {
            current.getNext().setPrev(current.getPrev());
        } else {
            tail = current.getPrev();
        }
        
        size--;
        return true;
    }

    public boolean remove(T content) {
        Node<T> current = head;
        while (current != null) {
            if (current.getContent().equals(content)) {
                if (current.getPrev() != null) {
                    current.getPrev().setNext(current.getNext());
                } else {
                    head = current.getNext();
                }
                
                if (current.getNext() != null) {
                    current.getNext().setPrev(current.getPrev());
                } else {
                    tail = current.getPrev();
                }
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public Node<T> get(int index) {
        if (index < 0 || index >= size) return null;
        
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    //This method was created with the help of ChatGPT.
    public boolean swap(int indexOne, int indexTwo) {
        if (indexOne < 0 || indexTwo < 0 || indexOne >= size || indexTwo >= size || indexOne == indexTwo) {
            return false;
        }

        Node<T> nodeOne = get(indexOne);
        Node<T> nodeTwo = get(indexTwo);

        // Handle swapping of adjacent nodes
        if (Math.abs(indexOne - indexTwo) == 1) {
            // If indexOne is before indexTwo, we need to adjust the connections accordingly
            if (indexOne < indexTwo) {
                nodeOne.setNext(nodeTwo.getNext());
                if (nodeTwo.getNext() != null) {
                    nodeTwo.getNext().setPrev(nodeOne);
                }
                nodeTwo.setPrev(nodeOne.getPrev());
                if (nodeOne.getPrev() != null) {
                    nodeOne.getPrev().setNext(nodeTwo);
                } else {
                    head = nodeTwo;
                }
                nodeTwo.setNext(nodeOne);
                nodeOne.setPrev(nodeTwo);
            } else {
                nodeTwo.setNext(nodeOne.getNext());
                if (nodeOne.getNext() != null) {
                    nodeOne.getNext().setPrev(nodeTwo);
                }
                nodeOne.setPrev(nodeTwo.getPrev());
                if (nodeTwo.getPrev() != null) {
                    nodeTwo.getPrev().setNext(nodeOne);
                } else {
                    head = nodeOne;
                }
                nodeOne.setNext(nodeTwo);
                nodeTwo.setPrev(nodeOne);
            }
        } else {
            // If they are not adjacent, we can safely swap the prev and next pointers
            Node<T> tempPrev = nodeOne.getPrev();
            Node<T> tempNext = nodeOne.getNext();

            nodeOne.setPrev(nodeTwo.getPrev());
            nodeOne.setNext(nodeTwo.getNext());
            nodeTwo.setPrev(tempPrev);
            nodeTwo.setNext(tempNext);

            if (nodeOne.getPrev() != null) {
                nodeOne.getPrev().setNext(nodeOne);
            } else {
                head = nodeOne;
            }
            if (nodeOne.getNext() != null) {
                nodeOne.getNext().setPrev(nodeOne);
            } else {
                tail = nodeOne;
            }

            if (nodeTwo.getPrev() != null) {
                nodeTwo.getPrev().setNext(nodeTwo);
            } else {
                head = nodeTwo;
            }
            if (nodeTwo.getNext() != null) {
                nodeTwo.getNext().setPrev(nodeTwo);
            } else {
                tail = nodeTwo;
            }
        }

        return true;
    }

}
