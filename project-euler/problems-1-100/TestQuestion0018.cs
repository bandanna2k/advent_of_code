using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Security.Claims;
using System.Text;
using NUnit.Framework;
using Project_Euler.Source;

namespace Project_Euler.Tests._000_099
{
    /*
Maximum path sum I

Problem 18
By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.

3
7 4
2 4 6
8 5 9 3

That is, 3 + 7 + 4 + 9 = 23.

Find the maximum total from top to bottom of the triangle below:
 */
    [TestFixture()]
    public class TestQuestion0018
    {
        private const string GridSmall = "3, 7, 4, 2, 4, 6, 8, 5, 9, 3";
        private const string GridMedium = "75,95,64,17,47,82,18,35,87,10,20,04,82,47,65,19,01,23,75,03,34,88,02,77,73,07,63,67,99,65,04,28,06,16,70,92,41,41,26,56,83,40,80,70,33,41,48,72,33,47,32,37,16,94,29,53,71,44,65,25,43,91,52,97,51,14,70,11,33,28,77,73,17,78,39,68,17,57,91,71,52,38,17,14,91,43,58,50,27,29,48,63,66,04,68,89,53,67,30,73,16,69,87,40,31,04,62,98,27,23,09,70,98,73,93,38,53,60,04,23";

        [TestCase(4, GridSmall, 23)]
        [TestCase(15, GridMedium, 1074)]
        public void TestMaximumPathSum1(Int32 size, string grid, Int64 expected)
        {
            GridSummation gs = new GridSummation(size);
            string[] valuesAsArray = grid.Split(new char[] { ',' }, StringSplitOptions.RemoveEmptyEntries);
            Int64[] values = valuesAsArray.Select(x => Convert.ToInt64(x)).ToArray();
            gs.SetAllValues(values);
            gs.Collapse();
            Assert.That(gs.FirstItem,Is.EqualTo(expected));
        }
        [Test]
        public void TestMaximumPathSumII()
        {
            string gridLarge;
            gridLarge = Properties.Resources.P0067_triangle;
            gridLarge = gridLarge.Replace(" ", ",");
            gridLarge = gridLarge.Replace("\n", ",");

            TestMaximumPathSum1(100,gridLarge , 7273);
        }

        #region Grid Summation
        class GridSummation
        {
            private TriangleGrid<Int64> mGrid;

            public GridSummation(Int32 size)
            {
                mGrid = new TriangleGrid<Int64>(size);
            }

            public void SetAllValues(Int64[] values)
            {
                mGrid.SetAllValues(values);
            }

            public void Collapse()
            {
                Int32 colsLimit = mGrid.Rows;
                Int64 left, right, above;

                for (int row = (mGrid.Rows - 1) ; row > 0 ; row--)
                {
                    // Set top row
                    for(int col = (colsLimit - 1) ; col > 0 ; col--)
                    {
                        right = mGrid.GetValue(row, col);
                        left = mGrid.GetValue(row, col - 1);
                        above = mGrid.GetValue(row - 1, col - 1);

                        above += Math.Max(left, right);
                        
                        mGrid.SetValue(row, col, right);
                        mGrid.SetValue(row, col-1, left);
                        mGrid.SetValue(row -1, col - 1, above);
                    }

                    // Clear bottom row
                    for (int col = (colsLimit - 1); col > 0; col--)
                    {
                        mGrid.SetValue(row, col, 0);
                        mGrid.SetValue(row, col - 1, 0);
                    }

                    colsLimit--;
                }
            }

            public Int64 FirstItem { get { return mGrid.GetValue(0, 0);}}
        }
        #endregion

        #region Triangle Grid
        [TestCase(3)]
        public void TestGridCreation(Int32 size)
        {
            TriangleGrid<Int64> tg = new TriangleGrid<Int64>(size);
            tg.SetValue(1, 1, 1);
            tg.SetValue(2, 2, 2);
            Assert.Throws<IndexOutOfRangeException>(() => tg.SetValue(1, 2, 0));
            Assert.Throws<IndexOutOfRangeException>(() => tg.SetValue(4, 1, 0));
        }

        #region Creation
        [TestCase(3, 7, 4, 2, 4, 6, 8, 5, 9, 3)]
        public void TestGridCreation(params Int64[] values)
        {
            TriangleGrid<Int64> tg = new TriangleGrid<Int64>(4);
            tg.SetAllValues(values);

            Assert.That(tg.GetValue(3, 3), Is.EqualTo(3));
        }
        [TestCase(4, GridSmall, 3, 3, 3)]
        [TestCase(15, GridMedium, 14, 14, 23)]
        public void TestGridCreation(Int32 size, string valuesAsString, Int32 row, Int32 col, Int64 expected)
        {
            TriangleGrid<Int64> tg = new TriangleGrid<Int64>(size);
            string[] valuesAsArray = valuesAsString.Split(new char[] { ',' }, StringSplitOptions.RemoveEmptyEntries);
            Int64[] values = valuesAsArray.Select(x => Convert.ToInt64(x)).ToArray();
            tg.SetAllValues(values);

            Assert.That(tg.GetValue(row, col), Is.EqualTo(expected));
        }
        #endregion

        class TriangleGrid<T>
        {
            T[][] mGrid;

            public TriangleGrid(Int32 size)
            {
                List<T[]> listOfRows = new List<T[]>();
                for (int i = 0; i < size; i++)
                {
                    T[] row = new T[i + 1];
                    listOfRows.Add(row);
                }
                mGrid = listOfRows.ToArray();
            }

            public Int32 Rows
            {
                get { return mGrid.Length; }
            }

            public T GetValue(Int32 row, Int32 col)
            {
                return mGrid[row][col];
            }
            public void SetValue(Int32 row, Int32 col, T value)
            {
                mGrid[row][col] = value;
            }

            public void SetAllValues(T[] values)
            {
                int rowIndex = 0;
                int colIndex = 0;
                for (int i = 0; i < values.Length; i++)
                {
                    SetValue(rowIndex, colIndex, values[i]);

                    // Set the next row and col
                    T[] row = mGrid[rowIndex];
                    if (row.Length == (colIndex + 1))
                    {
                        rowIndex++;
                        colIndex = 0;
                    }
                    else
                    {
                        colIndex++;
                    }
                }
            }
        }
        #endregion
    }
}
