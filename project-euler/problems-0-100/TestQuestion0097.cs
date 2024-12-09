using System;
using System.Collections.Generic;
using System.Xml.Schema;
using NUnit.Framework;
using Project_Euler.Source;

namespace Project_Euler.Tests._000_099
{
    /*
Large non-Mersenne prime

Problem 97
The first known prime found to exceed one million digits was discovered in 1999, 
and is a Mersenne prime of the form 2^6972593−1; it contains exactly 2,098,960 digits. 
Subsequently other Mersenne primes, of the form 2^p−1, have been found which contain more digits.

However, in 2004 there was found a massive non-Mersenne prime which contains 
2,357,207 digits: 28433×(2^7830457)+1.

Find the last ten digits of this prime number.
 */
    [TestFixture()]
    public class TestQuestion0097
    {
        [TestCase(8739992577)]
        public void LargeNonMersennePrime(Int64 expected)
        {
            // Formula is  a + b^c + d

            const Int64 m = 28433;
            const Int64 e = 7830457;
            const Int64 c = 1;

            Int64 bPowc = 2;

            const Int64 LimitExponent = 10;
            Int64 Limit = (Int64) Math.Pow(10, LimitExponent);

            Int64 result = 0;
            for (Int64 i = 1; i < e; i++)
            {
                bPowc *= 2;

                if (bPowc > Limit)
                {
                    bPowc = Truncate(bPowc, LimitExponent);
                }

                result = (m * bPowc) + 1;
            }
            Assert.That(Truncate(result,LimitExponent),Is.EqualTo(expected));
        }

        private Int64 Truncate(
            Int64 input, 
            Int64 digitsToKeep)
        {
            Int64 result;
            Int64 divisor = (Int64) Math.Pow(10, digitsToKeep);
            result = (input / divisor);
            result *= divisor;
            return input - result;
        }

        [TestCase(12345678, 4, 5678)]
        [TestCase(12345678, 7, 2345678)]
        [TestCase(12345678, 10, 12345678)]
        [TestCase(12345678, 0, 0)]
        public void TestTruncate(Int64 input,
                                Int64 digits,
                                Int64 expected)
        {
            Assert.That(Truncate(input,digits),Is.EqualTo(expected));
        }
    }
}
