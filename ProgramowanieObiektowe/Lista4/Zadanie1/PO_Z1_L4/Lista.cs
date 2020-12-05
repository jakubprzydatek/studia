using PO_Z1_L4;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;

namespace PO_Zad1_L3
{

    class Lista<T>: IEnumerable<T>
    {
        private Element<T> root = null;
        private Element<T> leaf = null;

        public Lista(){}

        public void AddToEnd(T valueToAdd)
        {
            if(root == null)
            {
                root = new Element<T>(valueToAdd, null, null);
                leaf = root;
            }
            else
            {
                Element<T> lastElement = root;
                while(lastElement.getNext() != null)
                {
                    lastElement = lastElement.getNext();
                }
                lastElement.setNext(new Element<T>(valueToAdd, null, lastElement));
                leaf = lastElement.getNext();
            }
        }

        public void AddToBegin(T valueToAdd)
        {
            if (root == null)
            {
                root = new Element<T>(valueToAdd, null, null);
                leaf = root;
            }
            else
            {
                root.setPrev(new Element<T>(valueToAdd, root, null));
                root = root.getPrev();
            }
        }

        public T DeleteLast()
        {
            if (leaf == null)
            {
                return default;
            }
            else
            {
                T valueToReturn = leaf.getValue();
                leaf = leaf.getPrev();
                leaf.setNext(null);
                return valueToReturn;
            }
        }

        public T DeleteFirst()
        {
            if (root == null)
            {
                return default;
            }
            else
            {
                T valueToReturn = root.getValue();
                root = root.getNext();
                root.setPrev(null);
                return valueToReturn;
            }
        }

        public void print()
        {
            if (root == null) return;
            if(root.getNext() == null)
            {
                Console.WriteLine(root.getValue());
            }
            else
            {
                Element<T> currentElement = root;
                while (currentElement != null)
                {
                    Console.WriteLine(currentElement.getValue());
                    currentElement = currentElement.getNext();
                }
            }
            
        }

        public ListaEnum<T> GetEnumerator()
        {
            return new ListaEnum<T>(root);
        }

        IEnumerator<T> IEnumerable<T>.GetEnumerator()
        {
            return GetEnumerator();
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }
    }
}
