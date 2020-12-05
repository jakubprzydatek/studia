using System;
using System.Collections.Generic;
using System.Text;

namespace PO_Z2_L3
{
    class Slownik<K, V>
    {
        K[] keys;
        V[] values;
        int sizeOfDictionary;
        int sizeOfArrays;

        public Slownik()
        {
            sizeOfArrays = 10;
            sizeOfDictionary = 0;
            keys = new K[sizeOfArrays];
            values = new V[sizeOfArrays];
        }
        
        public void Add(K key, V value)
        {
            if(sizeOfDictionary >= sizeOfArrays)
            {
                Array.Resize(ref keys, keys.Length + sizeOfArrays);
                Array.Resize(ref values, values.Length + sizeOfArrays);
                sizeOfArrays += 10;
            }
            keys[sizeOfDictionary] = key;
            values[sizeOfDictionary] = value;
            sizeOfDictionary += 1;
        }

        public V Search(K key)
        {
            for(int i=0; i<sizeOfDictionary; i++)
            {
                if (keys[i].Equals ( key ) ) return values[i];
            }
            return default;
        }

        public void Delete(K key)
        {
            for (int i = 0; i < sizeOfDictionary; i++)
            {
                if (keys[i].Equals(key))
                {
                    for(int j = i; j < sizeOfDictionary - 1; j++)
                    {
                        keys[j] = keys[j + 1];
                        values[j] = values[j + 1];
                    }
                    sizeOfDictionary -= 1;
                    break;
                }
            }
        }
    }
}
