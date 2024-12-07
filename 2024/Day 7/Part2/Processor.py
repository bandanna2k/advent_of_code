import sys
from Common.RowInput import RowInput
import re

class Processor:

    answer = 0

    def getAnswer(self):
        return self.answer

    def __init__(self, filename):

        list = []
        with open(filename) as fp:
            for line in fp:
                split = line.split(':')
                factors = (split[1]).split(' ')
                factors = filter(lambda x: x.__len__() > 0, factors)
                factors = [int(i) for i in factors]
                list = list + [(int(split[0]), factors)]

        # for record in list:
        #     print(record)

        for record in list:
            result = record[0]
            factors = record[1]
            # operators = []
            # for i in range(factors.__len__() - 1):
            #     operators = operators + [0]

            canCalc = False
            if canCalc or self.checkResult(result, factors, [], 0):
                canCalc = True
            if canCalc or self.checkResult(result, factors, [], 1):
                canCalc = True
            if canCalc or self.checkResult(result, factors, [], 2):
                canCalc = True

            if canCalc:
                self.answer = self.answer + result

    def checkResult(self, result, factors, operators, nextOperator):

        if operators.__len__() + 1 == factors.__len__():
            calc = factors[0]
            for i in range(operators.__len__()):
                match operators[i]:
                    case 0:
                        calc = calc + factors[i + 1]
                    case 1:
                        calc = calc * factors[i + 1]
                    case 2:
                        calc = int("{}{}".format(calc, factors[i + 1]))
            return calc == result

        else:
            if self.checkResult(result, factors, operators + [nextOperator], 0):
                print("{} = {} {}".format(result, factors, operators))
                return True
            if self.checkResult(result, factors, operators + [nextOperator], 1):
                print("{} = {} {}".format(result, factors, operators))
                return True
            if self.checkResult(result, factors, operators + [nextOperator], 2):
                print("{} = {} {}".format(result, factors, operators))
                return True
            return False




