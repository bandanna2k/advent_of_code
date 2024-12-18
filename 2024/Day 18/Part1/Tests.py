import unittest

from Processor import Processor

class Tests(unittest.TestCase):

    def test_the_test_input(self):
        test = Processor('test_input.txt', (6, 6))
        self.assertEqual(1928, test.getAnswer())

    def Xtest_the_real_input(self):
        test = Processor('real_input.txt', (6, 6))
        self.assertEqual(92349417108, test.getAnswer())

