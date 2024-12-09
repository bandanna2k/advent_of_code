using System;
using System.Collections;
using System.Collections.Generic;
using System.Numerics;
using NUnit.Framework;
using Project_Euler.Properties;
using Project_Euler.Source;

namespace Project_Euler.Tests._000_099
{
    /*
Self powers

Problem 48
The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.

Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000
     */
    [TestFixture()]
    public class TestQuestion0048
    {
        private const Int64 LAST10DIGITS = 10000000000L;

        [TestCase(10, "405071317")]
        [TestCase(1000, "9110846700")]
        public void TestSelfPowers(Int32 limit,
                                   string expectedLast10Digits)
        {
            int i;
            Int64 sum = 0;
            Int64 powerLast10Digits;

            for (i = 1; i <= limit; i++)
            {
                powerLast10Digits = GetPowerLast10Digits(i);

                sum = (sum + powerLast10Digits) % LAST10DIGITS;
            }

            Assert.That(HelperFunctions.Right(Convert.ToString(sum),10), 
                        Is.EqualTo(expectedLast10Digits));
        }

        Int64 GetPowerLast10Digits(Int32 value)
        {
            int i;
            Int64 result = 1;

            for (i = 1; i <= value; i++)
            {
                result *= value;
                result = (result % LAST10DIGITS);
            }
            return result;
        }
    }
}
