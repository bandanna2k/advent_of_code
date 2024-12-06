import sys

currentMinus2 = 100000
currentMinus1 = 100000
currentMinus0 = 100000
prevSum = sys.maxsize
currentSum = sys.maxsize
countOfIncreases = 0

with open('input.txt') as f:
    lines = f.readlines()
    for line in lines:

        currentMinus2 = currentMinus1
        currentMinus1 = currentMinus0
        currentMinus0 = int(line.strip())

        prevSum = currentSum
        currentSum = currentMinus0 + currentMinus2 + currentMinus1
        print("is " + str(currentSum) + " > " + str(prevSum))
        if currentSum > prevSum:
            countOfIncreases = countOfIncreases + 1

print(countOfIncreases)
