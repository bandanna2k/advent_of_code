using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Numerics;
using System.Security.Policy;
using NUnit.Framework;
using Project_Euler.Source;

namespace Project_Euler.Tests._600_699
{
    /*
Divisible Palindromes

Problem 655
The numbers 545, 5995 and 15151 are the three smallest palindromes divisible by 109. 
There are nine palindromes less than 100000 which are divisible by 109.

How many palindromes less than 10^32 are divisible by 10000019 ?
*/
    [TestFixture()]
    public class TestQuestion0655
    {
        //[Timeout(5 * 60 * 1000)]
        [Category("Slow")]
        [Test]
        public void TestTime()
        {
            Result655 result = TestDivisiblePalindromes();
            Console.WriteLine(result.Message);
        }
        public Result655 TestDivisiblePalindromes()
        { 
            Stopwatch sw = Stopwatch.StartNew();

            Result655 result = new Result655();

            BigInteger tenPower32 = BigInteger.Pow(10, 32);
            BigInteger tenPower24 = BigInteger.Pow(10, 24);
            BigInteger tenPower16 = BigInteger.Pow(10, 16);
            BigInteger tenPower6 = BigInteger.Pow(10, 6);
            BigInteger tenPower7 = BigInteger.Pow(10, 7);
            BigInteger tenPower8 = BigInteger.Pow(10, 8);
            UInt64 lTenPower8 = (UInt64)(Math.Pow(10, 8)); 

            UInt32 nDiv = 10000019;
            BigInteger div = nDiv;

            ListOfBigInt[] listOfLists = new ListOfBigInt[nDiv];
            for (UInt32 i = 0; i < nDiv; i++)
                listOfLists[i] = new ListOfBigInt();

            result.AddMessage(string.Format("Setup complete:{0}", sw.ElapsedMilliseconds / 1000));

            for (UInt32 D = 1; D < tenPower8 ; D++)
            {
                UInt32 nA = Reverse(D);
                BigInteger AD = nA;
                AD *= tenPower24;
                AD += D;
                BigInteger ADmodDiv = AD % div;
                UInt32 nADmodDiv = (UInt32) ADmodDiv;

                ListOfBigInt listOfValues = listOfLists[nADmodDiv];
                listOfValues.Add(AD);
            }

            result.AddMessage(string.Format("End of 1st loop:{0}", sw.ElapsedMilliseconds / 1000));

            for (UInt32 C = 0; C < tenPower8 ; C++)
            {
                UInt64 nB = Reverse(C);
                BigInteger BC = nB;
                BC *= tenPower16;
                BC += ((UInt64)C * lTenPower8);
                BigInteger BCmodDiv = BC % div;
                UInt32 nBCmodDiv = (UInt32) BCmodDiv;

                UInt32 nDivMinus_nBCmodDiv = (nDiv - nBCmodDiv) % nDiv;

                ListOfBigInt listOfValues = listOfLists[nDivMinus_nBCmodDiv];
                result.Count += listOfValues.Count;
                //for(UInt32 i = 0 ; i < listOfValues.Count ; i++)
                //{
                //    BigInteger value = listOfValues.Values[i];
                //    //Console.WriteLine("ModAD:{0}, AD:{1}", nDivMinus_nBCmodDiv, value.ToString());
                //    //Console.WriteLine("ModBC:{0}, BC:{1} B+C:{2} {3}", nBCmodDiv, BC.ToString(), nB, C);
                //    count++;
                //}
                ////Console.WriteLine("----------------");
            }

            result.AddMessage(string.Format("End of 2nd loop:{0}", sw.ElapsedMilliseconds / 1000));

            result.AddMessage(string.Format("Count:{0}", result.Count));
            result.AddMessage(string.Format("End:{0}", sw.ElapsedMilliseconds / 1000));
            return result;
        }

        public class Result655
        {
            public UInt64 Count = 0;
            public string Message = "";

            public void AddMessage(string message)
            {
                this.Message += (message + Environment.NewLine);
            }
        }
        
        internal class ListOfBigInt
        {
            internal UInt32 Count;
            internal BigInteger[] Values = new BigInteger[30];

            internal void Add(BigInteger value)
            {
                this.Values[this.Count] = value;
                this.Count++;
            }
        }



