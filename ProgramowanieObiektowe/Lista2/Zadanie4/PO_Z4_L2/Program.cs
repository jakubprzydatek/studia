using System;

namespace PO_Z4_L2
{
    class Program
    {
        static void Main(string[] args)
        {
            ListaLeniwa lazylist = new ListaLeniwa();
            Pierwsze primelist = new Pierwsze();

            /*Console.WriteLine(lazylist.size());
            Console.WriteLine(lazylist.element(10));
            Console.WriteLine(lazylist.element(5));
            Console.WriteLine(lazylist.element(15));
            Console.WriteLine(lazylist.element(50));
            Console.WriteLine(lazylist.element(50));
            Console.WriteLine(lazylist.size());*/
            Console.WriteLine(primelist.size());
            Console.WriteLine(primelist.element(1));
            Console.WriteLine(primelist.element(2));
            Console.WriteLine(primelist.element(4));
            Console.WriteLine(primelist.element(10));
            Console.WriteLine(primelist.element(5));
            Console.WriteLine(primelist.size());

        }
    }
}
