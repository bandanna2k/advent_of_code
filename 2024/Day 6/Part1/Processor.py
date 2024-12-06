import sys
from Common.RowInput import RowInput
import re

class Processor:

    answer = 0

    def getAnswer(self):
        return self.answer

    def __init__(self, filename):

        grid = []
        position = None

        direction = '^'
        with open(filename) as fp:
            for line in fp:
                split = list(line.strip('\n'))
                grid = grid + [split]

                try:
                    maybeX = line.index(direction)
                    position = (maybeX, grid.__len__())
                except ValueError:
                    pass
        print(position)

        maxX = grid[0].__len__()
        maxY = grid.__len__()

        plot = { '{}|{}'.format(position[0], position[1]) }
        while True:

            x = position[0]
            y = position[1]
            next = None
            match direction:
                case '^':
                    next = (x, y - 1)
                case '>':
                    next = (x + 1, y)
                case '<':
                    next = (x - 1, y)
                case 'd':
                    next = (x, y + 1)

            nextX = next[0]
            nextY = next[1]

            if nextX < 0 or nextX == (maxX - 0) or nextY < 0 or nextY == (maxY - 0):
                break

            if grid[nextY][nextX] == '#':
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
                position = next
                print(position)
                plot = plot.union({ '{}|{}'.format(position[0], position[1]) })

        print(plot)
        self.answer = plot.__len__()
