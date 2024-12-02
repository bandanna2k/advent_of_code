import sys
from Common.RowInput import RowInput

class Part1:

    input = None

    def __init__(self, filename):
        self.input = RowInput(filename)

    def getSafeRows(self):

        safe = 0
        for y in range(self.input.height):

            rowSafe = True
            prevValue = sys.maxsize

            row = self.input.getRow(y)

            for x in range(row.__len__()):
                value = row[x]

                if value >= prevValue:
                    rowSafe = False
                    break

                prevValue = value

            if rowSafe:
                safe = safe + 1

        return safe

