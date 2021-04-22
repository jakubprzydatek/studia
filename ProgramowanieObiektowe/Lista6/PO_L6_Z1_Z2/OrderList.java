package com.company;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

public class OrderList<T extends Comparable<T>> implements Collection<T>, Iterable<T>, Serializable {

    private Element<T> root = null;

    public Element<T> getRoot()
    {
        return this.root;
    }

    public void setRoot(Element<T> elemToSet)
    {
        this.root = elemToSet;
    }

    @Override
    public int size()
    {
        if(root == null)
        {
            return 0;
        }

        Element<T> current = root;
        int number = 0;
        do {
            number++;
            current = current.getNext();
        }while(current != null);
        return number;
    }

    @Override
    public boolean isEmpty()
    {
        return root == null;
    }

    @Override
    public boolean contains(Object o) {
        if(root == null) return false;
        T valueToFind;
        try{
            valueToFind = (T) o;
        }
        catch(Exception e)
        {
            throw new ClassCastException();
        }
        Element<T> current = root;
        do {
            if(current.getValue().equals(valueToFind)) return true;
            current = current.getNext();
        }while(current != null);

        return false;

    }

    @Override
    public Iterator<T> iterator() {
        return new CustomIterator<>(this);
    }

    @Override
    public Object[] toArray() {
        if(root == null)
        {
            return  new Object[0];
        }

        int size = this.size();
        Element<T> current = root;
        Object[] array = new Object[size];
        for(int i = 0; i < size; i++)
        {
            array[i] = current.getValue();
            current = current.getNext();
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s)
    {
        return (T1[]) toArray();
    }

    @Override
    public boolean add(T t) {
        if(root == null)
        {
            root = new Element<>(t, null);
        }
        else
        {
            Element<T> current = root;
            Element<T> elemToAdd = new Element<>(t, null);
            if(root.getValue().compareTo(elemToAdd.getValue()) > 0)
            {
                elemToAdd.setNext(current);
                root = elemToAdd;
            }
            else
            {
                while(current.getNext() != null && (current.getNext().getValue().compareTo(elemToAdd.getValue()) < 0) )
                {
                    current = current.getNext();
                }
                elemToAdd.setNext(current.getNext());
                current.setNext(elemToAdd);
            }

        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(!contains(o)){
            return false;
        }
        if(root == null)
        {
            return false;
        }

        T valueToRemove;
        try{
            valueToRemove = (T) o;
        }
        catch(Exception e)
        {
            throw new ClassCastException();
        }
        if(root.getValue().equals(valueToRemove))
        {
            root = root.getNext();
            return true;
        }
        else
        {
            Element<T> current = root;
            while(current.getNext() != null && !current.getNext().getValue().equals(valueToRemove))
            {
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());
            return true;
        }

    }

    @Override
    public boolean containsAll(Collection<?> collection)
    {
        if(root == null && collection.size() != 0) return false;
        for (Object o : collection)
        {
            if (!this.contains(o))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection)
    {
        for(Object o : collection)
        {
            T valueToAdd;
            try{
                valueToAdd = (T) o;
            }
            catch(Exception e)
            {
                throw new ClassCastException();
            }
            this.add(valueToAdd);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection)
    {
        for(Object o : collection)
        {
            T valueToRemove;
            try{
                valueToRemove = (T) o;
            }
            catch(Exception e)
            {
                throw new ClassCastException();
            }
            this.remove(valueToRemove);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection)
    {
        T valueToRetain;
        for(T o : this)
        {
            valueToRetain = o;
            if(!collection.contains(valueToRetain))
            {
                this.remove(valueToRetain);
            }
        }
        return true;
    }

    @Override
    public void clear()
    {
        this.setRoot(null);
    }

    public void printList()
    {
        Element<T> current = this.root;
        while(current != null)
        {
            System.out.println(current.getValue().toString());
            current = current.getNext();
        }
    }

}
