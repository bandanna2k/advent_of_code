using System;
using System.Collections.Generic;
using NUnit.Framework;

namespace Project_Euler.Tests._000_099
{
    /*
Longest Collatz sequence

Problem 14
The following iterative sequence is defined for the set of positive integers:

n → n/2 (n is even)
n → 3n + 1 (n is odd)

Using the rule above and starting with 13, we generate the following sequence:

13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.

Which starting number, under one million, produces the longest chain?

NOTE: Once the chain starts the terms are allowed to go above one million.
     */
    [TestFixture()]
    public class TestQuestion0014
    {
        [Category("Slow")]
        [TestCase(1, 1, 1)]
        [TestCase(2, 2, 2)]
        [TestCase(3, 3, 8)]
        [TestCase(837799, 837799, 525)]
        [TestCase(1000000, 837799, 525)]
        public void TestLongestCollatzSequence(Int32 limit,
                                               Int32 expectedMaxValue,
                                               Int32 expectedMaxCount)
        {
            Int64[] sequence;
            Int32 maxCount = Int32.MinValue;
            Int64 valueAtMaxCount = -1;

            //for (int i = limit ; i > 0 ; i--)
            for (int i = 1; i <= limit ; i++)
            {
                sequence = GetCollatzSequence(i);
                if (sequence.Length > maxCount)
                {
                    maxCount = sequence.Length;
                    valueAtMaxCount = i;
                }
            }
            Assert.That(valueAtMaxCount, Is.EqualTo(expectedMaxValue));
            Assert.That(maxCount, Is.EqualTo(expectedMaxCount));
        }

        private Int64 GetNextCollatzValue(Int64 value)
        {
            if (value <= 1) return 1;

            if ((value % 2) == 0)
            {
                return value / 2;
            }
            else
            {
                return (value * 3) + 1;
            }
        }

        private Int64[] GetCollatzSequence(Int64 input)
        {
            List<Int64> listOfValues = new List<Int64>();
            Int64 value = input;

            if( input <= 0 ) throw new ApplicationException("Values of 1 or greater only.");
            
            while (value >= 2)
            {
                listOfValues.Add(value);
                value = GetNextCollatzValue(value);
            }

            listOfValues.Add(1);
            return listOfValues.ToArray();
        }

        [TestCase(1, 1, 1)]
        [TestCase(2, 2, 2, 1)]
        [TestCase(3, 8, 3, 10,5,16,8,4,2,1)]
        public void TestGetCollatzSequence(Int64 input,
                                           Int32 expectedCount,
                                           params Int64[] expectedSequence)
        {
            Int64[] sequence = GetCollatzSequence(input);
            Assert.That(sequence.Length,Is.EqualTo(expectedCount));
            Assert.That(sequence,Is.EqualTo(expectedSequence));
        }
    }
}