        [TestCase(100019, "10000000000000", 103)] /* 32- secs */
        //[TestCase(10000019, "100000000000000000000000000000000", 3)]
        public void TestDivisiblePalindromes_CreatingPalindromes(
            Int64 valueAsInt64, 
            string limitAsString,
            Int64 expectedCount)
        {
            Int64 count = 0;

            Int32 lengthLimit = limitAsString.Length;
            Int32 lengthDivisor = valueAsInt64.ToString().Length;
            Int32 startDivisorPower = lengthDivisor + 1 + 2;
            string debug1, debug2, debug3, debug4, debug5;
            bool startAgain;
            while (true)
            {
                startAgain = false;

                BigInteger startValue = BigInteger.Pow(10, startDivisorPower);
                BigInteger nextStartValue = BigInteger.Pow(10, startDivisorPower+1);

                // Find first value
                for (int i = 0 ; i < valueAsInt64; i++)
                {
                    if (HelperFunctions.IsMultipleOf(startValue,valueAsInt64))
                    {
                        if (IsPalindrome(startValue))
                            count++;
                        break;
                    }
                    startValue++;
                }

                // Potential palindrome is startValue * 80 so that 10 matches 01
                for (int i = 0; i < 80; i++)
                {
                    startValue++;

                    // Check if we have gone to far
                    if (startValue > nextStartValue)
                    {
                        startAgain = true;
                        break;
                    }
                }

                if (startAgain)
                {
                    startDivisorPower++;
                    continue;
                }

                debug1 = startValue.ToString();
                startValue += (startValue * 80);
                debug2 = startValue.ToString();



                // Next
                startDivisorPower++;

                if (startDivisorPower > limitAsString.Length) break;
            }




            Assert.That(count, Is.EqualTo(expectedCount));
        }
        //[TestCase(109, "100000", 9)]
        //[TestCase(1009, "1000000", 1)]
        //[TestCase(10009, "10000000", 1)]
        //[TestCase(100009, "100000000", 0)]
        //[TestCase(119, "100000", 7)]
        //[TestCase(1019, "1000000", 2)]
        //[TestCase(10019, "10000000", 0)]
        //[TestCase(100019, "100000000", 0)]
        //[TestCase(100019, "1000000000", 0)]
        //[TestCase(100019, "10000000000", 0)]
        //[TestCase(100019, "100000000000", 8)]
        //[TestCase(100019, "1000000000000", 15)]
        ////[TestCase(100019, "10000000000000", 103)] /* 35 secs */
        //[TestCase(10000019, "10000000000", 0)]  /* */
        ////[TestCase(100019, "100000000000000", 171)] /* 7 mins */
        [TestCase(10000019, "100000000000000000000000000000000", 3)]
        public void TestDivisiblePalindromes_PlayMultiplyingDivisor(Int64 valueAsInt64,
            string limitAsString,
            Int64 expectedCount)
        {
            Int64 count = 0;
            BigInteger value = new BigInteger(valueAsInt64);
            BigInteger limit = BigInteger.Parse(limitAsString);
            for (BigInteger candidate = value;
                candidate <= limit && candidate < 1000000000000000;
                candidate += value)
            {
                string candidateAsString = candidate.ToString();
                if (IsPalindrome(candidate))
                {
                    count++;
                }

                if ((candidateAsString.Length % 2) == 0)
                {
                    Console.WriteLine("Cand:{0} {1}",
                        new string(candidateAsString.Substring(0, candidateAsString.Length / 2).Reverse().ToArray()),
                        //                        candidateAsString.Substring(0,candidateAsString.Length /2),
                        candidateAsString.Substring(candidateAsString.Length / 2, candidateAsString.Length / 2));
                }
                else
                {
                    Console.WriteLine("Cand:{0} {1} {2}",
                        new string(candidateAsString.Substring(0, candidateAsString.Length / 2).Reverse().ToArray()),
                        //                        candidateAsString.Substring(0,candidateAsString.Length /2),
                        candidateAsString.Substring((candidateAsString.Length / 2), 1),
                        candidateAsString.Substring((candidateAsString.Length / 2) + 1, (candidateAsString.Length / 2)));
                }
            }
            Assert.That(count, Is.EqualTo(expectedCount));
        }
        [TestCase(109, "100000", 9)]
        [TestCase(1009, "1000000", 1)]
        [TestCase(10009, "10000000", 1)]
        [TestCase(100009, "100000000", 0)]
        [TestCase(119, "100000", 7)]
        [TestCase(1019, "1000000", 2)]
        [TestCase(10019, "10000000", 0)]
        [TestCase(100019, "100000000", 0)]
        [TestCase(100019, "1000000000", 0)]
        [TestCase(100019, "10000000000", 0)]
        [TestCase(100019, "100000000000", 8)]
        [TestCase(100019, "1000000000000", 15)]
        //[TestCase(100019, "10000000000000", 103)] /* 35 secs */
        [TestCase(10000019, "10000000000", 0)]  /* */
        //[TestCase(100019, "100000000000000", 171)] /* 7 mins */
        //[TestCase(10000019, "100000000000000000000000000000000", 2000008332)]
        // Brute Force
        public void TestDivisiblePalindromes_MultiplyingDivisor(Int64 valueAsInt64,
            string limitAsString,
            Int64 expectedCount)
        {
            Int64 count = 0;
            BigInteger value = new BigInteger(valueAsInt64);
            BigInteger limit = BigInteger.Parse(limitAsString);
            for (BigInteger candidate = value;
                candidate <= limit && candidate < 10000000000000;
                candidate += value)
            {
                string candidateAsString = candidate.ToString();
                if (IsPalindrome(candidate))
                {
                    Console.WriteLine("Pal:{0}",candidateAsString);
                    count++;
                }
            }
            Assert.That(count, Is.EqualTo(expectedCount));
        }


