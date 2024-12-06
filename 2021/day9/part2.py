import sys

from fileIO import getAllLines

def findTotalOfBasin(filename):

    allLines = getAllLines(filename)

    # Calc max's
    maxX = len(allLines[0])
    maxY = len(allLines)

    # Preprocess grid
    # Create dud string
    dudString = ""
    for x in range(maxX):
        dudString = dudString + "X"

    # Insert dud string
    allLines.insert(0, dudString)
    allLines.append(dudString)

    # Calc max's
    maxX = len(allLines[0])
    maxY = len(allLines)

    # Append X to start and end
    for y in range(maxY):
        input = allLines[y]
        output = "X" + input + "X"
        allLines[y] = output

    # Calc max's
    maxX = len(allLines[0])
    maxY = len(allLines)

    # Calc if lowest point
    lowPoints = []
    for y in range(maxY):
        line = allLines[y]
        for x in range(maxX):
            value = line[x]

            if value == "X":
                continue

            # Check if lowest
            adjacents = []

            # UP
            adjacents.append(allLines[y - 1][x])
            # DOWN
            adjacents.append(allLines[y + 1][x])
            # LEFT
            adjacents.append(allLines[y][x - 1])
            # RIGHT
            adjacents.append(allLines[y][x + 1])

            isLower = True
            for adjacent in adjacents:
                if adjacent == "X":
                    continue
                if adjacent <= value:
                    isLower = False
                    break

            if isLower:
                # Subtract for X at start row and Y at start row
                pair = "{};{}".format(x - 1, y - 1)
                lowPoints.append(pair)

    print(lowPoints)

    lowPointToBasinSize = dict()
    for lowPoint in lowPoints:

        splitString = lowPoint.split(";")
        x = int(splitString[0]) + 1
        y = int(splitString[1]) + 1
        key = "{},{}".format(x, y)
        print(key)

        pointsChecked = set()
        pointsInBasin = set()
        prevValue = int(allLines[y][x]) - 1
        getValue(allLines, x, y, prevValue, pointsChecked, pointsInBasin)
        #print("Points checked: {} {}".format(pointsChecked, len(pointsChecked)))
        print("Points in basin: {} {}".format(pointsInBasin, len(pointsInBasin)))

        lowPointToBasinSize[key] = len(pointsInBasin)

    print(lowPointToBasinSize)

    # Remove lowest score
    while len(lowPointToBasinSize) > 3:
        lowestScore = sys.maxsize
        lowestKey = None
        for key in lowPointToBasinSize.keys():
            value = lowPointToBasinSize[key]
            if value < lowestScore:
                lowestScore = value
                lowestKey = key

        lowPointToBasinSize.pop(lowestKey)

    print(lowPointToBasinSize)

    result = 1
    for value in lowPointToBasinSize.values():
        result = result * value
    return result


def getValue(lines, x, y, prevValue, pointsChecked, pointsInBasin):

    # Points checked
    pair = "{},{}".format(x, y)
    # if pair in pointsChecked:
    #     print("Exit on points checked {} {}".format(x, y))
    #     return 0
    # pointsChecked.add(pair)

    # Check borders and blockers
    value = lines[y][x]
    if value == "X":
        #print("Exit on X  {} {}".format(x, y))
        return 0
    if value == "9":
        #print("Exit on 9  {} {}".format(x, y))
        return 0

    # Is in basin
    nValue = int(value)
    if nValue <= prevValue:
        #print("Exit on not in basin  {} {}".format(x, y))
        return 0

    #print("YEp, In basin {} {} Value {}".format(x, y, nValue))
    pointsInBasin.add(pair)

    # UP
    #print("Check up")
    getValue(lines, x, y - 1, nValue, pointsChecked, pointsInBasin)
    # DOWN
    #print("Check down")
    getValue(lines, x, y + 1, nValue, pointsChecked, pointsInBasin)
    # LEFT
    #print("Check left")
    getValue(lines, x - 1, y, nValue, pointsChecked, pointsInBasin)
    # RIGHT
    #print("Check right")
    getValue(lines, x + 1, y, nValue, pointsChecked, pointsInBasin)
