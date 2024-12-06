import sys

from adventOfCode.year2021 import fileIO

scenario = 0
if 0 == scenario:
    file = 'input.txt'
elif 1 == scenario:
    file = 'test.txt'
elif 2 == scenario:
    file = 'test2.txt'
else:
    exit()

allLines = fileIO.getAllLines(file)

allNumbers = list(map(int, allLines[0].split(",")))

print(allNumbers)

# Get min/max int
myMin = 0
myMax = 0
for number in allNumbers:
    myMax = max(myMax, number)
    myMin = min(myMin, number)

# Loop through getting fuel usage
minFuel = sys.maxsize
minPosition = sys.maxsize
for position in range(myMin, myMax + 1):

    # Calc fuel
    totalFuelForPosition = 0
    for number in allNumbers:
        fuelUsageForNumber = abs(number - position)
        totalFuelForPosition = totalFuelForPosition + fuelUsageForNumber

    if totalFuelForPosition < minFuel:
        minFuel = totalFuelForPosition
        minPosition = position


print("Position {}, Fuel {}".format(minPosition, minFuel))
