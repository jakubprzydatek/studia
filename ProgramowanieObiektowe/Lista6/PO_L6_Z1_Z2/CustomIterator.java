package com.company;

import java.util.Iterator;
import java.util.function.Consumer;

public class CustomIterator<T extends Comparable<T>> implements Iterator<T> {

    private OrderList<T> orderList;
    private Element<T> element;
    private  Element<T> previous;

    public CustomIterator(OrderList<T> orderList) {
        this.orderList = orderList;
        this.element = null;
        this.previous = this.element;
    }

    @Override
    public boolean hasNext() {
        if(element == null && orderList.getRoot() != null)
        {
            return true;
        }
            return element.getNext() != null;

    }

    @Override
    public T next() {
        if(element == null)
        {
            element = orderList.getRoot();
            return element.getValue();
        }
        previous = element;
        element = element.getNext();
        return element.getValue();
    }

    @Override
    public void remove()
    {
        if(previous == null && element != null)
        {
            orderList.setRoot(element.getNext());
            //element=element.getNext();
        }
        else if(element != null)
        {
            previous.setNext(element.getNext());
        }
    }

}
