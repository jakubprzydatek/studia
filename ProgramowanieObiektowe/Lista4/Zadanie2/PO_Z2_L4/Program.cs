using System;
using System.Collections;

namespace PO_Z2_L4
{
    class Program
    {
        static void Main(string[] args)
        {
            PrimeCollection primeCollection = new PrimeCollection();
            IEnumerator IEnum = primeCollection.GetEnumerator();

            foreach(int p in primeCollection)
            {
                Console.WriteLine(p);
            }
        }
    }
}
