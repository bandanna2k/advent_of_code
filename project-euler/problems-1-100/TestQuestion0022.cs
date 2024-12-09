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
Names scores

Problem 22
Using names.txt (right click and 'Save Link/Target As...'), 
a 46K text file containing over five-thousand first names, 
begin by sorting it into alphabetical order. Then working 
out the alphabetical value for each name, multiply this value 
by its alphabetical position in the list to obtain a name score.

For example, when the list is sorted into alphabetical order, 
COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, 
is the 938th name in the list. So, COLIN would obtain a score 
of 938 × 53 = 49714.

What is the total of all the name scores in the file?
     */
    [TestFixture()]
    public class TestQuestion0022
    {
        [TestCase(871198282)]
        public void TestNamesScores(Int64 expectedSum)
        {
            Int64 sumOfNameScores = 0;

            SortedList listOfNames = new SortedList();
            foreach(string name in Properties.Resources.P0022_names.Split(','))
            {
                listOfNames.Add(name,name);
            }

            Int32 lineNo = 1;
            Int32 value;
            foreach(string name in listOfNames.Values)
            {
                foreach (char c in name.Replace("\"","").ToCharArray())
                {
                    value = GetAlphabetIndex(c);
                    if (value < 0) throw new ApplicationException();
                    sumOfNameScores += (lineNo * value);
                }

                lineNo++;
            }

            Assert.That(sumOfNameScores,Is.EqualTo(expectedSum));
        }

        private static Int32 GetAlphabetIndex(char c)
        {
            const string ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            const string alphabet = "abcdefghijklmnopqrstuvwxyz";

            Int32 index;
            index = ALPHABET.IndexOf(c);
            if(index >= 0) 
                return index + 1;
            index = alphabet.IndexOf(c);
            if (index >= 0)
                return index + 1;
            return index;
        }
        [TestCase('a', 1)]
        [TestCase('b', 2)]
        [TestCase('A', 1)]
        [TestCase('B', 2)]
        [TestCase('z', 26)]
        [TestCase('Z', 26)]
        [TestCase(' ', -1)]
        public void TestGetAlphabetIndex(char c, Int32 expected)
        {
            Assert.That(GetAlphabetIndex(c),Is.EqualTo(expected));
        }
    }
}
