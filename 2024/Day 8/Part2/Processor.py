from tabnanny import check

import sys
from Common.RowInput import RowInput
import re

class Processor:

    answer = 0

    antinodes = {}

    def getAnswer(self):
        return self.answer

    def __init__(self, filename):

        width = None
        height = 0

        row = 0
        letterToAntennea = {}
        with open(filename) as fp:
            for line in fp:
                chars = list(line.strip())
                width = chars.__len__()
                for col in range(chars.__len__()):

                    letter = chars[col]
                    if letter == '.':
                        continue

                    antennea = letterToAntennea.get(letter)
                    if antennea:
                        letterToAntennea[letter] = antennea + [(row, col)]
                    else:
                        letterToAntennea[letter] = [(row, col)]

                row = row + 1
                height = height + 1
        # print(letterToAntennea)

        print("Width:{}, Height:{}", width, height)

        for letter in letterToAntennea.keys():
            antennea = letterToAntennea[letter]
            if antennea.__len__() == 1:
                continue
            self.check(antennea, 0, 0)

        # Remove overlaps
        # for letter in letterToAntennea.keys():
        #     antennea = letterToAntennea[letter]
        #     for pos in antennea:
        #         key = "{},{}".format(pos[0], pos[1])
        #         if self.antinodes.get(key):
        #             print("Overlap {}".format(key))
        #             self.antinodes.pop(key)

        # for key in self.antinodes:
        #     print("{} {}".format(key, self.antinodes[key]))

        # Remove out of range
        for key in list(self.antinodes.keys()):
            pos = self.antinodes[key]

            if pos[0] < 0 or pos[0] >= width:
                del self.antinodes[key]
            elif pos[1] < 0 or pos[1] >= height:
                del self.antinodes[key]

        # for key in self.antinodes:
        #     print("{} {}".format(key, self.antinodes[key]))

        self.answer = self.antinodes.__len__()


    def check(self, antennea, i, j):

        if i == j:
            self.check(antennea, i + 1, j)
            self.check(antennea, i, j + 1)
            return

        if i >= antennea.__len__():
            return

        if j >= antennea.__len__():
            return

        a = antennea[i]
        b = antennea[j]

        for k in range(0, 50):

            c = (a[0] - ((k * b[0]) - (k * a[0])), a[1] - ((k * b[1]) - (k * a[1])))
            d = (b[0] + ((k * b[0]) - (k * a[0])), b[1] + ((k * b[1]) - (k * a[1])))

            # print("{} - {} {} - {}".format(c, a, b, d))
            self.antinodes = self.antinodes | {"{},{}".format(c[0], c[1]): (c[0], c[1])}
            self.antinodes = self.antinodes | {"{},{}".format(d[0], d[1]): (d[0], d[1])}

        self.check(antennea, i + 1, j)
        self.check(antennea, i, j + 1)