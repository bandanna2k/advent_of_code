from day11.part1 import convertLinesToGrid
from fileIO import getAllLines, myPrint


def getBinaryGrid(grid):
    newMaxX = getSizeX(grid)
    newMaxY = getSizeY(grid)
    result = getNewGrid(newMaxX, newMaxY)
    for x in range(newMaxX):
        for y in range(newMaxY):
            if grid[y][x] > 0:
                result[y][x] = 'X'
            else:
                result[y][x] = ' '
    return result


def getFolderPaperScore(filename, folds):

    lines = getAllLines(filename)

    maxX = 0
    maxY = 0
    points = []
    for line in lines:
        if len(line) == 0:
            continue

        if line.startswith("fold"):
            continue

        points.append(line)
        coords = line.split(",")
        x = int(coords[0])
        y = int(coords[1])
        maxX = max(maxX, x)
        maxY = max(maxY, y)

    # Create grid
    grid = getNewGrid(maxX + 1, maxY + 1)

    # Plot items
    for pointString in points:
        point = pointString.split(",")
        x = int(point[0])
        y = int(point[1])

        grid[y][x] = grid[y][x] + 1

    myPrint(grid)

    # Do fold stuff
    foldCount = 0
    for line in lines:
        if line.startswith("fold"):
            foldCount = foldCount + 1
            line = line.replace("fold", "")
            line = line.replace("along", "")
            line = line.strip()
            if line[0] == "y":
                line = line.replace("y=", "")
                grid = getHorizontallyFoldedGrid(grid, int(line))
            if line[0] == "x":
                line = line.replace("x=", "")
                grid = getVerticallyFoldedGrid(grid, int(line))
            myPrint(grid)
            if foldCount >= folds:
                break

    myPrint(getBinaryGrid(grid))

    return getScore(grid)

def getScore(grid):
    newMaxX = getSizeX(grid)
    newMaxY = getSizeY(grid)
    score = 0
    for x in range(newMaxX):
        for y in range(newMaxY):
            if grid[y][x] > 0:
                score = score + 1
    return score


def getNewGrid(maxX, maxY):
    return [[0 for y in range(maxX)] for x in range(maxY)]

def getSizeX(grid):
    return len(grid[0])

def getSizeY(grid):
    return len(grid)


def getHorizontallyFoldedGrid(grid, y):
    assert getSizeY(grid) % 2 == 1
    result = getNewGrid(getSizeX(grid), int(getSizeY(grid) / 2))

    newMaxX = getSizeX(result)
    newMaxY = getSizeY(result)
    oldMaxY = getSizeY(grid)
    for x in range(newMaxX):
        for y in range(newMaxY):
            result[y][x] = result[y][x] + grid[y][x]
            result[y][x] = result[y][x] + grid[oldMaxY - y - 1][x]

    return result

def getVerticallyFoldedGrid(grid, x):
    assert getSizeX(grid) % 2 == 1
    result = getNewGrid(int(getSizeX(grid) / 2), getSizeY(grid))

    oldMaxX = getSizeX(grid)
    maxX = getSizeX(result)
    maxY = getSizeY(result)
    for x in range(maxX):
        for y in range(maxY):
            result[y][x] = result[y][x] + grid[y][x]
            result[y][x] = result[y][x] + grid[y][oldMaxX - x - 1]

    return result


