using System;
using Zad2Lib;

namespace PO_Z2_L3_withDLL
{
    class Program
    {
        static void Main(string[] args)
        {
            Slownik<int, int> dict = new Slownik<int, int>();
            dict.Add(1, 10);
            dict.Add(2, 11);
            dict.Add(3, 12);
            dict.Add(10, 13);
            dict.Add(4, 14);
            dict.Add(5, 15);
            dict.Add(6, 16);
            dict.Add(7, 17);
            dict.Add(8, 18);
            dict.Add(9, 19);
            dict.Add(11, 20);

            
            Console.WriteLine(dict.Search(4));
            dict.Delete(4);
            Console.WriteLine(dict.Search(4));
            Console.WriteLine(dict.Search(5));
        }
    }
}
