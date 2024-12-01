from PartOne import PartOne
from PartTwo import PartTwo
import unittest

class PartOneTest(unittest.TestCase):

    partOne = None
    partTwo = None

    def testPartOneTest(self):
        self.partOne = PartOne('test_input.txt')
        self.partOne.read()
        self.assertEqual(self.partOne.differences(), 11)

    def testPartOneReal(self):
        self.partOne = PartOne('real_input.txt')
        self.partOne.read()
        self.assertEqual(self.partOne.differences(), 1722302)

    def testPart2Test(self):
        self.partTwo = PartTwo('test_input.txt')
        self.partTwo.read()
        self.assertEqual(self.partTwo.differences(), 31)

    def testPart2Real(self):
        self.partTwo = PartTwo('real_input.txt')
        self.partTwo.read()
        self.assertEqual(self.partTwo.differences(), 20373490)
