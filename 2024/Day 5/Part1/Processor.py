import sys
from Common.RowInput import RowInput
import re

class Processor:

    answer = 0

    def getAnswer(self):
        return self.answer

    def __init__(self, rulesFilename, updatesFilename):

        rules = []
        with open(rulesFilename) as fp:
            for line in fp:
                split = line.split("|")
                rules = rules + [[int(split[0]), int(split[1])]]


        goodUpdates = []
        with open(updatesFilename) as fp:
            for line in fp:
                goodUpdate = True

                updates = [int(x) for x in line.split(",")]
                for first in range(updates.__len__()):
                    for second in range(first, updates.__len__()):
                        if first == second:
                            continue

                        no1 = updates[first]
                        no2 = updates[second]
                        # print("{} {}".format(no1, no2))

                        for rule in rules:
                            before = rule[0]
                            after = rule[1]

                            if before == no2 and after == no1:
                                goodUpdate = False
                                break
                    # print("--------")
                    if not goodUpdate:
                        break
                if goodUpdate:
                    goodUpdates = goodUpdates + [updates]

        for update in goodUpdates:
            halfLen = (update.__len__() - 1) / 2
            # print(update[int(halfLen)])
            self.answer = self.answer + update[int(halfLen)]



