from day10.part1 import getError
from fileIO import getAllLines

def getCompletionScore(filename):

    allLines = getAllLines(filename)

    scores = dict()
    scores[")"] = 1
    scores["]"] = 2
    scores["}"] = 3
    scores[">"] = 4

    lineCompletionScores = []

    total = 0
    for line in allLines:
        error = getError(line)

        if error == None:
            completionString = getCompleteString(line)
            print("Complete string {}".format(completionString))

            lineCompletionScore = 0
            for value in completionString:
                score = scores[value]
                lineCompletionScore = lineCompletionScore * 5
                lineCompletionScore = lineCompletionScore + score

            print("Line completion score {}".format(lineCompletionScore))

            lineCompletionScores.append(lineCompletionScore)

            continue
        else:
            score = scores[error]
            total = total + score

    lineCompletionScores = sorted(lineCompletionScores)
    while len(lineCompletionScores) != 1:
        lineCompletionScores.pop(len(lineCompletionScores) - 1)
        lineCompletionScores.pop(0)

    return lineCompletionScores[0]

def getCompleteString(input):

    opposites = dict()
    opposites["("] = ")"
    opposites["["] = "]"
    opposites["{"] = "}"
    opposites["<"] = ">"

    while "<>" in input or "[]" in input or "{}" in input or "()" in input:
        input = input.replace("<>", "")
        input = input.replace("[]", "")
        input = input.replace("{}", "")
        input = input.replace("()", "")

    print(input)

    result = ""
    for i in reversed(range(len(input))):
        value = input[i]
        opposite = opposites[value]
        result = result + opposite

    print(result)
    return result