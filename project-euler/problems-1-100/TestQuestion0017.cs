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
Number letter counts

Problem 17
If the numbers 1 to 5 are written out in words: one, two, three, four, five, then 
 there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.

If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, 
 how many letters would be used?

NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 
 23 letters and 115 (one hundred and fifteen) contains 20 letters. The use of "and" when 
 writing out numbers is in compliance with British usage.
 */
    [TestFixture()]
    public class TestQuestion0017
    {
        [TestCase(5, 19)]
        [TestCase(1000, 21124)]
        public void TestNumberLetterCounts(Int32 maxInput,
                                           Int32 expectedLetters)
        {
            Int64 sumOfLetters = 0;
            string valueAsString;
            for (int i = 1; i <= maxInput; i++)
            {
                valueAsString = GetTextFromNumber_0To1000(i);
                valueAsString = valueAsString.Replace(" " , "");
                sumOfLetters += valueAsString.Length;
            }
            Assert.That(sumOfLetters,Is.EqualTo(expectedLetters));
        }


        public string GetTextFromNumber_0To1000(Int32 input)
        {
            if(input==1000) return "one thousand";

            string result = "";
            string inputAsString = Convert.ToString(input);

            // Get lower value first
            string inputLessThan100 = HelperFunctions.Right(inputAsString, 2);
            Int32 intLessThan100 = Int32.Parse(inputLessThan100);
            string resultLessThan100 = GetTextFromNumber_0To99(intLessThan100);

            // Get result over 100
            string resultOver100 = "";


            if (input >= 100)
            {
                // Get hundred values
                string inputOver100 = inputAsString.Substring(0,inputAsString.Length-2);
                Int32 intOver100 = Convert.ToInt32(inputOver100);
                resultOver100 = GetTextFromNumber_0To10(intOver100);
            }

            if (!String.IsNullOrEmpty(resultLessThan100) &&
                !String.IsNullOrEmpty(resultOver100))
            {
                return String.Format("{0} hundred and {1}", resultOver100, resultLessThan100);
            }

            if (String.IsNullOrEmpty(resultLessThan100) &&
                !String.IsNullOrEmpty(resultOver100))
            {
                return String.Format("{0} hundred", resultOver100);
            }

            if (!String.IsNullOrEmpty(resultLessThan100) &&
                String.IsNullOrEmpty(resultOver100))
            {
                return String.Format("{0}", resultLessThan100);
            }

            return result;
        }
        [TestCase(0, "")]
        [TestCase(100, "one hundred")]
        [TestCase(200, "two hundred")]
        [TestCase(1000, "one thousand")]
        [TestCase(1, "one")]
        [TestCase(11, "eleven")]
        [TestCase(18, "eighteen")]
        [TestCase(21, "twenty one")]
        [TestCase(99, "ninety nine")]
        [TestCase(199, "one hundred and ninety nine")]
        public void TestGetTextFromNumber_0To1000(Int32 input, string expected)
        {
            Assert.That(GetTextFromNumber_0To1000(input),Is.EqualTo(expected));
        }

        #region Text between 1 and 99
        public string GetTextFromNumber_0To99(Int32 input)
        {
            if (input < 0) throw new ApplicationException("Invalid input. < 1 GetTextFromNumberBetween1and99");
            if (input > 99) throw new ApplicationException("Invalid input. > 99 GetTextFromNumberBetween1and99");

            if (input == 0) return "";

            if (input <= 10)
            {
                return GetTextFromNumber_0To10(input);
            }
            else if (input <= 20)
            {
                return GetTextFromNumberBetween11and20(input);
            }
            else
            {
                string inputTenValue = HelperFunctions.Right(Convert.ToString(input),1);
                Int32 intTenValue = Convert.ToInt32(inputTenValue);
                return GetTextTenValue(input) + " " + GetTextFromNumber_0To10(intTenValue);
            }
        }
        public static string GetTextTenValue(Int32 input)
        {
            if(input<20) throw new ApplicationException("Invalid input. < 20 GetTextTenValue");
            if (input > 99) throw new ApplicationException("Invalid input. > 99 GetTextTenValue");

            if (input <= 29) return "twenty";
            if (input <= 39) return "thirty";
            if (input <= 49) return "forty";
            if (input <= 59) return "fifty";
            if (input <= 69) return "sixty";
            if (input <= 79) return "seventy";
            if (input <= 89) return "eighty";
            if (input <= 99) return "ninety";
            throw new ApplicationException("Invalid input. End of function. GetTextTenValue");
        }
        public string GetTextFromNumber_0To10(Int32 input)
        {
            switch (input)
            {
                case 0: return "";
                case 1: return "one";
                case 2: return "two";
                case 3: return "three";
                case 4: return "four";

                case 5: return "five";
                case 6: return "six";
                case 7: return "seven";
                case 8: return "eight";
                case 9: return "nine";

                case 10: return "ten";

                default: throw new ApplicationException("Only inputs of 0 to 10 accepted.");
            }
        }
        public string GetTextFromNumberBetween11and20(Int32 input)
        {
            switch (input)
            { 
                case 11: return "eleven";
                case 12: return "twelve";
                case 13: return "thirteen";
                case 14: return "fourteen";
                case 15: return "fifteen";

                case 16: return "sixteen";
                case 17: return "seventeen";
                case 18: return "eighteen";
                case 19: return "nineteen";
                case 20: return "twenty";

                default: throw new ApplicationException("Only inputs of 11 to 20 accepted.");
            }
        }
        #endregion
    }
}
