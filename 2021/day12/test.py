import unittest

from part1 import getPathsCount
from part2 import getPart2

class Test(unittest.TestCase):

    def testPart1Test(self):
        self.assertEqual(getPathsCount("test.txt"), 10)

    def testPart1Test2(self):
        self.assertEqual(getPathsCount("test2.txt"), 19)

    def testPart1Test3(self):
        self.assertEqual(getPathsCount("test3.txt"), 226)

    def testPart1Input(self):
        self.assertEqual(getPathsCount("input.txt"), 4970)


    def testPart2Test(self):
        self.assertEqual(getPart2("test.txt"), 36)

    def testPart2Test(self):
        self.assertEqual(getPart2("test2.txt"), 103)

    def testPart2Test(self):
        self.assertEqual(getPart2("test3.txt"), 3509)

    def testPart2Input(self):
        self.assertEqual(getPart2("input.txt"), 137948)
