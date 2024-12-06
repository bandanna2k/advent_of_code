from adventOfCode.year2021 import fileIO

scenario = 0
if 0 == scenario:
    file = 'input.txt'
elif 1 == scenario:
    file = 'test.txt'
elif 2 == scenario:
    file = 'test2.txt'

allLines = fileIO.getAllLines(file)

# Parse file for max X and max Y
sizeX = 0
sizeY = 0
for line in allLines:
    coords = line.split("->")
    left = coords[0]
    right = coords[1]
    coordLeft = left.split(",")
    x1 = int(coordLeft[0])
    y1 = int(coordLeft[1])
    coordRight = right.split(",")
    x2 = int(coordRight[0])
    y2 = int(coordRight[1])

    sizeX = max(sizeX, x1, x2)
    sizeY = max(sizeY, y1, y2)
print("Size X {}, Size Y {}".format(sizeX, sizeY))

# Creat plot
plot = [[0 for y in range(sizeY + 1)] for x in range(sizeX + 1)]

# Add lines
for line in allLines:
    coords = line.split("->")
    left = coords[0]
    right = coords[1]
    coordLeft = left.split(",")
    x1 = int(coordLeft[0])
    y1 = int(coordLeft[1])
    coordRight = right.split(",")
    x2 = int(coordRight[0])
    y2 = int(coordRight[1])
    #print("{},{} -> {},{}".format(x1, y1, x2, y2))

    if y1 == y2:
        maxX = max(x1, x2)
        minX = min(x1, x2)
        length = 0
        for x in range(minX, maxX + 1):
            length = length + 1
            plot[x][y1] = (plot[x][y1]) + 1
        #print("X " + str(length))

    elif x1 == x2:
        maxY = max(y1, y2)
        minY = min(y1, y2)
        length = 0
        for y in range(minY, maxY + 1):
            plot[x1][y] = (plot[x1][y]) + 1
            length = length + 1
        #print("Y " + str(length))

    else:
        # Check dist X = dist Y
        maxX = max(x1, x2)
        minX = min(x1, x2)
        maxY = max(y1, y2)
        minY = min(y1, y2)
        distX = maxX - minX
        distY = maxY - minY
        assert distX == distY, "Distance X {} != Distance Y {}".format(distX, distY)

        if x1 > x2:
            deltaX = -1
        else:
            deltaX = 1

        if y1 > y2:
            deltaY = -1
        else:
            deltaY = 1

        nextX = x1
        nextY = y1
        for x in range(distX + 1):
            plot[nextX][nextY] = plot[nextX][nextY] + 1
            nextX = nextX + deltaX
            nextY = nextY + deltaY

print(plot)


# Count intercepting points
count = 0
for x in range(sizeX + 1):
    for y in range(sizeY + 1):
        if plot[x][y] > 1:
            count = count + 1

print("Intercepting points: " + str(count))