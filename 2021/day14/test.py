import unittest

from part1 import getLetterFrequency, getAnswerBruteFast
from part2 import getAnswerByPairs


class Test(unittest.TestCase):

    def testPart1Test(self):
        self.assertEqual(getAnswerBruteFast("test.txt", 2), 5)
        self.assertEqual(getAnswerByPairs("test.txt", 2), 5)
        self.assertEqual(getAnswerBruteFast("test.txt", 10), 1588)
        self.assertEqual(getAnswerByPairs("test.txt", 10), 1588)

    def testPart1Input(self):
        self.assertGreater(getAnswerBruteFast("input.txt", 10), 2617)
        self.assertEqual(getAnswerBruteFast("input.txt", 10), 2740)
        self.assertEqual(getAnswerByPairs("input.txt", 10), 2740)

    def testPart1Simon(self):
        self.assertEqual(getAnswerBruteFast("simon.txt", 10), 2223)
        self.assertEqual(getAnswerByPairs("simon.txt", 10), 2223)

    def testPart2Test(self):
        self.assertEqual(getAnswerByPairs("test.txt", 40), 2188189693529)

    def testPart2Input(self):
        self.assertEqual(getAnswerByPairs("input.txt", 40), 2959788056211)
