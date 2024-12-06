import sys
from Common.RowInput import RowInput
import re

class Processor:

    answer = 0

    def getAnswer(self):
        return self.answer

    def __init__(self, filename):

        grid = []
        origin = None
        originalDirection = '^'
        with open(filename) as fp:
            for line in fp:
                split = list(line.strip('\n'))
                grid = grid + [split]

                try:
                    maybeX = line.index(originalDirection)
                    origin = (maybeX, grid.__len__() - 1)
                except ValueError:
                    pass

        maxX = grid[0].__len__()
        maxY = grid.__len__()

        for obsX in range(maxX):
            for obsY in range(maxY):
                x = origin[0]
                y = origin[1]

                # if obsX == 3 and obsY == 6:
                #     pass
                # else:
                #     continue

                if grid[obsY][obsX] == '#':
                    continue

                grid[obsY][obsX] = '#'


                turns = {}
                nextX = None
                nextY = None
                direction = originalDirection
                while True:
                    match direction:
                        case '^':
                            nextX = x
                            nextY = y - 1
                        case '>':
                            nextX = x + 1
                            nextY = y
                        case '<':
                            nextX = x - 1
                            nextY = y
                        case 'd':
                            nextX = x
                            nextY = y + 1

                    if nextX < 0 or nextX == (maxX - 0) or nextY < 0 or nextY == (maxY - 0):
                        break

                    if grid[nextY][nextX] == '#':
                        turn = '{}|{}|{}'.format(x, y, direction)
                        # print(turns)
                        if turns.get(turn):
                            self.answer = self.answer + 1
                            # print("({},{})   {}".format(obsX, obsY, turns))
                            # for row in grid:
                            #     print(row)
                            break

                        turns = turns | {turn: turn}
                        match direction:
                            case '^':
                                direction = '>'
                            case '>':
                                direction = 'd'
                            case '<':
                                direction = '^'
                            case 'd':
                                direction = '<'
                    else:
                        x = nextX
                        y = nextY

                grid[obsY][obsX] = ' '

        # for row in grid:
        #     print(row)
