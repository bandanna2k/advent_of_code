import unittest

from Part1 import Part1

class PartOneTest(unittest.TestCase):

    def test_input(self):
        partOne = Part1('test_input.txt')
        self.assertEqual(partOne.getSafeRows(), 2)

    def test_input2(self):
        partOne = Part1('test_input2.txt')
        self.assertEqual(partOne.getSafeRows(), 3)

    def test_real_input(self):
        partOne = Part1('real_input.txt')
        self.assertEqual(partOne.getSafeRows(), 211)

