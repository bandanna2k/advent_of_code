import sys

from grid.grid import Grid


def getSolution3(filename):

    grid = Grid()
    grid.createFromFile(filename)
    print(grid)

    gridCumScores = getCumScores(grid)
    print(gridCumScores)

    value00 = gridCumScores.getValue(0, 0)
    valueMax = gridCumScores.getValue(gridCumScores.getSizeX() - 1, gridCumScores.getSizeY() - 1)
    return valueMax - value00


def getCumScores(grid):

    global valueCumN
    maxX = grid.getSizeX()
    maxY = grid.getSizeY()

    gridCumScores = Grid()
    gridCumScores.createFromDimensions(maxX, maxY)

    # Do first rect
    value00 = grid.getValue(0, 0)
    value10 = grid.getValue(1, 0)
    value01 = grid.getValue(0, 1)
    value11 = grid.getValue(1, 1)
    gridCumScores.setValue(0, 0, value00)
    gridCumScores.setValue(1, 0, value00 + value10)
    gridCumScores.setValue(0, 1, value00 + value01)
    if value01 < value10:
        gridCumScores.setValue(1, 1, value00 + value01 + value11)
    else:
        gridCumScores.setValue(1, 1, value00 + value01 + value11)



    x = 0
    y = 2
    for i in range(2, maxX):
        # VERTICAL
        valueCumN = gridCumScores.getValue(x, y - 1)
        valueCumNE = gridCumScores.getValue(x + 1, y - 1)
        valueO = grid.getValue(x, y)
        valueE = grid.getValue(x + 1, y)

        # X, Y
        gridCumScores.setValue(x, y, min(valueCumN + valueO, valueCumNE + valueE + valueO))

        # X + 1, Y
        gridCumScores.setValue(x + 1, y, min(valueCumNE + valueE, valueCumN + valueO + valueE))

        y = y + 1

    x = 2
    y = 0
    for i in range(2, maxX):
        # ACROSS
        valueCumW = gridCumScores.getValue(x - 1, y)
        valueCumSW = gridCumScores.getValue(x - 1, y + 1)
        valueO = grid.getValue(x, y)
        valueS = grid.getValue(x, y + 1)

        # X, Y
        gridCumScores.setValue(x, y, min(valueCumW + valueO, valueCumSW + valueS + valueO))

        # X + 1, Y
        gridCumScores.setValue(x, y + 1, min(valueCumSW + valueS, valueCumW + valueO + valueS))

        x = x + 1

    for y in range(2, maxX):
        for x in range(2, maxY):
            valueCumN = gridCumScores.getValue(x, y - 1)
            valueCumW = gridCumScores.getValue(x - 1, y)
            valueO = grid.getValue(x, y)
            gridCumScores.setValue(x, y, min(valueCumN + valueO, valueCumW + valueO))

    print(gridCumScores.getNiceDisplay())
    return gridCumScores

