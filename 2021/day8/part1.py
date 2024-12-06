from adventOfCode.year2021 import fileIO
from day8 import calculate

scenario = 0
if 0 == scenario:
    file = 'input.txt'
elif 1 == scenario:
    file = 'test.txt'
elif 2 == scenario:
    file = 'test2.txt'

allLines = fileIO.getAllLines(file)

# Result set
results = dict()
results["1"] = 0
results["4"] = 0
results["7"] = 0
results["8"] = 0

i = 0
while i < len(allLines):

    # mashedUpDigits = allLines[i].replace("|", "").strip()
    # i = i + 1
    #
    # mashedUpInputs = allLines[i].strip()
    # i = i + 1
    part1and2 = allLines[i].split("|")
    mashedUpDigits = part1and2[0].strip()
    mashedUpInputs = part1and2[1].strip()
    i = i + 1

    calc = calculate.calculate(mashedUpDigits, mashedUpInputs)
    print("Calc {}".format(calc))

    for letter in calc:
        if letter in results:
            results[letter] = results[letter] + 1

print(results)
total = 0
for value in results.values():
    total = total + value
print(total)

