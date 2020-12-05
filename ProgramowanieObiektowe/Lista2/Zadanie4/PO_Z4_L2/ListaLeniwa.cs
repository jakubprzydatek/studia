using System;
using System.Collections.Generic;
using System.Text;

namespace PO_Z4_L2
{
    class ListaLeniwa
    {
        public List<int> list = new List<int>();
        Random random = new Random();
        public int maxSize;

        virtual public int generateNumber()
        {
            return random.Next();
        }

        public int size()
        {
            return maxSize;
        }

        public int element( int i )
        {
            if (i < maxSize) return list[i - 1];

            while (maxSize < i)
            {
                list.Add(this.generateNumber());
                maxSize++;
            }

            return list[i - 1];
        }

        public ListaLeniwa()
        {
            maxSize = 0;
        }
    }
}
