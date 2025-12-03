from tabnanny import check

import sys
from Common.RowInput import RowInput
import re

class Processor:

    answer = 0

    def getAnswer(self):
        return self.answer

    def __init__(self, filename):

        knownValues = {}
        equations = []

        with open(filename) as fp:
            for line in fp:
                colonIndex = line.find(':')
                if colonIndex > 0:
                    key = line[:colonIndex]
                    value = line[(colonIndex + 1):].strip()
                    knownValues.update({key: True if value == '1' else False})

                arrow = line.find('>')
                if arrow > 0:
                    splits = line.split(' ')
                    record = Equation(splits[0], splits[1], splits[2], splits[4].strip())
                    equations = equations + [record]


        # 0-1
        # 2-3
        # 4-5
        # 5-6

        i1 = (0, 0)
        i2 = (0, 0)
        i3 = (0, 0)
        i4 = (0, 0)
        limit = len(equations)
        while True:



        for i1 in range(limit):
            for i2 in range(limit):
                if i1 == i2:
                    continue

                equationsCopy = equations.copy()
                equation1 = equationsCopy[i1]
                equation2 = equationsCopy[i2]

                temp = equation1.c
                equation1.c = equation2.c
                equation2.c = temp

                if self.solveEquations(knownValues, equations):
                    print(equation1)
                    print(equation2)


    def solveEquations(self, knownValues, equations):

        totalCount = len(equations)
        for i in range(100):
            doneCount = 0
            for equation in equations:
                equation.maybeCalculate(knownValues)

                if equation.complete:
                    doneCount += 1

            if totalCount == doneCount:
                for key, value in knownValues.items():
                    if key.startswith('z') and value:
                        number = int(key[1:])
                        numberToAdd = pow(2, number)
                        # print("{} {}".format(key, numberToAdd))
                        self.answer = self.answer + numberToAdd

                break

        # Check if solvable
        for equation in equations:
            if not equation.complete:
                return False

        # print(knownValues)
        # for equation in equations:
        #     print(equation)

        xValues = []
        yValues = []
        zValues = []
        for k, v in knownValues.items():
            if k.startswith('x'):
                xValues += [(k, v)]
            if k.startswith('y'):
                yValues += [(k, v)]
            if k.startswith('z'):
                zValues += [(k, v)]

        xValues.sort()
        yValues.sort()
        zValues.sort()

        print(xValues)
        print(yValues)
        print(zValues)

        x = 0
        y = 0
        z = 0
        for i in range(len(xValues)):
            xTuple = xValues[i]
            yTuple = yValues[i]
            zTuple = zValues[i]
            xIsOn = xTuple[1]
            yIsOn = yTuple[1]
            zIsOn = zTuple[1]

            if xIsOn:
                x += pow(2, i)
            if yIsOn:
                y += pow(2, i)
            if zIsOn:
                z += pow(2, i)

        print("{} + {} = {} ??? {}".format(x, y, x+y, z))

        return (x+y) == z

        # badOnes = []
        # for i in range(len(xValues)):
        #     xTuple = xValues[i]
        #     yTuple = yValues[i]
        #     zTuple = zValues[i]
        #     xIsOn = xTuple[1]
        #     yIsOn = yTuple[1]
        #     zIsOn = zTuple[1]
        #
        #     good = False
        #     if xIsOn and yIsOn and zIsOn:
        #         # print("Good. All on {}".format(i))
        #         good = True
        #     elif not zIsOn:
        #         if not xIsOn and yIsOn and not zIsOn:
        #             # print("x is false and z false {}".format(i))
        #             good = True
        #         if xIsOn and not yIsOn and not zIsOn:
        #             # print("y is false and z false {}".format(i))
        #             good = True
        #
        #     if not good:
        #         badOnes += [i]

        # print(badOnes)

class Equation:

    complete = False

    a = None
    operator = None
    b = None
    c = None

    valueA = None
    valueB = None
    valueC = None

    def __init__(self, a, operator, b, c):
        self.a = a
        self.operator = operator
        self.b = b
        self.c = c

    def __str__(self):
        return "{}({}) {} {}({}) -> {}({})".format(self.a, self.valueA, self.operator, self.b, self.valueB, self.c, self.valueC)

    def maybeCalculate(self, knownValues):
        if self.complete:
            # match self.operator:
            #     case "XOR":
            #         print((self.valueA != self.valueB) == self.valueC)
            #     case "OR":
            #         print((self.valueA or self.valueB) == self.valueC)
            #     case "AND":
            #         print((self.valueA and self.valueB) == self.valueC)
            return
        self.valueA = knownValues.get(self.a)
        self.valueB = knownValues.get(self.b)
        self.valueC = knownValues.get(self.c)
        match self.operator:
            case "XOR":
                if self.valueA is not None and self.valueB is not None:
                    self.valueC = (self.valueA != self.valueB)
                    knownValues.update({self.c: self.valueC})
                    self.complete = True
                if self.valueA is not None and self.valueC is not None:
                    self.valueB = (self.valueA != self.valueC)
                    knownValues.update({self.b: self.valueB})
                    self.complete = True
                if self.valueB is not None and self.valueC is not None:
                    self.valueA = (self.valueB != self.valueC)
                    knownValues.update({self.a: self.valueA})
                    self.complete = True
            case "OR":
                if self.valueA is not None and self.valueB is not None:
                    self.valueC = (self.valueA or self.valueB)
                    knownValues.update({self.c: self.valueC})
                    self.complete = True
            case "AND":
                if self.valueA is not None and self.valueB is not None:
                    self.valueC = (self.valueA and self.valueB)
                    knownValues.update({self.c: self.valueC})
                    self.complete = True
