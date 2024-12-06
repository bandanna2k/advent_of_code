import sys

from grid.grid import Grid


def getSolution5(filename):

    grid = Grid()
    grid.createFromFile(filename)
    print(grid)

    gridWeights = getWeightsGrid(grid)
    print(gridWeights)

    maxX = gridWeights.getSizeX()
    maxY = gridWeights.getSizeY()
    value00 = gridWeights.getValue(0, 0)
    valueXY = gridWeights.getValue(maxX - 1, maxY - 1)

    return valueXY.weight #- value00.weight

def getWeightsGrid(srcGrid):

    grid = srcGrid.getCopy()

    maxX = grid.getSizeX()
    maxY = grid.getSizeY()

    gridWeights = Grid()
    gridWeights.createFromDimensionsWithValue(maxX, maxY, Weight(sys.maxsize, False))

    grid.addBorder("X")
    gridWeights.addBorder("X")

    maxX = grid.getSizeX()
    maxY = grid.getSizeY()

    for x in range(1, maxX - 1):
        for i in range(1, maxY - 1):
            if x % 2 == 1:
                y = i
            else:
                y = maxY - i - 1

            if x == 1 and y == 1:
                gridWeights.setValue(x, y, Weight(0, True))

            gridWeightO = gridWeights.getValue(x, y)

            # Set all weights around me
            # N
            gridN = grid.getValue(x, y - 1)
            gridWeightN = gridWeights.getValue(x, y - 1)
            if gridWeightN != "X": # and not gridWeightN.complete:
                minWeight = min(gridWeightN.weight, gridWeightO.weight + gridN)
                gridWeights.setValue(x, y - 1, Weight(minWeight, False))

            # S
            gridS = grid.getValue(x, y + 1)
            gridWeightS = gridWeights.getValue(x, y + 1)
            if gridS != "X": # and not gridWeightS.complete:
                minWeight = min(gridWeightS.weight, gridWeightO.weight + gridS)
                gridWeights.setValue(x, y + 1, Weight(minWeight, False))

            # E
            gridE = grid.getValue(x + 1, y)
            gridWeightE = gridWeights.getValue(x + 1, y)
            if gridE != "X": # and not gridWeightE.complete:
                minWeight = min(gridWeightE.weight, gridWeightO.weight + gridE)
                gridWeights.setValue(x + 1, y, Weight(minWeight, False))

            # W
            gridW = grid.getValue(x - 1, y)
            gridWeightW = gridWeights.getValue(x - 1, y)
            if gridW != "X": # and not gridWeightW.complete:
                minWeight = min(gridWeightW.weight, gridWeightO.weight + gridW)
                gridWeights.setValue(x - 1, y, Weight(minWeight, False))

            gridWeightO.setComplete()

    gridWeights.removeBorder()

    return gridWeights


class Weight:
    weight = 0
    complete = False

    def __init__(self, weight, complete):
        self.weight = weight
        self.complete = complete

    def __str__(self):
        if self.complete:
            return "{}".format(self.weight)
        else:
            return "*{}".format(self.weight)

    def __repr__(self):
        return self.__str__()

    def setComplete(self):
        self.complete = True