        public void TestDivisiblePalindromes_BruteForce_Optimised(Int64 valueAsInt64,
            string limitAsString,
            Int64 expectedCount)
        {
            Int64 count = 0;
            BigInteger value = new BigInteger(valueAsInt64);
            BigInteger limit = BigInteger.Parse(limitAsString);
            BigInteger candidate = value;
            UInt32 prevFirst = 0;
            while (candidate <= limit)
            {
                string candidateAsString = candidate.ToString();
                UInt32 first = (UInt32)(Char.GetNumericValue(candidateAsString, 0));
                UInt32 last = (UInt32)(Char.GetNumericValue(candidateAsString,candidateAsString.Length-1));
                UInt32 skipMultiplier;
                if (first == prevFirst)
                    if (first == last)
                        skipMultiplier = 1;
                    else if (last > first)
                        skipMultiplier = (last - first);
                    else
                    {
                        //UInt32 second = (UInt32)(Char.GetNumericValue(candidateAsString, 1));
                        //skipMultiplier = Math.Min(10 - second, first - last);
                        skipMultiplier = 1;
                    }
                else
                    skipMultiplier = 1;

                if (IsPalindrome(candidate))
                {
                    count++;
                }

                candidate += (value * skipMultiplier);

                // After increment
                prevFirst = first;
            }
            Assert.That(count, Is.EqualTo(expectedCount));
        }

        private bool IsPalindrome(BigInteger value)
        {
            string valueAsString = value.ToString();
            bool result = true;
            for (int i = 0; i < (valueAsString.Length / 2); i++)
            {
                int j = (valueAsString.Length - i - 1);
                if (valueAsString[i] == valueAsString[j])
                {
                    // Continue
                }
                else
                {
                    result = false;
                    break;
                }
            }
            return result;
        }
        [TestCase("1", true)]
        [TestCase("2", true)]
        [TestCase("9", true)]
        [TestCase("11", true)]
        [TestCase("121", true)]
        [TestCase("123", false)]
        [TestCase("12121", true)]
        [TestCase("12131", false)]
        public void TestIsPalindrome(string valueAsString,
                                     bool expected)
        {
            BigInteger value = BigInteger.Parse(valueAsString);
            Assert.That(IsPalindrome(value), Is.EqualTo(expected));
        }


