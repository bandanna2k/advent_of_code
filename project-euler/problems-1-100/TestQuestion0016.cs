using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Text;
using NUnit.Framework;
using Project_Euler.Source;

namespace Project_Euler.Tests._000_099
{
    /*
Power digit sum

Problem 16
215 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

What is the sum of the digits of the number 21000?
*/
    [TestFixture()]
    public class TestQuestion0016
    {
        [TestCase(2, 15, "26")]
        [TestCase(2, 1000, "1366")]
        public void TestPowerDigitSum_UsingBigInteger(Int64 _base,
            Int64 _exponent,
            string _expectedSum)
        {
            BigInteger sum = 0;
            BigInteger value = _base;
            string valueAsString;
            for (int i = 0; i < (_exponent-1) ; i++)
            {
                value *= _base;
            }

            valueAsString = Convert.ToString(value);

            foreach (char c in valueAsString.ToCharArray())
            {
                sum += Convert.ToInt32(c.ToString());
            }

            Assert.That(sum,Is.EqualTo(BigInteger.Parse(_expectedSum)));
        }
        [Ignore("Using double truncates values. Using decimal causes stackoverflow.")]
        [TestCase(2, 15, 26)]
        [TestCase(2, 1000, 1366)]
        public void TestPowerDigitSum_UsingDouble(Int64 _base,
            Int64 _exponent,
            double _expectedSum)
        {
            double sum = 0;
            double value = _base;
            string valueAsString;
            for (int i = 0; i < (_exponent - 1); i++)
            {
                value *= _base;
            }

            valueAsString = value.ToString().TrimEnd('0').TrimEnd('.');

            foreach (char c in valueAsString.ToCharArray())
            {
                sum += Convert.ToInt32(c.ToString());
            }

            Assert.That(sum, Is.EqualTo(_expectedSum));
        }
    }
}
