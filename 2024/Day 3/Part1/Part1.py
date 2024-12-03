import sys
from Common.RowInput import RowInput
import re

class Part1:

    answer = 0

    def __init__(self, filename):

        with open(filename) as fp:
            for line in fp:
                self.processLine(line)

    def processLine(self, line):
        muls = re.findall("mul\(\d+,\d+\)", line)
        for mul in muls:
            values = re.findall("(\d+)", mul)
            # print(values)
            assert values.__len__() == 2
            v = int(values[0]) * int(values[1])
            self.answer += v

    def getAnswer(self):
        return self.answer



