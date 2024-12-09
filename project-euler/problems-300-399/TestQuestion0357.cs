using System;
using System.Linq;
using NUnit.Framework;
using NUnit.Framework.Constraints;
using Project_Euler.Source;

namespace Project_Euler.Tests._300_399
{
    /*
Prime generating integers

Problem 357
Consider the divisors of 30: 1,2,3,5,6,10,15,30.
It can be seen that for every divisor d of 30, d+30/d is prime.

Find the sum of all positive integers n not exceeding 100 000 000
such that for every divisor d of n, d+n/d is prime.
     */
    [TestFixture()]
    public class TestQuestion0357
    {
        [TestCase(30, 71)]
        [TestCase(1000, 8427)]
        [TestCase(1000000, 524402305), Description("Takes 4mins 41seconds minutes")]
        [Category("Slow")]
        public void TestPrimeGeneratingInteger(Int64 limit,
                                               Int64 expectedSum)
        {
            Int64[] primes = HelperFunctions.GetPrimesUpto(limit*2);

            Int64 sum = 0;
            for (int i = 1; i <= limit; i++)
            {
                if (IsPrimeGeneratingNumber(i,primes))
                {
                    sum += i;
                }
            }
            Assert.That(sum,Is.EqualTo(expectedSum));
        }


        private bool IsPrimeGeneratingNumber(Int64 value,
                                             Int64[] primes)
        {
            bool result = true;
            Int64[] factors = HelperFunctions.GetFactors(value);
            Int64 candidatePrime;
            
            foreach (Int64 factor in factors)
            {
                candidatePrime = factor + (value / factor);
                if (!primes.Contains(candidatePrime))
                {
                    return false;
                }
            }
            return result;
        }
        [TestCase(20, false)]
        [TestCase(30, true)]
        public void TestIsPrimeGeneratingNumber(Int64 value,
                                                bool expected)
        {
            Int64[] primes = HelperFunctions.GetPrimesUpto(value*2);
            Assert.That(IsPrimeGeneratingNumber(value,primes), Is.EqualTo(expected));
        }
    }
}
