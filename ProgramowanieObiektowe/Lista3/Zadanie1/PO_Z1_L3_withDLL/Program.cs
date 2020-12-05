using System;
using Zad1Lib;

namespace PO_Z1_L3_withDLL
{
    class Program
    {
        static void Main(string[] args)
        {
            Lista<int> list = new Lista<int>();
            list.AddToEnd(10);
            list.AddToEnd(11);
            list.AddToEnd(13);
            list.AddToBegin(2567);
            list.print();
            Console.WriteLine(" ");
            Console.WriteLine(list.DeleteFirst());
            Console.WriteLine(list.DeleteLast());
            Console.WriteLine(" ");
            list.print();
        }
    }
}
