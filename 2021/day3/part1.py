rows = 0
cols = 0
scenario = 1
if 0 == scenario:
    file = 'input.txt'
    rows = 1000
    cols = 12
elif 1 == scenario:
    file = 'test.txt'
    rows = 12
    cols = 5

values = [[0 for y in range(rows)] for x in range(cols)]

x = 0
y = 0

# Build array
with open(file) as f:
    lines = f.readlines()

    y = 0
    for lineWithEOL in lines:
        line = lineWithEOL.strip()

        x = 0
        for bit in line:
            values[x][y] = (bit == "1")

            x = x + 1
        y = y + 1

# Display array
for y in range(rows):
    s = ""
    for x in range(cols):
        if values[x][y]:
            s = s + "1"
        else:
            s = s + "0"
    print(s)

# Get gamma
gamma = ""
for x in range(cols):
    on = 0
    off = 0
    for y in range(rows):
        if values[x][y]:
            on = on + 1
        else:
            off = off + 1

    if on > off:
        gamma = gamma + "1"
    else:
        gamma = gamma + "0"

print("Gamma:" + gamma)
nGamma = int(gamma, 2)
print("Gamma:" + str(nGamma))

# Epsilon
epsilon = ""
for x in range(cols):
    on = 0
    off = 0
    for y in range(rows):
        if values[x][y]:
            on = on + 1
        else:
            off = off + 1

    if on < off:
        epsilon = epsilon + "1"
    else:
        epsilon = epsilon + "0"
print("Epsilon:" + epsilon)
nEpsilon = int(epsilon, 2)
print("Epsilon:" + str(nEpsilon))

# Answer Part 1
print(nGamma * nEpsilon)



# Populate list of lines
allLines = []
with open(file) as f:
    lines = f.readlines()
    for line in lines:
        allLines.append(line.strip())

# Oxy
def getScore(input1, input2):
    maxX = min(len(input1), len(input2))
    for x in range(maxX):
        if input1[x] != input2[x]:
            return x
    return maxX

# Create map of scores + max score
lineNoToScores = dict()
maxScore = 0
for y in range(len(allLines)):

    line = allLines[y]
    score = getScore(line, gamma)

    if score > maxScore:
        maxScore = score

    lineNoToScores[y] = score

# Get list of lines with max score
maxScores = []
for y in range(len(allLines)):
    if lineNoToScores[y] == maxScore:
        maxScores.append(y)

# Display max scores
print("Max score indices" + str(maxScores))
for y in maxScores:
    maxValue = allLines[y]
    nMaxValue = int(maxValue, 2)
    print("Max {} => {}".format(maxValue, nMaxValue))