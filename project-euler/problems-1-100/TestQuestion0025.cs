using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Text;
using System.Threading.Tasks;
using NUnit.Framework;
using Project_Euler.Source;

namespace Project_Euler.Tests._000_099
{
    /*
1000-digit Fibonacci number

Problem 25
The Fibonacci sequence is defined by the recurrence relation:

Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
Hence the first 12 terms will be:

F1 = 1
F2 = 1
F3 = 2
F4 = 3
F5 = 5
F6 = 8
F7 = 13
F8 = 21
F9 = 34
F10 = 55
F11 = 89
F12 = 144
The 12th term, F12, is the first term to contain three digits.

What is the index of the first term in the Fibonacci sequence to contain 1000 digits?    
*/
    [TestFixture]
    public class TestQuestion0025
    {
        [TestCase(3, 12)]
        [TestCase(1000, 4782)]
        public void Test1000DigitFibonacciNumber(Int32 digits, Int32 expectedN)
        {
            Test1000DigitFibonacciNumber_BruteForce(digits,expectedN);
        }
        public void Test1000DigitFibonacciNumber_BruteForce(Int32 digits, Int32 expectedN)
        {
            Int32 n = 1;
            string fibAsString = "";
            do
            {
                BigInteger fibAsBigInt = HelperFunctions.GetFibonacci(n);
                fibAsString = fibAsBigInt.ToString();
                n++;

            } while (fibAsString.Length < digits);
            Assert.That(n-1,Is.EqualTo(expectedN));
        }




        public double GetFibonacciApproximation(Int64 n)
        {
            double Phin = Math.Pow(GoldenRatio(), n);
            double phin = Math.Pow(-GoldenRatio(), -n);
            double result = (1.0 / Math.Sqrt(5));
            result *= (Phin - phin);
            return result;
        }
        [TestCase(7, 13)]
        [TestCase(9, 34)]
        //[TestCase(90, 2880067194370816120)]
        public void TestGetFibonacciApproximation(Int64 n, Int64 expected)
        {
            Assert.That((Int64)GetFibonacciApproximation(n), Is.EqualTo(expected));
        }

        public static double GoldenRatio()
        {
            double d = Math.Sqrt(5);
            d += 1;
            d /= 2;
            return d;
        }
        [TestCase(1000000, 1618033)]
        public void TestGoldenRatio(Int64 multiplier, Int64 expected)
        {
            Int32 ratio = (Int32)(GoldenRatio() * multiplier);
            Assert.That(ratio,Is.EqualTo(expected));
        }
    }
}
