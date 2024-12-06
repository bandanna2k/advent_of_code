import sys

from fileIO import getAllLines

def getAnswerByPairs(filename, iterations):

    lines = getAllLines(filename)
    letterScores = getUniqueLetterMap(lines)

    # Initialise letter scores
    for letter in lines[0]:
        letterScores[letter] = letterScores[letter] + 1

    getPairToCount(lines, iterations, letterScores)

    # Get most and least frequent
    maxi = 0
    mini = sys.maxsize
    for key in letterScores:
        value = letterScores[key]
        maxi = max(value, maxi)
        mini = min(value, mini)
    return maxi - mini

def getBlankMapOfPairs(lines):

    uniqueLetters = getUniqueLetterMap(lines)

    pairToCount = dict()
    for i in uniqueLetters.keys():
        for j in uniqueLetters.keys():
            pair = i + j
            pairToCount[pair] = 0
    return pairToCount

def getUniqueLetterMap(lines):

    uniqueLetters = dict()
    for line in lines:
        for char in line:
            if char.isalpha():
                uniqueLetters[char] = 0
    return uniqueLetters


def getPairToCount(lines, iterations, letterScores):

    lineCount = len(lines)

    # Create all pairToCount
    pairToCount = getBlankMapOfPairs(lines)

    # Create mappings
    start = lines[0]
    map = dict()
    for i in range(2, lineCount):
        line = lines[i]

        values = line.split(" -> ")
        pair = values[0]
        mapTo = values[1]
        mappings = []
        mappings.append(pair[0] + mapTo)
        mappings.append(mapTo + pair[1])
        map[values[0]] = mappings
    #print(map)

    # Inject line 1 into counts
    for i in range(len(start) - 1):
        firstValue = start[i]
        secondValue = start[i + 1]

        pair = firstValue + secondValue
        pairToCount[pair] = pairToCount[pair] + 1
    #print(pairToCount)

    # Loop through iterations
    for i in range(iterations):
        pairToNewCount = getBlankMapOfPairs(lines)
        for key in pairToCount:
            countOfPair = pairToCount[key]
            listToAdd = map[key]

            keyToAdd1 = listToAdd[0]
            pairToNewCount[keyToAdd1] = pairToNewCount[keyToAdd1] + countOfPair

            keyToAdd2 = listToAdd[1]
            pairToNewCount[keyToAdd2] = pairToNewCount[keyToAdd2] + countOfPair

            letterToScore = keyToAdd1[1]
            letterScores[letterToScore] = letterScores[letterToScore] + countOfPair

        for key in pairToCount:
            newCount = pairToNewCount[key]
            pairToCount[key] = newCount
        print("Pair counts {}".format(pairToCount))
    return pairToCount