        public static BigInteger GetNthPalindromicNumber(Int64 n)
        {
            //string nAsString = n.ToString();
            //Int64 nLength = nAsString.Length;
            //Int64 nThreshold = (Int64)(Math.Pow(10, nLength - 1));
            //Int64 left = n - nThreshold;
            //if (left <= nThreshold)
            //{
            //    left = n - (Int64)(Math.Pow(10, nLength - 2));
            //}

            //Int64 right;





            string nAsString = n.ToString();
            Int32 nLength = nAsString.Length;
            string resultAsString;

            if (nLength == 1)
            {
                resultAsString = (n - 1).ToString(); // if n has only one digit
            }
            else if(nAsString[0] == '1' && nAsString[1] == '0') // if first two digits == 10
            {
                resultAsString = "9";
                //p[0] = ' ';
                //    p[1] = '9';                         // n - 10^(t-2) => 10-1 = 9
                for (int j = nLength, i = nLength - 2; 
                    i >= 1;
                    i--, j++)                           // copy from t-2 down to 1
                    resultAsString += nAsString[i];     // so k = t-1 + t-2 = 2t-3
                }
                else if (nAsString[0] == '1')           // if 11 <= first two digits <= 19
                {
                    resultAsString = "";                // n - 10^(t-1) => 1-1 = 0
                    for(int j = nLength, i = nLength - 1;
                            i >= 1;
                            i--, j++)    // copy from t-1 down to 1
                        resultAsString += nAsString[i];                    // so k = t-1 + t-1 = 2t-2
                }
                else                                    // if first two digits >= 20
                {
                    UInt32 startDigit = (UInt32)(Char.GetNumericValue(nAsString[0])) - 1;
                    resultAsString = ("" + startDigit);
                    for (int j = nLength, i = nLength - 2; 
                        i >= 0; 
                        i--, j++)                           // copy from t-2 down to 0
                        resultAsString += nAsString[i];     // so k = t-0 + t-1 = 2t-1
                }
                return BigInteger.Parse(resultAsString);


            /*
            function get_p_from_n (n) // get n and return p both as arrays
            {
                var i, j;
                var p = n;
                var t = n.length;

                if(t==1) { p[0] -= 1; }                 // if n has only one digit

                else if(p[0]=='1' && p[1]=='0')         // if first two digits == 10
                {
                    p[0] = ' ';
                    p[1] = '9';                         // n - 10^(t-2) => 10-1 = 9
                    for (j=t, i=t-2; i>=1; i--, j++)    // copy from t-2 down to 1
                        p[j] = p[i];                    // so k = t-1 + t-2 = 2t-3
                }
                else if(p[0]=='1')                      // if 11 <= first two digits <= 19
                {
                    p[0] = ' ';                         // n - 10^(t-1) => 1-1 = 0
                    for (j=t, i=t-1; i>=1; i--, j++)    // copy from t-1 down to 1
                        p[j] = p[i];                    // so k = t-1 + t-1 = 2t-2
                }
                else                                    // if first two digits >= 20
                {
                    p[0] -= 1;                          // n - 10^(t-1)
                    for (j=t, i=t-2; i>=0; i--, j++)    // copy from t-2 down to 0
                        p[j] = p[i];                    // so k = t-0 + t-1 = 2t-1
                }
                return p;
            }
             */
        }
        [TestCase(1, "0")]
        [TestCase(11, "11")]
        [TestCase(20, "101")]
        [TestCase(22, "121")]
        [TestCase(100, "909")]
        [TestCase(1000, "9009")]
        [TestCase(10000, "90009")]
        public void TestGetNthPalindromicNumber(Int64 input, string expectedAsString)
        {
            BigInteger expected = BigInteger.Parse(expectedAsString);
            Assert.That(GetNthPalindromicNumber(input),Is.EqualTo(expected));
        }


        public static Int64 GetCountOfPalindromes(Int64 digits)
        {
            if (digits <= 1)
                return 9;
            else if ((digits % 2) == 1)
                return 10 * GetCountOfPalindromes(digits - 1);
            else if ((digits % 2) == 0)
                return 10 * GetCountOfPalindromes(digits - 2);
            else
                throw new ApplicationException("Invalid scenario.");
        }
        [Ignore("Doesn't work")]
        [TestCase(1, 9)]
        [TestCase(2, 9)]
        [TestCase(3, 90)]
        [TestCase(7, 9000)]
        [TestCase(33, 900000000000L)]
        public void TestGetCountOfPalindromes(Int64 digits, Int64 expected)
        {
            Assert.That(GetCountOfPalindromes(digits),Is.EqualTo(expected));
        }




        // A utility for creating palindrome 
        BigInteger CreatePalindrome(BigInteger input, BigInteger b, bool isOdd)
        {
            BigInteger n = input;
            BigInteger palin = input;

            // checks if number of digits is odd or even 
            // if odd then neglect the last digit of input in 
            // finding reverse as in case of odd number of 
            // digits middle element occur once 
            if (isOdd)
                n /= b;

            // Creates palindrome by just appending reverse 
            // of number to itself 
            while (n > 0)
            {
                palin = palin * b + (n % b);
                n /= b;
            }
            return palin;
        }
        // Function to print decimal palindromic number 
        void GeneratePalindromes(BigInteger n) 
        { 
            BigInteger number; 

            // Run two times for odd and even length palindromes 
            foreach (bool _bool in Bools)
            {
                // Creates palindrome numbers with first half as i.  
                // Value of j decided whether we need an odd length 
                // of even length palindrome. 
                BigInteger i = BigInteger.One;
                while((number = CreatePalindrome(i, 10, _bool)) < n)
                { 
                    Console.WriteLine(number);
                    i++; 
                } 
            } 
        }

