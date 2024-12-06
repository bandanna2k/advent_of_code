from fileIO import getAllLines


def getPart1(filename, iterations):

    allLines = getAllLines(filename)
    allLines = getGridWithBorder(allLines)

    grid = convertLinesToGrid(allLines)
    flashes = []
    for i in range(iterations):
        grid = step(grid, flashes)

    total = 0
    for flash in flashes:
        total = total + flash
    print("TOTAL {}".format(total))
    return total



def convertLinesToGrid(lines):
    maxY = len(lines)
    maxX = len(lines[0])
    result = []
    for y in range(maxY):
        values = []
        for x in range(maxX):
            value = lines[y][x]
            if value == "X":
                values.append(None)
            else:
                values.append(int(value))
        result.append(values)
    return result






def step(grid, flashes):

    #print("---------------- ")
    #print("Increment all ")
    maxY = len(grid)
    maxX = len(grid[0])
    for y in range(maxY):
        for x in range(maxX):
            value = grid[y][x]
            if value == None:
                continue
            newValue = value + 1
            grid[y][x] = newValue
    #myPrint(grid)

    #print(" Flash")
    for y in range(maxY):
        for x in range(maxX):
            value = grid[y][x]
            if value == None:
                continue
            if value >= 10:
                grid[y][x] = -99
                setFlashValue(grid, y - 1, x - 1)
                setFlashValue(grid, y - 1, x)
                setFlashValue(grid, y - 1, x + 1)
                setFlashValue(grid, y, x - 1)
                #setFlashValue(grid, y, x)
                setFlashValue(grid, y, x + 1)
                setFlashValue(grid, y + 1, x - 1)
                setFlashValue(grid, y + 1, x)
                setFlashValue(grid, y + 1, x + 1)
    #myPrint(grid)
    #print(" Flash")

    valueGreaterThan10 = True
    while valueGreaterThan10:
        valueGreaterThan10 = False
        for y in range(maxY):
            for x in range(maxX):
                value = grid[y][x]
                if value == None:
                    continue
                if value >= 10:
                    valueGreaterThan10 = True
                    grid[y][x] = -999
                    setFlashValue(grid, y - 1, x - 1)
                    setFlashValue(grid, y - 1, x)
                    setFlashValue(grid, y - 1, x + 1)
                    setFlashValue(grid, y, x - 1)
                    #setFlashValue(grid, y, x)
                    setFlashValue(grid, y, x + 1)
                    setFlashValue(grid, y + 1, x - 1)
                    setFlashValue(grid, y + 1, x)
                    setFlashValue(grid, y + 1, x + 1)
    #myPrint(grid)

    countOfFlashes = 0
    for y in range(maxY):
        for x in range(maxX):
            value = grid[y][x]
            if value == None:
                continue
            if value < 0:
                countOfFlashes = countOfFlashes + 1
                grid[y][x] = 0

    #myPrint(grid)
    flashes.append(countOfFlashes)
    print("Flashes {}".format(countOfFlashes))
    return grid


def setFlashValue(grid, y, x):
    value = grid[y][x]
    if value == None:
        return
    grid[y][x] = value + 1




def copyGrid(lines):
    result = []
    for line in lines:
        result.append(line)
    return result

def getGridWithBorder(lines):

    newLines = lines

    maxX = len(newLines[0])
    maxY = len(newLines)

    # Preprocess grid
    # Create dud string
    dudString = ""
    for x in range(maxX):
        dudString = dudString + "X"

    # Insert dud string
    newLines.insert(0, dudString)
    newLines.append(dudString)

    # Calc max's
    maxX = len(newLines[0])
    maxY = len(newLines)

    # Append X to start and end
    for y in range(maxY):
        input = lines[y]
        output = "X" + input + "X"
        newLines[y] = output

    return newLines

