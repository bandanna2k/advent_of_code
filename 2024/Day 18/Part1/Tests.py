import unittest

from Processor import Processor

class Tests(unittest.TestCase):

    def test_the_test_input_12(self):
        test = Processor('test_input_12.txt', (6, 6))
        self.assertEqual(22, test.getAnswer())

    def test_the_real_input(self):
        test = Processor('real_input_1024.txt', (70, 70))
        self.assertEqual(318, test.getAnswer())

