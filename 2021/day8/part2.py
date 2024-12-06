from adventOfCode.year2021 import fileIO
from day8 import calculate2

scenario = 0
if 0 == scenario:
    file = 'input.txt'
elif 1 == scenario:
    file = 'test.txt'
elif 2 == scenario:
    file = 'test2.txt'

allLines = fileIO.getAllLines(file)

i = 0
total = 0
while i < len(allLines):

    if scenario == 0:
        part1and2 = allLines[i].split("|")
        mashedUpDigits = part1and2[0].strip()
        mashedUpInputs = part1and2[1].strip()
        i = i + 1
    else:
        mashedUpDigits = allLines[i].replace("|", "").strip()
        i = i + 1

        mashedUpInputs = allLines[i].strip()
        i = i + 1

    calc = calculate2.calculate(mashedUpDigits, mashedUpInputs)
    total = total + int(calc)
    print(calc)

print("Total {}".format(total))