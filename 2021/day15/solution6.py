import sys
from queue import PriorityQueue

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


def getSolution6(filename):

    grid = Grid()
    grid.createFromFile(filename)
    print(grid)

    return getShortestPathWeight(grid)

def getShortestPathWeight(grid):

    grid.addBorder("X")

    maxX = grid.getSizeX()
    maxY = grid.getSizeY()

    path = dict()
    path["1,1"] = PathItem(1, 1, 0, False)
    currentPathItem = getLeastPath(path)

    while (currentPathItem.x != (maxX - 2) or currentPathItem.y != (maxY - 2)):

        #print(path)

        currentPathItem = getLeastPath(path)
        currentPathItem.visited = True

        weightToStart = currentPathItem.weight
        x = currentPathItem.x
        y = currentPathItem.y

        gridN = grid.getValue(x, y - 1)
        if gridN != "X":
            keyN = "{},{}".format(x, y - 1)
            if keyN in path:
                path[keyN].processWeightFromHere(weightToStart + gridN)
            else:
                path[keyN] = PathItem(x, y - 1, weightToStart + gridN, False)

        gridS = grid.getValue(x, y + 1)
        if gridS != "X":
            keyS = "{},{}".format(x, y + 1)
            if keyS in path:
                path[keyS].processWeightFromHere(weightToStart + gridS)
            else:
                path[keyS] = PathItem(x, y + 1, weightToStart + gridS, False)

        gridE = grid.getValue(x + 1, y)
        if gridE != "X":
            keyE = "{},{}".format(x + 1, y)
            if keyE in path:
                path[keyE].processWeightFromHere(weightToStart + gridE)
            else:
                path[keyE] = PathItem(x + 1, y, weightToStart + gridE, False)

        gridW = grid.getValue(x - 1, y)
        if gridW != "X":
            keyW = "{},{}".format(x - 1, y)
            if keyW in path:
                path[keyW].processWeightFromHere(weightToStart + gridW)
            else:
                path[keyW] = PathItem(x - 1, y, weightToStart + gridW, False)

    print(currentPathItem)
    return currentPathItem.weight

def getLeastPath(path):
    result = None
    for pathItem in path.values():
        if not pathItem.visited:
            if result is None:
                result = pathItem
            else:
                if pathItem.weight < result.weight:
                    result = pathItem
    return result







class PathItem:
    x = 0
    y = 0
    weight = 0
    visited = False
    def __init__(self, x, y, weight, visited):
        self.x = x
        self.y = y
        self.weight = weight
        self.visited = visited

    def __eq__(self, other):
        return self.x == other.x and self.y == other.y

    def processWeightFromHere(self, candidateWeight):
        if candidateWeight < self.weight:
            self.weight = candidateWeight

    def __str__(self):
        if self.visited:
            return "{},{} ({})".format(self.x, self.y, self.weight)
        else:
            return "{},{} (*{})".format(self.x, self.y, self.weight)

    def __repr__(self):
        return self.__str__()

    def __gt__(self, other):
        if not self.visited and other.visited:
            return False
        if self.visited and not other.visited:
            return True
        return self.weight > other.weight