import unittest

from PatternRemover import PatternRemover
from ProcessorAttempt2 import Processor

class Tests(unittest.TestCase):

    def test_the_test_input(self):
        test = Processor('test_input.txt')
        self.assertEqual(6, test.getAnswer())

    def test_the_pattern_input(self):
        test = PatternRemover('pattern_input.txt')
        self.assertEqual(424, test.getAnswer())

    def test_the_pattern_input2(self):
        test = PatternRemover('pattern_input2.txt')
        self.assertEqual(0, test.getAnswer())

    def test_the_real_input(self):
        test = Processor('real_input.txt')
        self.assertEqual(318, test.getAnswer())

    def test_the_real_input2(self):
        test = Processor('real_input2.txt')
        self.assertEqual(206, test.getAnswer())

