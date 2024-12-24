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

        totalCount = len(equations)
        for i in range(100):
            doneCount = 0
            for equation in equations:
                equation.maybeCalculate(knownValues)

                if equation.complete:
                    doneCount = doneCount + 1

            if totalCount == doneCount:
                for key, value in knownValues.items():
                    if key.startswith('z') and value:
                        number = int(key[1:])
                        numberToAdd = pow(2, number)
                        print("{} {}".format(key, numberToAdd))
                        self.answer = self.answer + numberToAdd

                break


        print(knownValues)
        for equation in equations:
            print(equation)




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
