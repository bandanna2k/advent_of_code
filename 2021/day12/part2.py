from day12.part1 import getGraph
from fileIO import getAllLines

def getPart2(filename):

    allLines = getAllLines(filename)

    theMap = getGraph(allLines)

    print(theMap)

    paths = []
    search(theMap, "START", set(), [], paths, None)

    return len(paths)

def search(theMap, key, visited, pathSoFar, paths, singleCave):

    copyOfPath = pathSoFar.copy()
    copyOfPath.append(key)

    if key == "END":
        paths.append(copyOfPath)
        print(copyOfPath)
        return

    # Recursive search
    links = theMap[key]
    for link in links:

        newSingleCave = singleCave
        if link in copyOfPath and link[0].islower():
            if singleCave != None:
                continue
            else:
                newSingleCave = link

        search(theMap, link, visited, copyOfPath, paths, newSingleCave)
