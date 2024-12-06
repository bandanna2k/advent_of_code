from fileIO import getAllLines

def findLowPointTotal(filename):

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
                lowPoints.append(int(value))
                print("X {}, Y {}, Value {}".format(x, y, value))


    result = 0
    for lowPoint in lowPoints:
        result = result + (lowPoint + 1)

    return result