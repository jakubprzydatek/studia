using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;

namespace PO_Z2_L4
{
    class PrimeEnum : IEnumerator
    {
        private PrimeCollection prime;

        public PrimeEnum(PrimeCollection prime)
        {
            this.prime = prime;
            this.prime.reset();
        }

        public int Current => prime.getCurrent();

        object IEnumerator.Current => Current;

        public bool MoveNext()
        {
            if(this.prime.eos())
            {
                return false;
            }
            prime.next();
            return true;

        }

        public void Reset()
        {
            this.prime.reset();
        }
    }
}
