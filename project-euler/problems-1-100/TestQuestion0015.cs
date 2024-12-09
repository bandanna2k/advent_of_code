using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using NUnit.Framework;
using Project_Euler.Source;

namespace Project_Euler.Tests._000_099
{
    /*
Lattice paths

Problem 15
Starting in the top left corner of a 2×2 grid, 
and only being able to move to the right and down, 
there are exactly 6 routes to the bottom right corner.

How many such routes are there through a 20×20 grid?     
*/
    [TestFixture()]
    public class TestQuestion0015
    {
        [TestCase(2, 2, 6)]
        [TestCase(3, 3, 20)]
        [TestCase(4, 4, 70)]
        public void TestLatticePaths_BruteForce(Int32 squareCountX,
            Int32 squareCountY,
            Int32 expectedRoutes)
        {
            List<char> listOfRoutes = new List<char>();
            string[] routes;

            for (int i = 0; i < squareCountX; i++) listOfRoutes.Add('R');
            for (int i = 0; i < squareCountY; i++) listOfRoutes.Add('D');

            routes = HelperFunctions.GetPermutations(new String(listOfRoutes.ToArray()));
            routes = routes.Distinct().ToArray();

            Assert.That(routes.Length, Is.EqualTo(expectedRoutes));
        }

        [TestCase(2, 6)]
        [TestCase(3, 20)]
        [TestCase(4, 70)]
        [TestCase(20, 137846528820)]
        public void TestLatticePaths(Int64 squareCount,
                                     Int64 expectedRoutes)
        {
            BigInteger stepsRight = new BigInteger(squareCount);
            BigInteger stepsDown = new BigInteger(squareCount);
            BigInteger expected = new BigInteger(expectedRoutes);

            // To get to the end, we need an equal amount
            BigInteger permutations = HelperFunctions.Factorial(stepsRight+stepsDown) /
                (HelperFunctions.Factorial(stepsRight) * HelperFunctions.Factorial(stepsDown));

            Assert.That(permutations,Is.EqualTo(expected));
        }
    }
}
