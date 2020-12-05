using PO_Zad1_L3;
using System;
using System.Collections;
using System.Collections.Generic;

namespace PO_Z1_L4
{
    class ListaEnum<T> : IEnumerator<T>
    {
        private Element<T> root;
        private Element<T> current;

        public ListaEnum(Element<T> root)
        {
            this.root = root;
            this.current = root;
        }

        public T Current => current.getValue();

        object IEnumerator.Current => Current;

        public void Dispose()
        {
            throw new NotImplementedException();
        }

        public bool MoveNext()
        {
            if(current.getNext() == null)
            {
                return false;
            }
            current = current.getNext();
            return true;
        }

        public void Reset()
        {
            current = root;
        }
    }
}
