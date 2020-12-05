using PO_Zad1_L3;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;

namespace PO_Z1_L4
{
    class main
    {
        static void Main()
        {
            Lista<int> lista = new Lista<int>();

            lista.AddToBegin(5);
            lista.AddToBegin(4);
            lista.AddToBegin(3);
            lista.AddToBegin(2);
            lista.AddToBegin(1);

            IEnumerator<int> listaEnum = lista.GetEnumerator();
            do
            {
                Console.WriteLine(listaEnum.Current);
            } while (listaEnum.MoveNext());
            
        }
    }
}
