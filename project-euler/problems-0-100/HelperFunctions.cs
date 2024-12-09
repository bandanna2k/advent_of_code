using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using NUnit.Framework;

namespace Project_Euler.Source
{
    [TestFixture]
    public class HelperFunctions
    {
        #region Count Digits
        public static Int64 CountAllDigits(Int64 value)
        {
            Int64 count = 0;
            string s = Convert.ToString(value);

            foreach (char c in s.ToCharArray())
                count = count + Int32.Parse(Convert.ToString(c));
            return count;
        }
        [TestCase(0, 0)]
        [TestCase(1, 1)]
        [TestCase(9, 9)]
        [TestCase(10, 1)]
        [TestCase(19, 10)]
        [TestCase(99, 18)]
        [TestCase(1234567890, 45)]
        public void TestCountAllDigits(Int64 value, Int64 expected)
        {
            Assert.That(CountAllDigits(value), Is.EqualTo(expected));
        }
        public static Int64 CountAllDigits(BigInteger value)
        {
            Int64 count = 0;
            string s = Convert.ToString(value);

            foreach (char c in s.ToCharArray())
                count = count + Int32.Parse(Convert.ToString(c));
            return count;
        }
        [TestCase("0", 0)]
        [TestCase("1", 1)]
        [TestCase("9", 9)]
        [TestCase("10", 1)]
        [TestCase("19", 10)]
        [TestCase("99", 18)]
        [TestCase("1234567890", 45)]
        [TestCase("12345678901234567890", 90)]
        public void TestCountAllDigits(string valueAsBigInt, Int64 expected)
        {
            Assert.That(CountAllDigits(BigInteger.Parse(valueAsBigInt)), 
                Is.EqualTo(expected));
        }
        #endregion

        #region Is Multiple
        public static bool IsMultipleOf3(Int64 value)
        {
            return 0 == (HelperFunctions.CountAllDigits(value) % 3);
        }
        [TestCase(0, true)]
        [TestCase(3, true)]
        [TestCase(6, true)]
        [TestCase(9, true)]
        [TestCase(1, false)]
        [TestCase(4, false)]
        [TestCase(7, false)]
        [TestCase(102, true)]
        [TestCase(333, true)]
        [TestCase(963, true)]
        [TestCase(3 * 3 * 3 * 3, true)]
        [TestCase((3 * 3 * 3 * 3) + 1, false)]
        [TestCase(1234567890, true)]
        public void TestIsMultipleOf3(Int64 value, bool expected)
        {
            Assert.That(IsMultipleOf3(value), Is.EqualTo(expected));
        }

        public static bool IsMultipleOf5(Int64 value)
        {
            string s = Convert.ToString(value);
            if (s.EndsWith("0"))
                return true;
            if (s.EndsWith("5"))
                return true;
            return false;
        }
        [TestCase(0, true)]
        [TestCase(5, true)]
        [TestCase(10, true)]
        [TestCase(1055, true)]
        [TestCase(1000, true)]
        [TestCase(1, false)]
        [TestCase(22, false)]
        [TestCase(333, false)]
        [TestCase(4444, false)]
        [TestCase(666666, false)]
        [TestCase(7777777, false)]
        [TestCase(88888888, false)]
        [TestCase(999999999, false)]
        public void TestIsMultipleOf5(Int64 value, bool expected)
        {
            Assert.That(IsMultipleOf5(value), Is.EqualTo(expected));
        }

        public static bool IsMultipleOf(BigInteger value,
            BigInteger potentialDivisor)
        {
            BigInteger divided;
            BigInteger newValue;

            divided = value / potentialDivisor;
            newValue = potentialDivisor * divided;

            return newValue == value;
        }
        [TestCase(1, 1, true)]
        [TestCase(10, 2, true)]
        [TestCase(3, 2, false)]
        [TestCase(22, 7, false)]
        public void TestIsMultipleOf(Int64 value, Int64 divisor, bool expected)
        {
            Assert.That(IsMultipleOf(value, divisor), Is.EqualTo(expected));
        }
        #endregion

        #region Factors
        public static Int64[] GetFactors(Int64 value)
        {
            List<Int64> factors = new List<Int64>();
            Int64 max = Convert.ToInt64(System.Math.Sqrt(value));
            Int64 valueOverFactor;
            for (Int64 factor = 1; factor <= max; factor++)
            {
                if (value % factor == 0)
                {
                    factors.Add(factor);

                    valueOverFactor = Convert.ToInt64(value / (double)factor);
                    if (factor != valueOverFactor)
                        factors.Add(valueOverFactor);
                }
            }
            return factors.OrderBy(f => f).ToArray();
        }
        [TestCase(25, 1, 5, 25)]
        [TestCase(128, 1, 2, 4, 8, 16, 32, 64, 128)]
        public void TestGetFactors(Int64 value, params Int64[] expected)
        {
            Assert.That(GetFactors(value), Is.EqualTo(expected));
        }

