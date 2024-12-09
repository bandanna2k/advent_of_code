from tabnanny import check

import sys
from Common.RowInput import RowInput
import re

class Processor:

    answer = 0

    def getAnswer(self):
        return self.answer

    def __init__(self, filename):

        with open(filename) as fp:
            for line in fp:
                for value in list(line.strip()):
                    print(value)