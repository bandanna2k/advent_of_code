
class Grid:

    theGrid = None

    def createFromDimensions(self, maxX, maxY):
        self.createFromDimensionsWithValue(maxX, maxY, 0)

    def createFromDimensionsWithValue(self, maxX, maxY, value):
        self.theGrid = [[0 for y in range(maxX)] for x in range(maxY)]
        for x in range(maxX):
            for y in range(maxY):
                self.setValue(x, y, value)


    def createFromFile(self, filename):
        lines = getAllLines(filename)
        self.theGrid = getGridFromLines(lines)

    def setValue(self, x, y, value):
        assert x >= 0
        assert y >= 0
        self.theGrid[y][x] = value

    def getSizeX(self):
        return len(self.theGrid[0])

    def getSizeY(self):
        return len(self.theGrid)

    def getNiceDisplay(self):
        result = ""
        for row in self.theGrid:
            result = "{}{}\n".format(result, row)
        return result

    def __str__(self):
        return "{}".format(self.getNiceDisplay())

    def getValue(self, x, y):
        assert x >= 0, "Failed on X >= {}".format(x)
        assert y >= 0, "Failed on Y >= {}".format(y)
        return self.theGrid[y][x]

    def addBorder(self, value):
        self.addBorderBottom(value)
        self.addBorderRight(value)
        self.addBorderBottom(value)
        self.addBorderRight(value)

        maxX = self.getSizeX()
        maxY = self.getSizeY()
        for x in reversed(range(1, maxX - 1)):
            for y in reversed(range(1, maxY - 1)):
                src = self.getValue(x - 1, y - 1)
                self.setValue(x, y, src)

        for x in range(maxX):
            self.setValue(x, 0, value)
        for y in range(maxY):
            self.setValue(0, y, value)



    def addBorderBottom(self, value):
        maxX = self.getSizeX()
        newRow = []
        for i in range(maxX):
            newRow.append(value)
        self.theGrid.append(newRow)

    def addBorderRight(self, value):
        for row in self.theGrid:
            row.append(value)

    def getCopy(self):
        result = Grid()
        maxX = self.getSizeX()
        maxY = self.getSizeY()
        result.createFromDimensions(maxX, maxY)
        for x in range(maxX):
            for y in range(maxY):
                result.setValue(x, y, self.getValue(x, y))
        return result

    def removeBorder(self):
        copy = Grid()
        copy.createFromDimensions(self.getSizeX() - 2, self.getSizeY() - 2)
        maxX = copy.getSizeX()
        maxY = copy.getSizeY()
        for x in range(maxX):
            for y in range(maxY):
                copy.setValue(x, y, self.getValue(x + 1, y + 1))
        self.theGrid = copy.theGrid


def getAllLines(fileName):
    with open(fileName) as f:
        linesWithEOL = f.readlines()
    lines = []
    for line in linesWithEOL:
        lines.append(line.strip())
    return lines

def getGridFromLines(lines):
    maxY = len(lines)
    maxX = len(lines[0])
    result = []
    for y in range(maxY):
        values = []
        for x in range(maxX):
            value = lines[y][x]
            values.append(int(value))
        result.append(values)
    return result


