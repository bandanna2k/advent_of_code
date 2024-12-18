from sre_constants import error
from tabnanny import check

import sys
from dockerpty import start
from more_itertools.more import adjacent

from Common.RowInput import RowInput
import re

class Processor:

    answer = 0

    start = (0, 0)
    goal = (-1, -1)
    walls = set()

    def getAnswer(self):
        return self.answer

    def __init__(self, filename, goal):

        self.goal = goal
        with open(filename) as fp:
            for line in fp:
                wall = line.split(',')
                wall = (int(wall[1]), int(wall[0]))
                self.walls.add(wall)

        # print(walls)

        # for x in range(0,6):
        #     s = ""
        #     for y in range(0,6):
        #         if (x, y) in self.walls:
        #             s = "{}#".format(s)
        #         else:
        #             s = "{}.".format(s)
        #     print(s)

        bfs = []
        visited = set()

        visited.add(self.start)
        bfs = bfs + [self.start]

        parent = [start]
        while bfs.__len__() > 0:
            pop = bfs.pop()
            path = [pop]

            adjacent = self.getAdjacent(pop)
            adjacent = {pos for pos in adjacent if not pos in self.walls}

            for pos in adjacent:
                newPath = [path]
                if not pos in visited:
                    if pos == self.goal:
                        print(newPath)
                        return

                    bfs = bfs + [pos]
        print("done")

    def getAdjacent(self, pos):

        x = pos[0]
        y = pos[1]

        n = y - 1
        s = y + 1
        w = x - 1
        e = x + 1

        result = []
        if n > 0 and n <= self.goal[1]:
            result = result + [(x, n)]
        if s > 0 and s <= self.goal[1]:
            result = result + [(x, s)]
        if w > 0 and w <= self.goal[0]:
            result = result + [(w, y)]
        if e > 0 and e <= self.goal[0]:
            result = result + [(e, y)]
        return result
