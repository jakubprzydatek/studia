package com.company;

import java.io.Serializable;

public class Element <T extends Comparable> implements Serializable {

    private Element<T> next;
    private T value;

    public Element<T> getNext()
    {
        return  this.next;
    }

    public void setNext(Element<T> next) {
        this.next = next;
    }


    public T getValue()
    {
        return value;
    }

    public Element(T value, Element<T> nextElem)
    {
        this.value = value;
        this.next = nextElem;
    }
}
