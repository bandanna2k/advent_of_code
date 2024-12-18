import unittest

from Processor import Processor

class Tests(unittest.TestCase):

    def test_the_test_input(self):
        test = Processor('test_input.txt')
        self.assertEqual(34, test.getAnswer())

    def test_the_test_input2(self):
        test = Processor('test_input2.txt')
        self.assertEqual(9, test.getAnswer())

    def test_the_real_input(self):
        test = Processor('real_input.txt')
        self.assertEqual(809, test.getAnswer())

