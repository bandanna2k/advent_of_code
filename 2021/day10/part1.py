from fileIO import getAllLines

def getScore(filename):

    allLines = getAllLines(filename)

    scores = dict()
    scores[")"] = 3
    scores["]"] = 57
    scores["}"] = 1197
    scores[">"] = 25137

    opposites = dict()
    opposites[")"] = "("
    opposites["]"] = "["
    opposites["}"] = "{"
    opposites[">"] = "<"

    total = 0
    for line in allLines:
        error = getError(line)

        if error == None:
            continue
        else:
            opposite = opposites[error]
            score = scores[error]
            total = total + score

    return total


def getError(input):

    size = len(input)
    halfSize = (size + 1) / 2

    openList = "[<{("
    closeList = "]>})"

    while "<>" in input or "[]" in input or "{}" in input or "()" in input:
        input = input.replace("<>", "")
        input = input.replace("[]", "")
        input = input.replace("{}", "")
        input = input.replace("()", "")

    if len(input) == 0:
        return None

    print(input)

    for i in range(len(input)):
        value = input[i]
        if value in closeList:
            return value

    return None