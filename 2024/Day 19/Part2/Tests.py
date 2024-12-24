import unittest

from PatternRemover2 import PatternRemover2

class Tests(unittest.TestCase):

    def test_pattern_remover(self):
        test = PatternRemover2('pattern_input.txt')
        self.assertEqual(424, test.getAnswer())
