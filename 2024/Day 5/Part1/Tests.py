import unittest

from Processor import Processor

class Tests(unittest.TestCase):

    def test_the_test_input(self):
        test = Processor('test_input_rules.txt', 'test_input_updates.txt')
        self.assertEqual(test.getAnswer(), 143)

    def test_the_real_input(self):
        test = Processor('real_input_rules.txt', 'real_input_updates.txt')
        self.assertEqual(test.getAnswer(), 42)

