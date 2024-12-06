from day15.solution6 import getShortestPathWeight
from grid.grid import Grid


def expandGrid(grid):
    maxX = grid.getSizeX()
    maxY = grid.getSizeY()

    expanded = Grid()
    expanded.createFromDimensions(maxX * 5, maxX * 5)

    for x in range(maxX):
        for y in range(maxY):
            value = grid.getValue(x, y)

            expanded.setValue(x, y, value)

            expanded.setValue(x + (1 * maxX), y + (0 * maxY), ((value + 0) % 9) + 1)
            expanded.setValue(x + (0 * maxX), y + (1 * maxY), ((value + 0) % 9) + 1)

            expanded.setValue(x + (2 * maxX), y + (0 * maxY), ((value + 1) % 9) + 1)
            expanded.setValue(x + (1 * maxX), y + (1 * maxY), ((value + 1) % 9) + 1)
            expanded.setValue(x + (0 * maxX), y + (2 * maxY), ((value + 1) % 9) + 1)

            expanded.setValue(x + (3 * maxX), y + (0 * maxY), ((value + 2) % 9) + 1)
            expanded.setValue(x + (2 * maxX), y + (1 * maxY), ((value + 2) % 9) + 1)
            expanded.setValue(x + (1 * maxX), y + (2 * maxY), ((value + 2) % 9) + 1)
            expanded.setValue(x + (0 * maxX), y + (3 * maxY), ((value + 2) % 9) + 1)

            expanded.setValue(x + (4 * maxX), y + (0 * maxY), ((value + 3) % 9) + 1)
            expanded.setValue(x + (3 * maxX), y + (1 * maxY), ((value + 3) % 9) + 1)
            expanded.setValue(x + (2 * maxX), y + (2 * maxY), ((value + 3) % 9) + 1)
            expanded.setValue(x + (1 * maxX), y + (3 * maxY), ((value + 3) % 9) + 1)
            expanded.setValue(x + (0 * maxX), y + (4 * maxY), ((value + 3) % 9) + 1)

            expanded.setValue(x + (4 * maxX), y + (1 * maxY), ((value + 4) % 9) + 1)
            expanded.setValue(x + (3 * maxX), y + (2 * maxY), ((value + 4) % 9) + 1)
            expanded.setValue(x + (2 * maxX), y + (3 * maxY), ((value + 4) % 9) + 1)
            expanded.setValue(x + (1 * maxX), y + (4 * maxY), ((value + 4) % 9) + 1)

            expanded.setValue(x + (4 * maxX), y + (2 * maxY), ((value + 5) % 9) + 1)
            expanded.setValue(x + (3 * maxX), y + (3 * maxY), ((value + 5) % 9) + 1)
            expanded.setValue(x + (2 * maxX), y + (4 * maxY), ((value + 5) % 9) + 1)

            expanded.setValue(x + (4 * maxX), y + (3 * maxY), ((value + 6) % 9) + 1)
            expanded.setValue(x + (3 * maxX), y + (4 * maxY), ((value + 6) % 9) + 1)

            expanded.setValue(x + (4 * maxX), y + (4 * maxY), ((value + 7) % 9) + 1)

    print(expanded)

    return expanded


def getSolution6Part2(filename):
    grid = Grid()
    grid.createFromFile(filename)
    print(grid)

    grid = expandGrid(grid)

    return getShortestPathWeight(grid)

