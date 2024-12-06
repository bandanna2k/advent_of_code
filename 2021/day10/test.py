import unittest

from part1 import getError
from part1 import getScore
from part2 import getCompleteString
from part2 import getCompletionScore

class Test(unittest.TestCase):

    def testGood(self):
        self.assertEqual(getError("{}<>()[]"), None)

    def testgetCompleteString1(self):
        self.assertEqual(getCompleteString("[({(<(())[]>[[{[]{<()<>>"), "}}]])})]")

    def testgetCompleteString2(self):
        self.assertEqual(getCompleteString("[(()[<>])]({[<{<<[]>>("), ")}>]})")

    def testgetCompleteString3(self):
        self.assertEqual(getCompleteString("(((({<>}<{<{<>}{[]{[]{}"), "}}>}>))))")

    def testgetCompleteString4(self):
        self.assertEqual(getCompleteString("{<[[]]>}<{[{[{[]{()[[[]"), "]]}}]}]}>")

    def testgetCompleteString5(self):
        self.assertEqual(getCompleteString("<{([{{}}[<[[[<>{}]]]>[]]"), "])}>")

    def testGetError1(self):
        self.assertEqual(getError("{([(<{}[<>[]}>{[]{[(<()>"), "}")

    def testGetError2(self):
        self.assertEqual(getError("[[<[([]))<([[{}[[()]]]"), ")")

    def testGetError3(self):
        self.assertEqual(getError("[{[{({}]{}}([{[{{{}}([]"), "]")

    def testGetError4(self):
        self.assertEqual(getError("[<(<(<(<{}))><([]([]()"), ")")

    def testGetError5(self):
        self.assertEqual(getError("<{([([[(<>()){}]>(<<{{"), ">")

    def testPart1Test(self):
        self.assertEqual(getScore("test.txt"), 26397)

    def testPart1Input(self):
        self.assertEqual(getScore("input.txt"), 311949)

    def testPart2Test(self):
        self.assertEqual(getCompletionScore("test.txt"), 288957)

    def testPart2Input(self):
        self.assertEqual(getCompletionScore("input.txt"), 3042730309)
