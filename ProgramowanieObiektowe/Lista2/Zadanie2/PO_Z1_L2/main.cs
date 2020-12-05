using System;
using System.Threading;
using System.Collections.Generic;
using System.Text;

namespace PO_Z1_L2
{
    class main
    {
        static void Main()
        {
            RandomWordStream rwd = new RandomWordStream();
            while(true)
            {
                Console.WriteLine(rwd.next());
                Thread.Sleep(500);
            }
            
        }
    }
}
