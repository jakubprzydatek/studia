package com.company;

import java.util.ArrayList;
import java.util.List;

public class RisingList<T extends Comparable> {
    private List<T> list = new ArrayList<>();

    public void insert(T toInsert) {
        if (list.size() == 0) {
            list.add(toInsert);
            return;
        } else {
            int i;
            for (i = 0; i < list.size(); i++) {
                if (toInsert.compareTo(list.get(i)) < 0) {
                    list.add(i, toInsert);
                    return;
                }
            }
            list.add(i, toInsert);
        }
    }

    public T get(int i) {
        if (i > list.size() || i < 0)
        {
            return null;
        } else
        {
            return list.get(i);
        }
    }

    public boolean delete(int i)
    {
        if(i < 0 || i > list.size())
        {
            return false;
        }
        list.remove(i);
        return true;
    }

    @Override
    public String toString() {
        return "RisingList{" +
                "list=" + list +
                '}';
    }
}
