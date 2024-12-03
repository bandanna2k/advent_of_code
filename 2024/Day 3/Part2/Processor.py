import sys
from Common.RowInput import RowInput
import re

class Processor:

    answer = 0
    multiplier = 1

    def __init__(self, filename):

        with open(filename) as fp:
            for line in fp:
                self.processLine(line)

    def processLine(self, line):
        muls = re.findall("mul\(\d+,\d+\)|don't|do", line)
        for mul in muls:

            if mul == "do":
                self.multiplier = 1
            elif mul == "don't":
                self.multiplier = 0
            else:
                values = re.findall("(\d+)", mul)
                v = self.multiplier * int(values[0]) * int(values[1])
                self.answer += v

            print("{} {}".format(mul, self.multiplier))

    def getAnswer(self):
        return self.answer



