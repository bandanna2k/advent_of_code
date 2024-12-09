using System;
using NUnit.Framework;

namespace Project_Euler.Tests._000_099
{
    /*
    Su Doku

Problem 96
Su Doku (Japanese meaning number place) is the name given to a 
popular puzzle concept. Its origin is unclear, but credit must be attributed 
to Leonhard Euler who invented a similar, and much more difficult, 
puzzle idea called Latin Squares. The objective of Su Doku puzzles, 
however, is to replace the blanks (or zeros) in a 9 by 9 grid in such 
that each row, column, and 3 by 3 box contains each of the digits 1 to 9. 
Below is an example of a typical starting puzzle grid and its solution grid.

Problem
0 0 3	0 2 0	6 0 0
9 0 0   3 0 5   0 0 1
0 0 1   8 0 6   4 0 0

0 0 8   1 0 2   9 0 0
7 0 0   0 0 0   0 0 8
0 0 6	7 0 8   2 0 0

0 0 2   6 0 9   5 0 0
8 0 0   2 0 3   0 0 9
0 0 5	0 1 0   3 0 0

Answer
4 8 3   9 2 1   6 5 7
9 6 7   3 4 5   8 2 1
2 5 1	8 7 6   4 9 3

5 4 8   1 3 2   9 7 6
7 2 9   5 6 4   1 3 8
1 3 6	7 9 8   2 4 5 

3 7 2   6 8 9   5 1 4
8 1 4   2 5 3   7 6 9   
6 9 5	4 1 7   3 8 2

A well constructed Su Doku puzzle has a unique solution and can be solved 
by logic, although it may be necessary to employ "guess and test" methods 
in order to eliminate options (there is much contested opinion over this). 
The complexity of the search determines the difficulty of the puzzle; the 
example above is considered easy because it can be solved by straight 
forward direct deduction.

The 6K text file, sudoku.txt (right click and 'Save Link/Target As...'), 
contains fifty different Su Doku puzzles ranging in difficulty, but all 
with unique solutions (the first puzzle in the file is the example above).

By solving all fifty puzzles find the sum of the 3-digit numbers found in 
the top left corner of each solution grid; for example, 483 is the 3-digit 
number found in the top left corner of the solution grid above.
  */
    [TestFixture()]
    public class TestQuestion0096
    {
        const Int32 MaxX = 9;
        const Int32 MaxY = 9;
        private Int32[] Values = {1,2,3,4,5,6,7,8,9};

        [TestCase(0)]
        public void TestSuDoku(Int64 expected)
        {

        }




        public Int32[,] ConvertStringArrayToGrid(string[] rows)
        {
            if(rows.Length != 9)
                throw new ApplicationException("Invalid amount of rows.");

            Int32[,] result = new Int32[9, 9];
            for(Int32 y = 0 ; y < 9 ; y++)
            {
                char[] row = rows[y].ToCharArray();
                for (Int32 x = 0; x < 9; x++)
                {
                    result[x, y] = (Int32)Char.GetNumericValue(row[x]);
                }
            }
            return result;
        }

        #region Convert StringArray to Grid
        [TestCase(405,"111111111,222222222,333333333,444444444,555555555,666666666,777777777,888888888,999999999")]
        public void TestConvertStringArrayToGrid(
            Int32 expectedSum,
            string gridAsString)
        {
            string[] gridAsRows = gridAsString.Split(',');
            Int32[,] grid = ConvertStringArrayToGrid(gridAsRows);
            Int32[,] result = new Int32[MaxX, MaxY];

            Assert.That(grid.GetLength(0),Is.EqualTo(MaxX));
            Assert.That(grid.GetLength(1), Is.EqualTo(MaxY));

            Int32 sum = 0;
            for (Int32 x = 0; x < MaxX; x++)
            {
                for (Int32 y = 0; y < MaxY; y++)
                {
                    sum += grid[x,y];
                }
            }
            Assert.That(sum, Is.EqualTo(expectedSum));
        }
        #endregion

        #region "Complete Methods
        public Int32[,] CompleteSuDuko(Int32[,] grid)
        {
            if (!IsGridValid(grid))
                return null;

            Int32[,] result;
            result = CompleteRows(grid);

            return result;
        }

        public Int32[,] CompleteRows(Int32[,] grid)
        {
            Int32[,] result = new Int32[9,9];
            for (int x = 0; x < MaxX; x++)
            {

            }
            return result;
        }
        #endregion

        public bool IsGridValid(Int32[,] grid)
        {
            if (grid.GetLength(0) == 9 &&
                grid.GetLength(1) == 9)
                return true;
            else
                return true;
        }
    }
}
