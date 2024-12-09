using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using NUnit.Framework;
using Project_Euler.Source;

namespace Project_Euler.Tests._000_099
{
    /*
Non-abundant sums

Problem 23
A perfect number is a number for which the sum of its proper divisors is exactly equal 
 to the number. For example, the sum of the proper divisors of 28 would 
 be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.

A number n is called deficient if the sum of its proper divisors is less than n and 
 it is called abundant if this sum exceeds n.

As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number 
 that can be written as the sum of two abundant numbers is 24. By mathematical analysis, 
 it can be shown that all integers greater than 28123 can be written as the sum of two 
 abundant numbers. However, this upper limit cannot be reduced any further by analysis 
 even though it is known that the greatest number that cannot be expressed as the sum 
 of two abundant numbers is less than this limit.

Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
*/
    [TestFixture]
    public class TestQuestion0023
    {
        const Int32 LIMIT = 28123;
        public enum Abundance
        {
            Perfect,
            Abundant,
            Deficient
        }

        [Category("Slow")]
        [Test]
        public void TestNonAbundantSums()
        {
            Int64 sumOfNonAbundanceSums = 0;

            for (int i = 1; i <= LIMIT; i++)
            {
                if (!IsSumOfAbundantPair(i))
                {
                    sumOfNonAbundanceSums += i;
                }
            }
            Assert.That(sumOfNonAbundanceSums,Is.EqualTo(4179871));
        }

        private bool IsSumOfAbundantPair(Int64 value)
        {
            bool result = false;
            Int64 j;
            for (Int64 i = 12; i <= (value/2); i++)
            {
                j = value - i;
                if (GetAbundance(i) == Abundance.Abundant &&
                    GetAbundance(j) == Abundance.Abundant)
                {
                    result = true;
                    break;
                }
            }
            return result;
        }
        [TestCase(24, true)]
        [TestCase(25, false)]
        public void TestIsSumAbundantPair(Int64 value, bool expected)
        {
            Assert.That(IsSumOfAbundantPair(value), Is.EqualTo(expected));
        }

        Abundance GetAbundance(Int64 value)
        {
            Int64[] factors = HelperFunctions.GetFactors(value);
            Int64 sumOfFactors = 0;
            for (int i = 0; i < (factors.Length - 1); i++)
                sumOfFactors += factors[i];

            if (sumOfFactors < value)
                return Abundance.Deficient;
            else if (sumOfFactors > value)
                return Abundance.Abundant;
            return Abundance.Perfect;
        }
        [TestCase(10, Abundance.Deficient)]
        [TestCase(12, Abundance.Abundant)]
        [TestCase(28, Abundance.Perfect)]
        public void TestAbundance(Int64 value, Abundance abundance)
        {
            Assert.That(GetAbundance(value),Is.EqualTo(abundance));
        }
    }
}
