import unittest

from Processor import Processor

class Tests(unittest.TestCase):

    def test_the_test_input(self):
        test = Processor('test_input.txt')
        self.assertEqual(1928, test.getAnswer())

    def test_the_test_input2(self):
        test = Processor('test_input2.txt')
        self.assertEqual(60, test.getAnswer())

    def test_the_test_input3(self):
        test = Processor('test_input3.txt')
        self.assertEqual(4006, test.getAnswer())

    def test_the_real_input(self):
        test = Processor('real_input.txt')
        self.assertEqual(92349417108, test.getAnswer())

