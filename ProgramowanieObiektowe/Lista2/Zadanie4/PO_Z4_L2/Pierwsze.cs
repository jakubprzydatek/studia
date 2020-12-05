using System;
using System.Collections.Generic;
using System.Text;

namespace PO_Z4_L2
{
    class Pierwsze : ListaLeniwa
    {

        private bool isPrime(int n)
        {
            if (n <= 1) return false;
            if (n <= 3) return true;

            if (n % 2 == 0 || n % 3 == 0)
                return false;

            for (int i = 5; i * i <= n; i = i + 6)
                if (n % i == 0 || n % (i + 2) == 0)
                    return false;

            return true;
        }

        override public int generateNumber()
        {
            if (this.size() == 0)
            {
                return 2;
            }
            int tmp = this.list[this.size() - 1] + 1;
            while (!this.isPrime(tmp))
            {
                tmp++;
            }
            return tmp;
        }

        public Pierwsze()
        {
            maxSize = 0;
        }
    }
}
