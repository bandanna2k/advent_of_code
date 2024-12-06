from fileIO import getAllLines

from part1 import step
from part1 import getGridWithBorder
from part1 import convertLinesToGrid




def getBigFlash(filename):

    allLines = getAllLines(filename)
    allLines = getGridWithBorder(allLines)

    grid = convertLinesToGrid(allLines)
    flashes = []

    iterations = 0
    while not hasCompleteFlash(grid):
        grid = step(grid, flashes)
        iterations = iterations + 1

    print("BIG FLASH AT {}".format(iterations))
    return iterations



def hasCompleteFlash(grid):
    maxY = len(grid)
    maxX = len(grid[0])
    for y in range(maxY):
        for x in range(maxX):
            value = grid[y][x]
            if value == None:
                continue
            if value != 0:
                return False
    return True
