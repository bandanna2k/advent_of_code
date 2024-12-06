import unittest

from part1 import getPart1
from part2 import getBigFlash

class Test(unittest.TestCase):

    def testPart1Test(self):
        self.assertEqual(getPart1("test.txt", 10), 204)

    def testPart1Test2(self):
        self.assertEqual(getPart1("test.txt", 100), 1656)

    def testPart1Input(self):
        self.assertEqual(getPart1("input.txt", 100), 1721)


    def testBigFlashTest(self):
        self.assertEqual(getBigFlash("test.txt"), 195)

    def testBigFlashInput(self):
        self.assertEqual(getBigFlash("input.txt"), 298)