        // Driver Program to test above function
        [TestCase("104")]
        [TestCase("1000004")]
        [TestCase("1000000004")]
        [TestCase("100000000000000000")]
        //[TestCase(10000019, "100000000000000000000000000000000", 3)]
        public void TestCreatingPalindromes(string nAsString)
        {
            BigInteger n = BigInteger.Parse(nAsString);
            GeneratePalindromes(n); 
        } 
        private static bool[] Bools = new bool[] {true,false};








        /*
Let's look at the case with 32 decimal digits.  This can be broken down into A,B,C,D where 
each of A,B,C,D are 8 decimal digits.  We know that A = reverse(D) and B = reverse(C).

In this case, we really have A*10^24 + B*10^16 + C*10^8 + D.  If we split this, we have

outside = A*10^24 + D
inside = (B*10^8 + C)*10^8

If we loop over all of the possible D values (0, 1, ...10^8-1), then we know A and hence 
we can deduce outside (mod N). If we put these in a table and sort it, then we have a table 
with 100,000,000 entries.  If we then loop over the possible C values (same as D), we then 
know B and hence we can deduce inside.  We know that inside + outside = 0 (mod N).  
We know (given inside) which outside we need - look it up in the table.  (Note that there 
may be several for a given residue mod N, so chose your data struct appropriately.)

This will work for all numbers with even number of digits.  
For odd digit counts, you can tweak this method.

This worked for me.  Enjoy!
         */

        [TestCase(10000019)]
        public void TestInsideOutside(UInt32 divisorAsUInt32)
        {
            Int64 count = 0;
            BigInteger divisor = divisorAsUInt32;
            count += GetInsideOutside10Pow32(divisor);
        }

        public Int64 GetInsideOutside10Pow32(BigInteger divisor)
        {
            Dictionary<BigInteger,List<BigInteger>> mapOfOutside = new Dictionary<BigInteger, List<BigInteger>>();

            BigInteger tenPower24 = BigInteger.Pow(10, 24);
            BigInteger tenPower16 = BigInteger.Pow(10, 16);
            BigInteger tenPower8 = BigInteger.Pow(10, 8);

            Int64 count = 0;
            Stopwatch sw = Stopwatch.StartNew();

            for (BigInteger i = 0; i < tenPower8; i++)
            {
                BigInteger D = i;
                BigInteger A = Reverse(D);
                A *= tenPower24;
                //BigInteger AD = A + D;
                //BigInteger ADmodDiv = (AD % divisor);

                //List<BigInteger> listOfOutside;
                //if (mapOfOutside.TryGetValue(ADmodDiv, out listOfOutside))
                //{
                //    // Do nothing. We add AD later.
                //}
                //else
                //{
                //    listOfOutside = new List<BigInteger>();
                //    mapOfOutside[ADmodDiv] = listOfOutside;
                //}
                //listOfOutside.Add(AD);
            }

            sw.Stop();
            Console.WriteLine("Time:{0}",sw.ElapsedMilliseconds/1000);
            return count;
        }
        public static string Reverse(string s)
        {
            char[] charArray = s.ToCharArray();
            Array.Reverse(charArray);
            return new string(charArray);
        }
        public static UInt32 Reverse(UInt32 input)
        {
            UInt32 n = input;
            UInt32 reverse = 0;
            UInt32 rem;
            while (n != 0)
            {
                rem = n % 10;
                reverse = reverse * 10 + rem;
                n /= 10;
            }
            return reverse;
        }
        [TestCase(0, 0)]
        [TestCase(1, 1)]
        [TestCase(11, 11)]
        [TestCase(12, 21)]
        [TestCase(161616, 616161)]
        [TestCase(1234567, 7654321)]
        public void TestReverseInt(UInt32 input, UInt32 expected)
        {
            Assert.That(Reverse(input), Is.EqualTo(expected));
        }
        public static BigInteger Reverse(BigInteger input)
        {
            BigInteger n = input;
            BigInteger reverse = 0;
            BigInteger rem;
            while (n != 0)
            {
                rem = n % 10;
                reverse = reverse * 10 + rem;
                n /= 10;
            }
            return reverse;
        }
        [TestCase(0, 0)]
        [TestCase(1, 1)]
        [TestCase(11, 11)]
        [TestCase(12, 21)]
        [TestCase(161616, 616161)]
        [TestCase(1234567, 7654321)]
        public void TestReverseBigInteger(UInt32 input, UInt32 expected)
        {
            Assert.That(Reverse(new BigInteger(input)), Is.EqualTo(new BigInteger(expected)));
        }
    }
}
