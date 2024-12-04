import sys
from Common.RowInput import RowInput
import re

class Processor:

    answer = 0

    def __init__(self, filename):

        with open(filename) as fp:
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

                    if 'X' == rows[y][x] and 'M' == rows[y][x+1] and 'A' == rows[y][x+2] and 'S' == rows[y][x+3]:
                        # print("W x{} y{}".format(x-3, y-3))
                        count = count + 1
                    if 'S' == rows[y][x] and 'A' == rows[y][x+1] and 'M' == rows[y][x+2] and 'X' == rows[y][x+3]:
                        # print("E x{} y{}".format(x-3, y-3))
                        count = count + 1

                    if 'X' == rows[y][x] and 'M' == rows[y+1][x] and 'A' == rows[y+2][x] and 'S' == rows[y+3][x]:
                        # print("S x{} y{}".format(x-3, y-3))
                        count = count + 1
                    if 'S' == rows[y][x] and 'A' == rows[y+1][x] and 'M' == rows[y+2][x] and 'X' == rows[y+3][x]:
                        # print("N x{} y{}".format(x-3, y-3))
                        count = count + 1

                    if 'X' == rows[y][x] and 'M' == rows[y+1][x+1] and 'A' == rows[y+2][x+2] and 'S' == rows[y+3][x+3]:
                        # print("SW x{} y{}".format(x-3, y-3))
                        count = count + 1
                    if 'S' == rows[y][x] and 'A' == rows[y+1][x+1] and 'M' == rows[y+2][x+2] and 'X' == rows[y+3][x+3]:
                        # print("NE x{} y{}".format(x-3, y-3))
                        count = count + 1

                    if 'X' == rows[y][x] and 'M' == rows[y-1][x-1] and 'A' == rows[y-2][x-2] and 'S' == rows[y-3][x-3]:
                        # print("NW x{} y{}".format(x-3, y-3))
                        count = count + 1
                    if 'S' == rows[y][x] and 'A' == rows[y-1][x-1] and 'M' == rows[y-2][x-2] and 'X' == rows[y-3][x-3]:
                        # print("SE x{} y{}".format(x-3, y-3))
                        count = count + 1
            self.answer = count


    def getAnswer(self):
        return self.answer



