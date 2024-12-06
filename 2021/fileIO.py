

def getAllLines(fileName):
    with open(fileName) as f:
        lines = f.readlines()
    allLines = []
    for line in lines:
        allLines.append(line.strip())

    return allLines


def myPrint(grid):
    print("=======================")
    for row in grid:
        print(row)




def getStringFromFile(fileName):
    lines = getAllLines(fileName)
    result = ""
    for line in lines:
        result = "{}\n{}".format(result, line)
    return result