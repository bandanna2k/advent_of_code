import sys
from Common.RowInput import RowInput
import re

class Processor:

    ruleSet = set
    answer = 0

    def getAnswer(self):
        return self.answer

    def __init__(self, rulesFilename, updatesFilename):

        with open(rulesFilename) as fp:
            for line in fp:
                self.ruleSet = self.ruleSet.union({line.strip('\n')})

        goodUpdates = []
        badUpdates = []
        with open(updatesFilename) as fp:
            for line in fp:

                update = [int(x) for x in line.split(",")]

                if self.isGood(update):
                    goodUpdates = goodUpdates + [update]
                else:
                    badUpdates = badUpdates + [update]

        newUpdates = []
        counter = 0
        for badUpdate in badUpdates:
            counter = counter + 1
            print("{} of {}".format(counter, badUpdates.__len__()))
            newUpdate = badUpdate
            while not self.isGood(newUpdate):
                newUpdate = self.maybeSwitchFirstBadRule(newUpdate)
            newUpdates = newUpdates + [newUpdate]

        for newUpdate in newUpdates:
            halfLen = (newUpdate.__len__() - 1) / 2
            self.answer = self.answer + newUpdate[int(halfLen)]

    def isGood(self, update):
        for first in range(update.__len__()):
            for second in range(first, update.__len__()):
                if first == second:
                    continue

                no1 = update[first]
                no2 = update[second]
                # print("{} {}".format(no1, no2))

                if "{}|{}".format(no2, no1) in self.ruleSet:
                    return False
        return True

    def maybeSwitchFirstBadRule(self, update):
        for first in range(update.__len__()):
            for second in range(first, update.__len__()):
                if first == second:
                    continue

                no1 = update[first]
                no2 = update[second]
                # print("{} {}".format(no1, no2))

                if "{}|{}".format(no2, no1) in self.ruleSet:
                    newUpdate = update.copy()
                    newUpdate[first] = no2
                    newUpdate[second] = no1
                    return newUpdate
        return update


