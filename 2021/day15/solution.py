import sys

from grid.grid import Grid


def getSolutionForPathScoring(filename):
    grid = Grid()
    grid.createFromFile(filename)
    grid.addBorder("X")

    solver = PathSolver(grid)
    solver.solve()

    return solver.getMinimumScore()


class PathSolver:

    grid = None
    minimumPath = None
    endX = -1
    endY = -1

    def __init__(self, grid):
        self.grid = grid
        self.minimumPath = [PathItem(1, 1, 1, sys.maxsize)]
        self.endX = grid.getSizeX() - 2
        self.endY = grid.getSizeY() - 2

    def solve(self):

        # Create initial path
        startValue = self.grid.getValue(1, 1)
        start = PathItem(1, 1, startValue, startValue)
        path = [start]

        # Create current minimum
        self.solveWithPath(path)

        print(self.minimumPath)


    def solveWithPath(self, path):

        pathSize = len(path)
        pathEnd = path[pathSize - 1]

        if pathEnd.x == self.endX and pathEnd.y == self.endY:
            minimumPathEnd = self.minimumPath[len(self.minimumPath) - 1]
            if pathEnd.cumScore < minimumPathEnd.cumScore:
                self.minimumPath = path.copy()
                print("New minimum path: {}".format(self.minimumPath))
            else:
                sys.stdout.write("")

        # South
        #sys.stdout.write("S")
        #self.solveInDirection(path, 0, 1)
        # East
        #sys.stdout.write("E")
        #self.solveInDirection(path, 1, 0)
        #sys.stdout.flush()
        # # North
        # sys.stdout.write("N")
        # self.solveInDirection(path, 0, -1)
        # # West
        # sys.stdout.write("W")
        # self.solveInDirection(path, -1, 0)

        pathEast = None
        pathSouth = None

        pathEnd = path[len(path) - 1]
        minimumPathEnd = self.minimumPath[len(self.minimumPath) - 1]

        deltaX = 0
        deltaY = 1
        valueSouth = self.grid.getValue(pathEnd.x + deltaX, pathEnd.y + deltaY)
        if valueSouth != "X":
            pathSouthItem = PathItem(pathEnd.x + deltaX, pathEnd.y + deltaY, valueSouth, pathEnd.cumScore + valueSouth)
            if pathSouthItem not in path:
                if pathSouthItem.cumScore < minimumPathEnd.cumScore:
                    pathSouth = path.copy()
                    pathSouth.append(pathSouthItem)
                # else:
                #     sys.stdout.write("|")
                #     sys.stdout.flush()

        pathEnd = path[len(path) - 1]
        minimumPathEnd = self.minimumPath[len(self.minimumPath) - 1]

        deltaX = 1
        deltaY = 0
        valueEast = self.grid.getValue(pathEnd.x + deltaX, pathEnd.y + deltaY)
        if valueEast != "X":
            pathEastItem = PathItem(pathEnd.x + deltaX, pathEnd.y + deltaY, valueEast, pathEnd.cumScore + valueEast)
            if pathEastItem not in path:
                if pathEastItem.cumScore < minimumPathEnd.cumScore:
                    pathEast = path.copy()
                    pathEast.append(pathEastItem)
                # else:
                #     sys.stdout.write("-")
                #     sys.stdout.flush()

        if pathEast != None and pathSouth != None:
            if valueEast < valueSouth:
                self.solveWithPath(pathEast)
                self.solveWithPath(pathSouth)
            else:
                self.solveWithPath(pathSouth)
                self.solveWithPath(pathEast)
        elif pathEast != None:
            self.solveWithPath(pathEast)
        elif pathSouth != None:
            self.solveWithPath(pathSouth)


    def solveInDirection(self, path, deltaX, deltaY):

        pathEnd = path[len(path) - 1]
        minimumPathEnd = self.minimumPath[len(self.minimumPath) - 1]

        valueSouth = self.grid.getValue(pathEnd.x + deltaX, pathEnd.y + deltaY)
        if valueSouth != "X":
            pathSouthItem = PathItem(pathEnd.x + deltaX, pathEnd.y + deltaY, valueSouth, pathEnd.cumScore + valueSouth)
            if pathSouthItem not in path and pathSouthItem.cumScore < minimumPathEnd.cumScore:
                pathSouth = path.copy()
                pathSouth.append(pathSouthItem)
                self.solveWithPath(pathSouth)

    def getMinimumScore(self):
        minimumPathStart = self.minimumPath[0]
        minimumPathEnd = self.minimumPath[len(self.minimumPath) - 1]
        return minimumPathEnd.cumScore - minimumPathStart.score


class PathItem:
    x = 0
    y = 0
    score = 0
    cumScore = 0

    def __init__(self, x, y, score, cumScore):
        self.x = x
        self.y = y
        self.score = score
        self.cumScore = cumScore

    def __str__(self):
        return "{},{} ({}) ({})".format(self.x, self.y, self.score, self.cumScore)

    def __repr__(self):
        return self.__str__()

    def __eq__(self, other):
        return self.x == other.x and \
               self.y == other.y and \
               self.score == other.score

    def __ne__(self, other):
        return self != other