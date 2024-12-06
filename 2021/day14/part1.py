import sys
import time

from fileIO import getAllLines

def getAnswerBruteFast(filename, iterations):
    results = getLetterFrequency(filename, iterations)

    # Get most and least frequent
    maxi = 0
    mini = sys.maxsize
    for key in results.keys():
        value = results[key]
        maxi = max(value, maxi)
        mini = min(value, mini)
    return maxi - mini


def getLetterFrequency(filename, iterations):

    lines = getAllLines(filename)
    lineCount = len(lines)

    start = lines[0]
    print(start)
    map = dict()
    for i in range(2, lineCount):
        line = lines[i]

        values = line.split(" -> ")
        map[values[0]] = values[1]
    #print(map)

    value = start
    for i in range(iterations):
        value = step(map, value)
        print("Step {}  {}".format(i, value))

    # Create results
    results = dict()
    for i in range(len(value)):
        letter = value[i]
        if letter not in results.keys():
            results[letter] = 0
        results[letter] = results[letter] + 1

    print(results)

    return results

def step(map, value):
    newValue = ""
    length = len(value)

    firstValue = value[0]
    for i in range(1, length):
        secondValue = value[i]

        toProcess = firstValue + secondValue
        mapping = map[toProcess]
        newValue = newValue + firstValue + mapping

        firstValue = secondValue
    newValue = newValue + secondValue

    #assert len(newValue) == len(value) + len(value) - 1

    return newValue
