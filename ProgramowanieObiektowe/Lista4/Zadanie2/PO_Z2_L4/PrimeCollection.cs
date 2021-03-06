﻿using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;

namespace PO_Z2_L4
{
    class PrimeCollection : IEnumerable
    {
        private int CurrentNumber;
        public int next()
        {
            if (eos())
                reset();
            int toReturn = CurrentNumber;
            CurrentNumber = nextPrime();
            return toReturn;
        }


        private bool isPrime(int n)
        {
            if (n <= 1) return false;
            if (n <= 3) return true;

            if (n % 2 == 0 || n % 3 == 0)
                return false;

            for (int i = 5; i * i <= n; i = i + 6)
                if (n % i == 0 ||
                    n % (i + 2) == 0)
                    return false;

            return true;
        }

        private int nextPrime()
        {
            if (CurrentNumber <= 1)
                return 2;

            int prime = CurrentNumber;
            bool found = false;
            while (!found)
            {
                prime++;

                if (isPrime(prime))
                    found = true;
            }
            return prime;
        }

        public bool eos()
        {
            return int.MaxValue == CurrentNumber;
        }

        public void reset()
        {
            CurrentNumber = 2;
        }

        public IEnumerator GetEnumerator()
        {
            return new PrimeEnum(this);
        }

        public int getCurrent()
        {
            return CurrentNumber;
        }

        public PrimeCollection()
        {
            CurrentNumber = 2;
        }
    }
}
