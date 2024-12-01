
class Person:
    def __init__(self, no1, no2):
        self.no1 = no1
        self.no2 = no2

# Opening file
file = 'real_input.txt'
file1 = open(file, 'r')
count = 0

# Using for loop
print("Using for loop")
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


length = 0
sumOfDifference = 0
while lefties.__len__() != 0:

    print(lefties)

    left = lefties.pop(0)
    right = righties.pop(0)

    difference = abs(left - right)

    sumOfDifference = sumOfDifference + difference

print("Sum of diffs: {}".format(sumOfDifference))

# Closing files
file1.close()