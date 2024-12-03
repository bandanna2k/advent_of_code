import unittest

from Part1 import Part1

class PartOneTest(unittest.TestCase):

    def test_part1_test_input(self):
        partOne = Part1('test_input.txt')
        self.assertEqual(partOne.getAnswer(), 161)

    def test_part1_real_input(self):
        partOne = Part1('real_input.txt')
        self.assertEqual(partOne.getAnswer(), 153469856)
