import sys
from Common.RowInput import RowInput
import re

class Processor:

    answer = 0

    def __init__(self, filename):

        with (open(filename) as fp):
            rows = []
            for line in fp:
                row = ([' '] * 3) + list(line.strip()) + ([' '] * 3)
                rows = rows + [row]

            width = rows[0].__len__() - 6
            height = rows.__len__()

            for x in range(3):
                rows = [([' '] * (width + 6))] + rows
            for x in range(3):
                rows = rows + [([' '] * (width + 6))] + rows

            count = 0
            for x in range(3,width+3):
                # print('---------')
                for y in range(3,height+3):
                    # print("{} {}".format(x, y))
                    assert rows[y][x] != ' '

                    if 'M' == rows[y][x] and 'S' == rows[y][x+2] and 'A' == rows[y+1][x+1] and 'M' == rows[y+2][x] and 'S' == rows[y+2][x+2]:
                        count = count + 1
                    if 'S' == rows[y][x] and 'M' == rows[y][x+2] and 'A' == rows[y+1][x+1] and 'S' == rows[y+2][x] and 'M' == rows[y+2][x+2]:
                        count = count + 1
                    if 'S' == rows[y][x] and 'S' == rows[y][x+2] and 'A' == rows[y+1][x+1] and 'M' == rows[y+2][x] and 'M' == rows[y+2][x+2]:
                        count = count + 1
                    if 'M' == rows[y][x] and 'M' == rows[y][x+2] and 'A' == rows[y+1][x+1] and 'S' == rows[y+2][x] and 'S' == rows[y+2][x+2]:
                        count = count + 1
            self.answer = count


    def getAnswer(self):
        return self.answer



