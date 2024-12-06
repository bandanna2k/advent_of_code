import sys

from grid.grid import Grid


def getSolution2(filename):

    grid = Grid()
    grid.createFromFile(filename)
    print(grid)

    gridCumScores = getCumScores(grid)
    print(gridCumScores)

def getCumScores(grid):

    maxX = grid.getSizeX()
    maxY = grid.getSizeY()

    gridCumScores = Grid()
    gridCumScores.createFromDimensions(maxX, maxY)

    assert maxX == maxY, "Grid not square"

    # Do first value
    gridCumScores.setValue(0, 0, grid.getValue(0, 0))

    #print(gridCumScores.getNiceDisplay())

    x = 1
    y = 1
    counter = 1
    while x < maxX:
        # Mark cum south side
        for i in range(counter):
            currentValue = grid.getValue(i, y)
            prevValue = gridCumScores.getValue(i, y - 1)
            gridCumScores.setValue(i, y, prevValue + currentValue)
        # Mark east side
        for i in range(counter):
            currentValue = grid.getValue(x, i)
            prevValue = gridCumScores.getValue(x - 1, i)
            gridCumScores.setValue(x, i, prevValue + currentValue)

        # Corner
        # prevW = grid.getValue(x - 1, y)
        # prevN = grid.getValue(x, y - 1)
        # currentValue = grid.getValue(x, y)
        # if prevW < prevN:
        #     prevCumValueNW = gridCumScores.getValue(x - 1, y - 1)
        #     gridCumScores.setValue(x, y, prevCumValueNW + prevW + currentValue)
        # else:
        #     prevCumValueNW = gridCumScores.getValue(x - 1, y - 1)
        #     gridCumScores.setValue(x, y, prevCumValueNW + prevN + currentValue)

        # Check south, is it cheaper to come from the side or below on th
        for i in range(counter):
            cumScoreN = gridCumScores.getValue(i, y)
            cumScoreE = gridCumScores.getValue(i, y)
            gridCumScores.setValue(i, y, prevValue + currentValue)


        x = x + 1
        y = y + 1
        counter = counter + 1
    print(gridCumScores.getNiceDisplay())

    return

    for j in range(1, maxX + 1):
        # Mark south side
        for i in range(j - 1):
            currentValue = grid.getValue(i, y)
            prevValue = gridCumScores.getValue(i, y - 1)
            gridCumScores.setValue(i, y, prevValue + currentValue)
        # Mark east side
        for i in range(j - 1):
            currentValue = grid.getValue(x, i)
            prevValue = gridCumScores.getValue(x - 1, i)
            gridCumScores.setValue(x, i, prevValue + currentValue)
        # Corner
        if j > 1:
            prevW = grid.getValue(j - 2, j - 1)
            prevN = grid.getValue(j - 1, j - 2)
            currentValue = grid.getValue(j - 1, j - 1)
            if prevW < prevN:
                prevCumValueNW = gridCumScores.getValue(j - 1, j - 1)
                gridCumScores.setValue(j, j, prevCumValueNW + prevW + currentValue)
            else:
                prevCumValueNW = gridCumScores.getValue(j - 1, j - 1)
                gridCumScores.setValue(j, j, prevCumValueNW + prevN + currentValue)


        x = x + 1
        y = y + 1
    print(gridCumScores.getNiceDisplay())




def doLoopThroughOuter(filename):
    grid = Grid()
    grid.createFromFile(filename)

    maxX = grid.getSizeX()
    maxY = grid.getSizeY()

    assert maxX == maxY, "Grid not square"

    x = 0
    y = 0
    counter = 1
    for j in range(maxX):
        # Mark south side
        for i in range(j + 1):
            grid.setValue(i, y, counter)
        # Mark east side
        for i in range(j + 1):
            grid.setValue(x, i, counter)
        counter = counter + 1
        x = x + 1
        y = y + 1
    print(grid.getNiceDisplay())

