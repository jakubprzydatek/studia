using System;
using System.Collections.Generic;
using System.Text;

namespace PO_Z1_L2
{
    class RandomStream : IntStream
    {
        private Random rnd;
        private int minValue;
        private int maxValue;

        public int next()
        {
            return rnd.Next(minValue, maxValue);
        }

        public bool eos()
        {
            return false;
        }

        public void reset()
        {
           //notning to do 
        }

        public RandomStream( int minValue, int maxValue )
        {
            rnd = new Random();
            this.minValue = minValue;
            this.maxValue = maxValue;

        }
    }
}
