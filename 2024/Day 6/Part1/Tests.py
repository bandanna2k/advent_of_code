import unittest

from Processor import Processor

class Tests(unittest.TestCase):

    def test_the_test_input(self):
        test = Processor('test_input.txt')
        self.assertEqual(test.getAnswer(), 41)

    def test_the_real_input(self):
        test = Processor('real_input.txt')
        self.assertEqual(test.getAnswer(), 42)

