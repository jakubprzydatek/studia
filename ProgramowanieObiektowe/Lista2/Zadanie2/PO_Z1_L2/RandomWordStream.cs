using System;
using System.Collections.Generic;
using System.Text;

namespace PO_Z1_L2
{
    class RandomWordStream 
    {

        private PrimeStream primeStream;
        private RandomStream randomStream;
        private string GenerateRandString()
        {
            if( eos() )
                reset();

            string generatedWord = "";
            int prime = primeStream.next();
            for (int i = 0; i < prime; i++)
            {
                generatedWord += (char)randomStream.next();
            }
            return generatedWord;
        }

        public string next()
        {
            return GenerateRandString();
        }

        public bool eos()
        {
            return primeStream.eos();
        }

        public void reset()
        {
            primeStream.reset();
        }

        public RandomWordStream()
        {
            primeStream = new PrimeStream();
            randomStream = new RandomStream(32, 126);

        }
    }
}
