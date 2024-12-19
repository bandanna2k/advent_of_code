import unittest

from Processor import Processor

class Tests(unittest.TestCase):

    def test_the_test_input(self):
        test = Processor('test_input.txt', (6, 6), 12)
        self.assertEqual((6, 1), test.getAnswer())

    def test_the_real_input(self):
        test = Processor('real_input.txt', (70, 70), 1024)
        self.assertEqual((56, 29), test.getAnswer())

