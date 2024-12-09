from sre_constants import error
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
                expansion1 = []
                counter = 0
                id = 0
                isFile = True
                for value in list(line.strip()):
                    expansion1 = expansion1 + [(counter, int(value), id if isFile else None)]
                    counter = counter + 1
                    if isFile:
                        id = id + 1
                    isFile = not isFile

                print("Expansion 1 ???")
                expansion2 = self.getExpansionString(expansion1)
                #print(expansion2)

                print("Expanding large values")
                expansion3 = expansion2
                # print(expansion3)

                freeIndices = []
                digitIndices = []

                print("Creating list of free indices and digit indices")
                expansion3size = expansion3.__len__()
                for i in range(expansion3size):
                    if expansion3[i] == '.':
                        freeIndices = freeIndices + [i]
                for i in range(expansion3size):
                    if expansion3[i] != '.':
                        digitIndices = digitIndices + [i]

                print("Swapping spaces with digits")
                while freeIndices.__len__() > 0 and digitIndices.__len__() > 0:
                    freeIndex = freeIndices.pop(0)
                    digitIndex = digitIndices.pop(-1)
                    if freeIndex > digitIndex:
                        break

                    expansion3[freeIndex] = expansion3[digitIndex]
                    expansion3[digitIndex] = '.'
                print(expansion3)

                print("Calculating checksum")
                checksum = 0
                for i in range(expansion3.__len__()):
                    value = expansion3[i]
                    if value == '.':
                        break
                    checksum = checksum + (i * int(value))

                self.answer = checksum
                break

    def getExpansionString(self, expansion):
        result = []
        for tuple in expansion:
            if tuple[2] != None:
                result = result + ([tuple[2]] * tuple[1])
            else:
                result = result + (['.'] * tuple[1])
        return result

    def isComplete(self, expansion):
        count = 0
        for i in range(1, expansion.__len__()):
            a = expansion[i - 1]
            b = expansion[i]
            if a.isdigit() and not b.isdigit():
                count = count + 1
            elif not a.isdigit() and b.isdigit():
                count = count + 1

            if count > 1:
                return False
        return True

    def getFirstFreeSpaceIndex(self, expansion):
        counter = 0
        for i in range(expansion.__len__()):
            if not str(expansion[i]).isdigit():
                return counter
            counter = counter + 1
        assert False

    def getLastIdIndex(self, expansion):
        counter = expansion.__len__() - 1
        for i in range(expansion.__len__()):
            if str(expansion[counter]).isdigit():
                return counter
            counter = counter - 1
        assert False

    def expandLargeValues(self, expansion2):
        result = []
        for value in expansion2:
            if expansion2.__len__() > 1:
                result = result + list(str(value))
            else:
                result = result + [value]
        return result