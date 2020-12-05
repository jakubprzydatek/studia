using System;
using System.Collections.Generic;
using System.Text;

namespace PO_Zad1_L3
{
    class Element<T>
    {
        private T value;
        private Element<T> next;
        private Element<T> previous;

        public Element(T value, Element<T> next, Element<T> previous)
        {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }

        public Element<T> getNext()
        {
            return next;
        }
        public T getValue()
        {
            return value;
        }
        public Element<T> getPrev()
        {
            return previous;
        }

        public void setNext(Element<T> elemToSet)
        {
            next = elemToSet;
        }

        public void setPrev(Element<T> elemToSet)
        {
            previous = elemToSet;
        }
    }
}
