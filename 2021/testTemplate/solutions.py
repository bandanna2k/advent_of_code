import sys

from fileIO import getAllLines, myPrint


def getSolution(filename):

    lines = getAllLines(filename)

    myPrint(lines)

    return "X"