package structures;

import java.util.Iterator;

public class OrderedList<T extends Comparable<T>> implements OrderedSequence<T>, Iterable<T> {


    private class Node<T extends Comparable<T>>
    {
        private Node<T> next;
        private T data;

        protected Node(T data)
        {
            if(data == null) throw new NullPointerException();
            this.data = data;
            this.next = null;
        }

        protected Node<T> getNext()
        {
            return this.next;
        }

        protected void setNext(Node<T> nextNode)
        {
            this.next = nextNode;
        }

        protected T getData()
        {
            return this.data;
        }

        @Override
        public String toString() {
            return getData().toString();
        }
    }

    private class CustomIterator<T extends Comparable<T>> implements Iterator<T>
    {
        protected OrderedList<T> orderedList;
        protected OrderedList<T>.Node<T> elem;
        protected OrderedList<T>.Node<T> prev;

        protected CustomIterator(OrderedList<T> orderedList)
        {
            if(orderedList == null) throw new NullPointerException();
            this.orderedList = orderedList;
            this.elem = null;
            this.prev = null;
        }

        @Override
        public boolean hasNext() {
            if(elem == null && orderedList.getStart() != null)
            {
                return true;
            }
            return elem.getNext() != null;
        }

        @Override
        public T next() {
            if(elem == null)
            {
                elem = orderedList.getStart();
                return elem.getData();
            }
            prev = elem;
            elem = elem.getNext();
            return elem.getData();
        }

        @Override
        public void remove() {
            if(prev == null && elem != null)
            {
                orderedList.setStart(elem.getNext());
            }
            else if(elem != null)
            {
                prev.setNext(elem.getNext());
            }
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new CustomIterator<>(this);
    }

    private Node<T> start;

    public OrderedList()
    {
        this.start = null;
    }

    public void insert(T el) throws ExistsInListException {
        if(el == null) throw new NullPointerException();
        if(this.start == null)
        {
            this.start = new Node<T>(el);
        }else{
            if(this.search(el)) throw new ExistsInListException();
            Node<T> current = this.start;
            Node<T> elemToAdd = new Node<T>(el);
            if(this.start.getData().compareTo(elemToAdd.getData()) > 0){
                elemToAdd.setNext(current);
                this.start = elemToAdd;
            }else{
                while(current.getNext()!=null && (current.getNext().getData().compareTo(elemToAdd.getData())<0))
                {
                    current = current.getNext();
                }
                elemToAdd.setNext(current.getNext());
                current.setNext(elemToAdd);
            }
        }
    }

    public void remove(T el) {
        if(!search(el) || this.start == null){
            return;
        }
        if(this.start.getData().equals(el))
        {
            this.start = this.start.getNext();
            return;
        }
        else
        {
            Node<T> current = this.start;
            while(current.getData() != null && !current.getNext().getData().equals(el))
            {
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());
        }
        
    }

    public T min() throws EmptyListException {
        if(this.start != null){
            return this.start.getData();
        }
        throw new EmptyListException();
    }

    public T max() throws EmptyListException {
        if(this.start!=null){
            Node<T> current = this.start;
            while(current.getNext() != null){
                current = current.getNext();
            }
            return current.getData();
        }
        throw new EmptyListException();
    }

    public T at(int pos) throws EmptyListException {
        if(pos < 0) throw new IndexOutOfBoundsException();
        if(this.start!=null){
            Node<T> current = this.start;
            int i = 0;
            while(i<pos){
                i+=1;
                current = current.getNext();
                if(current == null) throw new IndexOutOfBoundsException();
            }
            return current.getData();
        }
        throw new EmptyListException();
    }

    public boolean search(T el) {
        if(this.start == null) return false;
        Node<T> current = this.start;
        do{
            if(current.getData().equals(el)) return true;
            current = current.getNext();
        }while(current != null);

        return false;
    }

    public int index(T el) {
        if(this.start == null || !this.search(el)) return -1;
        Node<T> current = this.start;
        int i = 0;
        while(current.getNext() != null){
            if(current.getData().equals(el)) return i;
            current = current.getNext();
            i+=1;
        }
        return i;
    }

    public Node<T> getStart()
    {
        return this.start;
    }
    public void setStart(Node<T> elem){
        this.start = elem;
    }

    @Override
    public String toString() {
        StringBuilder stringToReturn = new StringBuilder();
        if(this.start == null) return stringToReturn.toString();
        Node<T> current = this.start;
        while(current.getNext() != null){
            stringToReturn.append(current.getData()).append(", ");
            current = current.getNext();
        }
        stringToReturn.append(current.getData());
        return stringToReturn.toString();
    }
}