        public static BigInteger[] GetFactors(BigInteger value)
        {
            BigInteger limit = value;
            BigInteger counter = BigInteger.One;
            List<BigInteger> listOfFactors = new List<BigInteger>();
            while (counter.CompareTo(limit) < 0)
            {
                if (value % counter == BigInteger.Zero)
                {
                    listOfFactors.Add(counter);

                    BigInteger partner = (value / counter);

                    listOfFactors.Add(partner);

                    limit = partner;
                }

                counter = counter + BigInteger.One;
            }
            return listOfFactors.Distinct().ToArray();
        }
        [TestCase(25, "1", "25", "5")]
        [TestCase(128, "1", "128", "2", "64", "4", "32", "8", "16")]
        public void TestGetFactors(Int64 value, params string[] expected)
        {
            BigInteger[] factors = GetFactors(new BigInteger(value));
            Assert.That(factors.Select(f => f.ToString().ToArray()), Is.EqualTo(expected));
        }
        #endregion

        #region Primes
        public static bool IsPrime(Int64 value)
        {
            return IsPrime_Slow(value);
        }
        public static bool IsPrime_Slow(Int64 value)
        {
            // Use XY = Value to work out our

            Int64 divisor;
            Int64 XY;

            for (Int64 i = 2; i <= Convert.ToInt64(value / (double)2); i++)
            {
                divisor = Convert.ToInt64(value / (double)i);
                XY = divisor * i;

                if (XY == value)
                    // Not prime
                    return false;
            }

            if (value <= 1)
                return false;

            return true;
        }
        [TestCase(2, true)]
        [TestCase(3, true)]
        [TestCase(5, true)]
        [TestCase(7, true)]
        [TestCase(101, true)]
        [TestCase(4, false)]
        [TestCase(15, false)]
        [TestCase(1, false)]
        public void TestIsPrime(Int64 value, bool expected)
        {
            Assert.That(IsPrime(value), Is.EqualTo(expected));
        }

        public static Int64[] GetPrimesUpto(Int64 limit)
        {
            Int64 n;
            Int64 k;
            List<Int64> listOfPrimes = new List<Int64>();

            Int64[] listOfNumbers = new Int64[limit + 1];
            for (n = 2; n <= Convert.ToInt64(System.Math.Sqrt(limit)); n++)
            {
                if (listOfNumbers[n] == 0)
                {
                    for (k = n * n; k <= limit; k += n)
                        listOfNumbers[k] = 1;
                }
            }

            listOfPrimes = new List<Int64>();
            for (n = 2; n <= limit; n++)
            {
                if (listOfNumbers[n] == 0)
                    listOfPrimes.Add(n);
            }
            return listOfPrimes.ToArray();
        }
        [TestCase(1)]
        [TestCase(2, 2)]
        [TestCase(3, 2, 3)]
        [TestCase(4, 2, 3)]
        [TestCase(11, 2, 3, 5, 7, 11)]
        public void TestGetPrimesUpto(Int32 limit, params Int32[] expected)
        {
            Assert.That(GetPrimesUpto(limit), Is.EqualTo(expected));
        }
        [TestCase(1, 0)]
        [TestCase(10, 4)]
        [TestCase(100, 25)]
        [TestCase(1000, 168)]
        [TestCase(10000, 1229)]
        [TestCase(100000, 9592)]
        [TestCase(110100, 10461)]
        public void TestGetPrimesUpto_Count(Int64 limit, Int64 expected)
        {
            Assert.That(GetPrimesUpto(limit).Count(), Is.EqualTo(expected));
        }
        #endregion

        #region Get Sum Of Squares
        /*
         * Get Sum of Squares
         */
        public static Int64 GetSumOfSquares(Int64 value)
        {
            Int64 result = 0;
            Int64 square;
            for (Int64 i = 1; i <= value; i++)
            {
                square = (i * i);
                result += square;
            }
            return result;
        }
        [TestCase(0, 0)]
        [TestCase(1, 1)]
        [TestCase(2, 5)]
        [TestCase(3, 14)]
        [TestCase(10, 385)]
        public void TestGetSumOfSquares(Int64 value, Int64 expected)
        {
            Assert.That(GetSumOfSquares(value), Is.EqualTo(expected));
        }

