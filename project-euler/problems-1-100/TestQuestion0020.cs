using System;
using System.Numerics;
using NUnit.Framework;
using Project_Euler.Source;

namespace Project_Euler.Tests._000_099
{
    /*
Factorial digit sum

Problem 20
n! means n × (n − 1) × ... × 3 × 2 × 1

For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.

Find the sum of the digits in the number 100!
     */
    [TestFixture()]
    public class TestQuestion0020
    {
        [TestCase(10, 27)]
        [TestCase(100, 648)]
        public void TestFactorialDigitSum(Int32 input, Int32 expectedSum)
        {
            Int64 sumOfDigits;

            BigInteger sum = 1;
            string sumAsString;

            for (int i = 1; i <= input; i++)
            {
                sum *= i;
                Console.WriteLine(String.Format("Index:{0},Sum:{1}", i, sum));

                sumAsString = sum.ToString();
                Console.WriteLine(String.Format("Index:{0},Sum as string:{1}", i, sum));
                sumAsString = sumAsString.TrimEnd('0');

                sum = BigInteger.Parse(sumAsString);

                Console.WriteLine(String.Format("Index:{0},New Sum:{1}", i, sum));
            }

            sumOfDigits = HelperFunctions.CountAllDigits(sum);
            Assert.That(sumOfDigits,Is.EqualTo(expectedSum));
        }
    }
}
