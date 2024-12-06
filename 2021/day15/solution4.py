import sys

from grid.grid import Grid


def getSolution4(filename):

    grid = Grid()
    grid.createFromFile(filename)
    print(grid)

    gridCumScores = getCumScores(grid)
    print(gridCumScores)



    value00 = gridCumScores.getValue(0, 0)
    valueMax = gridCumScores.getValue(gridCumScores.getSizeX() - 1, gridCumScores.getSizeY() - 1)
    return valueMax - value00



def getCumScores(sourceGrid):

    grid = sourceGrid.getCopy()

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

    # Set algorithm to use
    gridCumScores.setValue(0, maxY - 1, "N")
    gridCumScores.setValue(0, maxY - 2, "N")

    gridCumScores.setValue(1, maxY - 1, "NW")
    gridCumScores.setValue(1, maxY - 2, "W")

    gridCumScores.setValue(maxX - 1, 0, "W")
    gridCumScores.setValue(maxX - 2, 0, "W")

    gridCumScores.setValue(maxX - 1, 1, "W")
    gridCumScores.setValue(maxX - 2, 1, "NW")

    # Add border
    gridCumScores.addBorder("X")
    grid.addBorder("X")

    # Loops
    doLater = ["N", "W", "NW"]
    for i in range(maxX):
        x = i + 1
        for j in range(maxY):
            y = j + 1
            cumValue = gridCumScores.getValue(x, y)

            if cumValue in doLater:
                continue

            if cumValue != 0:
                continue



            if y > 2:
                cumValueA = gridCumScores.getValue(x, y - 2)
                if cumValueA in doLater:
                    continue
                if cumValueA == 0:
                    continue

                # Check
                #  A (cumValue)
                #
                #  B (unknown)
                minPathValue = getMinValueForHorizontal(grid, cumValueA, x, y)
                gridCumScores.setValue(x, y, minPathValue)


    return gridCumScores.getNiceDisplay()


def getMinValueForHorizontal(grid, cumValueA, x, y):

    cantCalc = ["N", "W", "NW", "X"]

    minValue = sys.maxsize

    # Check
    #  A (cumValue)
    #
    #  B (unknown)
    a = y - 2
    m = y - 1
    b = y
    #valueA = grid.getValue(x, a)
    valueM = grid.getValue(x, m)
    valueB = grid.getValue(x, b)

    valueAW = sys.maxsize
    valueAWW = sys.maxsize
    valueAWWW = sys.maxsize
    valueMW = sys.maxsize
    valueMWW = sys.maxsize
    valueMWWW = sys.maxsize
    valueBW = sys.maxsize
    valueBWW = sys.maxsize
    valueBWWW = sys.maxsize

    for mult in [1, -1]:
        W = x + (1 * mult)
        valueAW = grid.getValue(W, a)
        valueMW = grid.getValue(W, m)
        valueBW = grid.getValue(W, b)
        if valueAW in cantCalc:
            valueAW = sys.maxsize
            valueMW = sys.maxsize
            valueBW = sys.maxsize
        else:
            WW = x + (2 * mult)
            valueAWW = grid.getValue(WW, a)
            valueMWW = grid.getValue(WW, m)
            valueBWW = grid.getValue(WW, b)
            if valueAWW in cantCalc:
                valueAWW = sys.maxsize
                valueMWW = sys.maxsize
                valueBWW = sys.maxsize
            else:
                WWW = x + (3 * mult)
                valueAWWW = grid.getValue(WWW, a)
                valueMWWW = grid.getValue(WWW, m)
                valueBWWW = grid.getValue(WWW, b)
                if valueAWWW in cantCalc:
                    valueAWWW = sys.maxsize
                    valueMWWW = sys.maxsize
                    valueBWWW = sys.maxsize

        minValue = min(minValue, cumValueA + valueM + valueB)
        minValue = min(minValue, cumValueA + valueAW + valueMW + valueBW + valueB)
        minValue = min(minValue, cumValueA + valueAW, valueAWW + valueMWW + valueBWW + valueBW, valueB)
        minValue = min(minValue, cumValueA + valueAW, valueAWW + valueAWWW + valueMWWW + valueBWWW + valueBWW + valueBW, valueB)

    return minValue