import unittest

from part1 import findLowPointTotal
from part2 import findTotalOfBasin

class Test(unittest.TestCase):

    def testPart1Test(self):
        self.assertEqual(findLowPointTotal("test.txt"), 15)

    def testPart1Input(self):
        self.assertEqual(findLowPointTotal("input.txt"), 494)


    def testPart2Test(self):
        self.assertEqual(findTotalOfBasin("test.txt"), 1134)

    def testPart2Input(self):
        self.assertEqual(findTotalOfBasin("input.txt"), 1048128)
