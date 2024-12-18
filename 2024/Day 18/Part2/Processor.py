from sre_constants import error
from tabnanny import check

import sys
from dockerpty import start
from more_itertools.more import adjacent

from Common.RowInput import RowInput
import re

class Processor:

    answer = None

    start = (0, 0)
    goal = (-1, -1)

    def getAnswer(self):
        return self.answer

    def __init__(self, filename, goal, cutoff):

        self.goal = goal
        walls = []
        with open(filename) as fp:
            for line in fp:
                wall = line.split(',')
                wall = (int(wall[0]), int(wall[1]))
                walls = walls + [wall]

        limit = cutoff
        newWalls = set()
        while 1 == 1:
            for i in range(limit):
                newWalls.add(walls[i])

            bfs = []
            visited = set()
            visitedPath = {}

            visited.add(self.start)
            visitedPath[self.start] = self.start

            bfs = bfs + [self.start]

            path = None
            while bfs and not path:
                current = bfs.pop()

                adjacent2 = self.getAdjacent(current)
                adjacent2 = {pos for pos in adjacent2 if not pos in newWalls}
                for pos in adjacent2:
                    if not pos in visited:
                        visited.add(pos)

                        if not pos in visitedPath:
                            visitedPath[pos] = current

                        if pos == self.goal:
                            path = self.getPath(visitedPath, pos)
                            # print("Path: {}".format(path))
                            break

                        bfs = [pos] + bfs
            limit = limit + 1
            if not path:
                self.answer = walls[i]

                # for y in range(0,70):
                #     s = ""
                #     for x in range(0,70):
                #         if (x, y) == walls[i]:
                #             s = "{}B".format(s)
                #         elif (x, y) in visited:
                #             s = "{} ".format(s)
                #         elif (x, y) in walls:
                #             s = "{}#".format(s)
                #         else:
                #             s = "{}.".format(s)
                #     print(s)

                return

    def getAdjacent(self, pos):

        x = pos[0]
        y = pos[1]

        n = y - 1
        s = y + 1
        w = x - 1
        e = x + 1

        result = []
        if 0 <= e <= self.goal[0]:
            result = result + [(e, y)]
        if 0 <= s <= self.goal[1]:
            result = result + [(x, s)]
        if 0 <= w <= self.goal[0]:
            result = result + [(w, y)]
        if 0 <= n <= self.goal[1]:
            result = result + [(x, n)]
        return result

    def getPath(self, visitedPath, pos):
        listPath = []
        currPos = pos
        nextPos = visitedPath[pos]
        while nextPos != currPos:
            listPath = listPath + [currPos]

            currPos = nextPos
            nextPos = visitedPath[nextPos]
        return listPath + []
