import sys
from Common.RowInput import RowInput

class Part2:

    input = None

    def __init__(self, filename):
        self.input = RowInput(filename)

    def getSafeRows(self):

        safe = 0
        for y in range(self.input.height):

            increasing = True
            decreasing = True
            safeDelta = True

            row = self.input.getRow(y)

            for x in range(1, row.__len__()):

                value = row[x]
                prevValue = row[x - 1]
                delta = abs(value - prevValue)

                if value > prevValue:   # Increasing
                    decreasing = False
                if value < prevValue:   # Decreasing
                    increasing = False
                if not (delta == 1 or delta == 2 or delta == 3):
                    safeDelta = False

            if (increasing or decreasing) and safeDelta:
                safe = safe + 1
            else:


                for x in range(0, row.__len__()):

                    rowMinusX = row[:]
                    del rowMinusX[x]

                    increasing = True
                    decreasing = True
                    safeDelta = True

                    for x_take in range(1, rowMinusX.__len__()):

                        value = rowMinusX[x_take]
                        prevValue = rowMinusX[x_take - 1]
                        delta = abs(value - prevValue)

                        if value > prevValue:   # Increasing
                            decreasing = False
                        if value < prevValue:   # Decreasing
                            increasing = False
                        if not (delta == 1 or delta == 2 or delta == 3):
                            safeDelta = False

                    if (increasing or decreasing) and safeDelta:
                        safe = safe + 1
                        break

        return safe

