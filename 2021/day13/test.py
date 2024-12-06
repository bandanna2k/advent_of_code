import unittest

from fileIO import myPrint
from part1 import getFolderPaperScore, getNewGrid, getSizeX, getSizeY
from part2 import getPart2

class Test(unittest.TestCase):

    def testFoldedPaperScoreTest(self):
        self.assertEqual(getFolderPaperScore("test.txt", 1), 17)

    def testFoldedPaperScoreInput(self):
        self.assertEqual(getFolderPaperScore("input.txt", 1), 610)

    def testFoldedPaperScoreInput(self):
        self.assertEqual(getFolderPaperScore("input.txt", 100), 95)

    @unittest.skip("Not implemented")
    def testPart2Test(self):
        self.assertEqual(getPart2("test.txt"), "X")

    @unittest.skip("Not implemented")
    def testPart2Input(self):
        self.assertEqual(getPart2("input.txt"), "X")


    def testNewGrid(self):
        grid = getNewGrid(10, 2)
        myPrint(grid)

        self.assertEqual(len(grid[0]), 10)
        self.assertEqual(len(grid), 2)
        self.assertEqual(getSizeX(grid), 10)
        self.assertEqual(getSizeY(grid), 2)
        self.assertEqual(grid[2-1][10-1], 0)

