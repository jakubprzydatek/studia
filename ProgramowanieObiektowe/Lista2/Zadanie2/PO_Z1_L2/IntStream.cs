using System;
using System.Collections.Generic;
using System.Text;

namespace PO_Z1_L2
{
    class IntStream
    {
        protected int CurrentNumber;

        public int next()
        {
            if (eos())
                reset();
            int toReturn = CurrentNumber;
            CurrentNumber += 1;
            return toReturn;
        }

        public bool eos()
        {
            return int.MaxValue == CurrentNumber;
        }

        public void reset()
        {
            CurrentNumber = Constans.FirstNaturalNumber;
        }

        public IntStream()
        {
            CurrentNumber = Constans.FirstNaturalNumber;
        }

    }
}
