import unittest

from testTemplate.solutions import getSolution


class Test(unittest.TestCase):

    def testPart1Test(self):
        self.assertEqual(getSolution("test.txt"), 0)

    def testPart1Input(self):
        self.assertEqual(getSolution("input.txt"), 0)

    @unittest.skip("Not implemented")
    def testPart2Test(self):
        self.assertEqual(getSolution("test.txt"), -1)

    @unittest.skip("Not implemented")
    def testPart2Input(self):
        self.assertEqual(getSolution("input.txt"), -1)
