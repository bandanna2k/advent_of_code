scenario = 0
if 0 == scenario:
    file = 'input.txt'
elif 1 == scenario:
    file = 'test.txt'

# Populate list of lines
allLines = []
maxX = 0
with open(file) as f:
    lines = f.readlines()
    for lineWithEOL in lines:
        line = lineWithEOL.strip()
        allLines.append(line)
        maxX = max(maxX, len(line))
maxY = len(allLines)


theLines = allLines
for position in range(maxX):
    #print("Position {}".format(position))

    # Calculate for position, more ones of zeros
    ons = 0
    offs = 0
    for y in range(len(theLines)):
        line = theLines[y]

        value = line[position]
        if value == "1":
            ons = ons + 1
        else:
            offs = offs + 1

    if ons < offs:
        removeBit = "1"
    elif offs < ons:
        removeBit = "0"
    else:
        removeBit = "0"

    #print("High {} Ons {} Offs {}".format(removeBit, ons, offs))

    # Remove items from lines
    for y in reversed(range(len(theLines))):
        line = theLines[y]

        value = line[position]

        if value != removeBit:
            theLines.pop(y)

    print(str(theLines))

    if len(theLines) == 1:
        theLine = theLines[0]
        nTheLine = int(theLine, 2)
        print(str(theLines) + " " + str(nTheLine))


print(1427 * 2502)