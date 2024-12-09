using System;
using System.Collections.Generic;
using System.Xml.Schema;
using NUnit.Framework;
using Project_Euler.Source;

namespace Project_Euler.Tests._000_099
{
    /*
Largest exponential

Problem 99
Comparing two numbers written in index form like 211 and 37 is not difficult, 
as any calculator would confirm that 2^11 = 2048 < 3^7 = 2187.

However, confirming that 632382^518061 > 519432^525806 would be much more difficult, 
as both numbers contain over three million digits.

Using base_exp.txt (right click and 'Save Link/Target As...'), a 22K text file 
containing one thousand lines with a base/exponent pair on each line, 
determine which line number has the greatest numerical value.

NOTE: The first two lines in the file represent the numbers in the example given above.
*/
    [TestFixture()]
    public class TestQuestion0099
    {
        [Test]
        public void TestLargestExponential()
        {
            string CSV_FILE = Properties.Resources.P0099_base_exp;
            string[] separatorArray = new string[] { Environment.NewLine, "\n" };

            string[] numbers;

            Int64 fileValue;
            Int64 fileExponent;
            Int64 maxLineNumber = 0;
            double maxValue = double.MinValue;
            double value;

            string[] lines = CSV_FILE.Split(separatorArray, StringSplitOptions.RemoveEmptyEntries);
            string line;

            for(int i = 0 ; i < lines.Length ; i++)
            {
                line = lines[i];

                numbers = line.Split(',');
                fileValue = Convert.ToInt64(numbers[0]);
                fileExponent = Convert.ToInt64(numbers[1]);

                value = fileExponent * Math.Log10(fileValue);

                if (value > maxValue)
                {
                    maxValue = value;
                    maxLineNumber = (i + 1);
                }
            }
            Assert.That(maxLineNumber,Is.EqualTo(709));
        }
    }
}