        /// <summary>
        ///     ''' Get Sum up to n
        ///     ''' </summary>
        public static Int64 GetSum(Int64 n)
        {
            Int64 result = 0;
            for (Int64 i = 1; i <= n; i++)
                result += i;
            return result;
        }
        [TestCase(0, 0)]
        [TestCase(1, 1)]
        [TestCase(2, 3)]
        [TestCase(3, 6)]
        [TestCase(10, 55)]
        public void TestGetSum(Int64 value, Int64 expected)
        {
            Assert.That(GetSum(value), Is.EqualTo(expected));
        }
        #endregion

        #region Permutations
        public static string[] GetPermutations(string input)
        {
            List<string> listOfPermutations = new List<string>();
            foreach (char[] charArray in GetPermutations<char>(input.ToCharArray()))
                listOfPermutations.Add(new string(charArray));
            return listOfPermutations.ToArray();
        }
        public static T[][] GetPermutations<T>(T[] input)
        {
            return GetPermutations(input, 0).ToArray();
        }
        private static List<T[]> GetPermutations<T>(T[] input, Int32 startIndex)
        {
            List<T[]> perms = new List<T[]>();
            Int32 l = input.Length - 1;

            if (l == startIndex)
                perms.Add(input);
            else
                for (int i = startIndex; i <= l; i++)
                {
                    T[] listCopy = input.ToArray();
                    T temp = listCopy[startIndex];
                    listCopy[startIndex] = listCopy[i];
                    listCopy[i] = temp;
                    perms.AddRange(GetPermutations(listCopy, startIndex + 1));
                }
            return perms;
        }
        [TestCase("ab", 2, "ab", "ba")]
        [TestCase("abc", 6, "abc", "acb", "bac", "bca", "cab", "cba")]
        public void TestGetPermutations(string input, Int32 expectedCount, params string[] expectedPermutations)
        {
            string[] permutations = GetPermutations(input);

            // Check counts are equal
            foreach (string s in permutations)
                Assert.That(s.Length, Is.EqualTo(input.Length));

            // Check a few permutations
            Assert.Multiple(() =>
            {
                foreach (string expectedPerm in expectedPermutations)
                    Assert.That(permutations.Contains(expectedPerm));
            });
        }
        #endregion

        #region Factorials
        public static Int64 Factorial(Int64 n)
        {
            if (n == 1)
                return 1;
            else
                return n * Factorial(n - 1);
        }
        public static BigInteger Factorial(BigInteger n)
        {
            if (n == 1)
                return 1;
            else
                return n * Factorial(n - 1);
        }
        [TestCase(1, 1)]
        [TestCase(2, 2)]
        [TestCase(3, 6)]
        [TestCase(10, 3628800)]
        [TestCase(20, 2432902008176640000)]
        public void TestFactorial(Int64 input,
            Int64 expected)
        {
            Assert.That(Factorial(input), Is.EqualTo(expected));
        }
        [TestCase("1", "1")]
        [TestCase("2", "2")]
        [TestCase("3", "6")]
        [TestCase("10", "3628800")]
        [TestCase("20", "2432902008176640000")]
        [TestCase("40", "815915283247897734345611269596115894272000000000")]
        public void TestFactorial(string inputAsString,
                                  string expectedAsString)
        {
            BigInteger input = BigInteger.Parse(inputAsString);
            BigInteger expected = BigInteger.Parse(expectedAsString);

            Assert.That(Factorial(input), Is.EqualTo(expected));
        }
        #endregion

        #region String Methods
        public static string Right(string input,
                                   Int32 length)
        {
            //Check if the value is valid
            if (string.IsNullOrEmpty(input))
            {
                //Set valid empty string as string could be null
                input = string.Empty;
            }
            else if (input.Length > length)
            {
                //Make the string no longer than the max length
                input = input.Substring(input.Length - length, length);
            }

            //Return the string
            return input;
        }
        [TestCase("1234", 2, "34")]
        [TestCase("1", 2, "1")]
        [TestCase("", 2, "")]
        [TestCase("1234", 0, "")]
        [TestCase(null, 2, "")]
        public void TestRight(string input,
            Int32 length,
            string expected)
        {
            Assert.That(Right(input, length), Is.EqualTo(expected));
        }
        #endregion

        #region Fibonacci
        public static BigInteger GetFibonacci(Int32 n)
        {
            BigInteger a = 0;
            BigInteger b = 1;

            // In N steps compute Fibonacci sequence iteratively.
            for (int i = 0; i < n; i++)
            {
                BigInteger temp = a;
                a = b;
                b = temp + b;
            }
            return a;
        }

        #endregion
    }
}
