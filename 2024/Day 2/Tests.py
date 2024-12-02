import unittest

from Part1 import Part1
from Part2 import Part2

class PartOneTest(unittest.TestCase):

    def test_part1_test_input(self):
        partOne = Part1('test_input.txt')
        self.assertEqual(partOne.getSafeRows(), 2)

    def test_part1_test_input2(self):
        partOne = Part1('test_input2.txt')
        self.assertEqual(partOne.getSafeRows(), 3)

    def test_part1_real_input(self):
        partOne = Part1('real_input.txt')
        self.assertEqual(partOne.getSafeRows(), 269)


    def test_part2_test_input(self):
        test = Part2('test_input.txt')
        self.assertEqual(test.getSafeRows(), 4)

    def test_part2_real_input(self):
        test = Part2('real_input.txt')
        self.assertEqual(test.getSafeRows(), 337)

