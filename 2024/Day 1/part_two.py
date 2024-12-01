
class Person:
    def __init__(self, no1, no2):
        self.no1 = no1
        self.no2 = no2

# Opening file
file = 'real_input.txt'
file1 = open(file, 'r')

print("Parsing input")
lefties = []
righties = []
for line in file1:
    values = line.split(' ')

    record = Person(values[0], values[1])

    values = [v for v in values if v != '']

    lefties = lefties + [int(values[0])]
    righties = righties + [int(values[1])]

lefties.sort()
righties.sort()

setOfLefties = set(lefties)

valueToCount = {}
for left in setOfLefties:
    count = 0
    for right in righties:
        if left == right:
            valueCount = valueToCount.get(left)
            if valueCount:
                valueCount = valueCount + 1
                valueToCount[left] = valueCount
            else:
                valueToCount[left] = 1

length = 0
sumOfDifference = 0
while lefties.__len__() != 0:

    left = lefties.pop(0)
    right = righties.pop(0)

    multiplier = valueToCount.get(left)
    if multiplier:
        difference = abs((left * multiplier))
        sumOfDifference = sumOfDifference + difference

print("Sum of diffs: {}".format(sumOfDifference))






file1.close()