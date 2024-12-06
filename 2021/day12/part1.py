from fileIO import getAllLines

def getPathsCount(filename):

    allLines = getAllLines(filename)

    theMap = getGraph(allLines)

    print(theMap)

    paths = []
    search(theMap, "START", set(), [], paths)

    return len(paths)

def search(theMap, key, visited, pathSoFar, paths):

    copyOfPath = pathSoFar.copy()
    copyOfPath.append(key)

    if key == "END":
        paths.append(copyOfPath)
        return

    # Recursive search
    links = theMap[key]
    for link in links:

        if link in copyOfPath and link[0].islower():
            continue

        search(theMap, link, visited, copyOfPath, paths)

def getGraph(lines):
    result = dict()
    for line in lines:
        items = line.split("-")
        point1 = items[0]
        point2 = items[1]

        if point1 in result:
            point1links = result[point1]
        else:
            point1links = set()
            result[point1] = point1links

        if point2 != "START":
            point1links.add(point2)

        if point2 in result:
            point2links = result[point2]
        else:
            point2links = set()
            result[point2] = point2links

        if point1 != "START":
            point2links.add(point1)

    result["END"] = []
    return result