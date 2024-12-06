import sys

from fileIO import getAllLines, myPrint


def getSolution(filename):

    lines = getAllLines(filename)

    myPrint(lines)

    return "X"


class Projectiles:

    def __init__(self, x1, x2, y1, y2, startX, startY):
        self.target = Box(x1, x2, y1, y2)
        self.start = Point(startX, startY)

    def project(self, initialVelocityX, initialVelocityY):

        point = self.start.copy()
        currentVelocityX = initialVelocityX
        currentVelocityY = initialVelocityY
        while not self.target.hasPassed(point):

            #print(point)
            point.x = point.x + currentVelocityX
            point.y = point.y + currentVelocityY

            if currentVelocityX == 0:
                pass # do nothing
            elif currentVelocityX > 0:
                currentVelocityX = currentVelocityX - 1
            else:
                currentVelocityX = currentVelocityX + 1

            currentVelocityY = currentVelocityY - 1

            if self.target.contains(point):
                print("Hit " + str(point))
                return True
        return False

    def findMaxProjection(self):

        highestInitialVelocityY = 1 - sys.maxsize
        associatedInitialVelocityX = 1 - sys.maxsize
        countOfHits = 0

        maxProjectionY = max(abs(self.target.y1), abs(self.target.y2)) + 1
        maxProjectionX = max(self.target.x1, self.target.x2) + 1
        for i in range(-maxProjectionY, maxProjectionY):
            initialVelocityY = i

            for j in range(1, maxProjectionX):
                initialVelocityX = j

                if self.project(initialVelocityX, initialVelocityY):
                    countOfHits = countOfHits + 1
                    if initialVelocityY > highestInitialVelocityY:
                        highestInitialVelocityY = initialVelocityY
                        associatedInitialVelocityX = initialVelocityX

        return "Max reach ({},{}), Count {}".format(associatedInitialVelocityX, highestInitialVelocityY, countOfHits)

class Box:
    def __init__(self, x1, x2, y1, y2):
        self.x1 = x1
        self.x2 = x2
        self.y1 = y1
        self.y2 = y2

    def contains(self, point):
        if (point.x >= self.x1 and point.x <= self.x2) or (point.x >= self.x2 and point.x <= self.x1):
            if (point.y >= self.y1 and point.y <= self.y2) or (point.y >= self.y2 and point.y <= self.y1):
                return True
        return False

    def hasPassed(self, point):
        # if abs(point.x) > max(abs(self.x1), abs(self.x2)) and point.y < min(self.y1, self.y2):
        #     return True
        # return False
        return self.hasPassedBelow(point) or self.hasPassedBeyond(point)

    def hasPassedBelow(self, point):
        if point.y < min(self.y1, self.y2):
            return True
        return False

    def hasPassedBeyond(self, point):
        if abs(point.x) > max(abs(self.x1), abs(self.x2)):
            return True
        return False

class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def copy(self):
        return Point(self.x, self.y)

    def __str__(self):
        return "{},{}".format(self.x, self.y)

    def __repr__(self):
        return self.__str__()