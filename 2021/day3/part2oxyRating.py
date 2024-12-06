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

    if ons > offs:
        removeBit = "1"
    elif offs > ons:
        removeBit = "0"
    else:
        removeBit = "1"
    #    print("High {} Ons {} Offs {}".format(highest, ons, offs))

    # Remove items from lines
    for y in reversed(range(len(theLines))):
        line = theLines[y]

        value = line[position]

        if value != removeBit:
            theLines.pop(y)

    if len(theLines) == 1:
        theLine = theLines[0]
        nTheLine = int(theLine, 2)
        print(str(theLines) + " " + str(nTheLine))




#
# for y in range(maxY):
#     line = allLines[y]
#
#     ons = 0
#     offs = 0
#     for x in range(len(line)):
#         value = line[x]
#
#         if value == "1":
#             ons = ons + 1
#         else:
#             off = offs + 0
#
#     if ons > offs:
#         highest = "1"
#     elif offs > ons:
#         highest = "0"
#
#



#
#
# # Oxy
# def getScore(input1, input2):
#     maxX = min(len(input1), len(input2))
#     for x in range(maxX):
#         if input1[x] != input2[x]:
#             return x
#     return maxX
#
# # Create map of scores + max score
# lineNoToScores = dict()
# maxScore = 0
# for y in range(len(allLines)):
#
#     line = allLines[y]
#     score = getScore(line, gamma)
#
#     if score > maxScore:
#         maxScore = score
#
#     lineNoToScores[y] = score
#
# # Get list of lines with max score
# maxScores = []
# for y in range(len(allLines)):
#     if lineNoToScores[y] == maxScore:
#         maxScores.append(y)
#
# # Display max scores
# print("Max score indices" + str(maxScores))
# for y in maxScores:
#     maxValue = allLines[y]
#     nMaxValue = int(maxValue, 2)
#     print("Max {} => {}".format(maxValue, nMaxValue))


print(1427 * 2502)