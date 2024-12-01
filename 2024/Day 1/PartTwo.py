from PartOne import PartOne

class PartTwo(PartOne):

    def __init__(self, filename):
        super().__init__(filename)

    def differences(self):

        setOfLefties = set(self.lefties)

        valueToCount = {}
        for left in setOfLefties:
            for right in self.righties:
                if left == right:
                    valueCount = valueToCount.get(left)
                    if valueCount:
                        valueCount = valueCount + 1
                        valueToCount[left] = valueCount
                    else:
                        valueToCount[left] = 1

        sumOfDifference = 0
        while self.lefties.__len__() != 0:

            left = self.lefties.pop(0)
            right = self.righties.pop(0)

            multiplier = valueToCount.get(left)
            if multiplier:
                difference = abs((left * multiplier))
                sumOfDifference = sumOfDifference + difference

        print("Sum of diffs: {}".format(sumOfDifference))
        return sumOfDifference


