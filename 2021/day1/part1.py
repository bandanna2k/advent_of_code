import sys

prev = sys.maxsize
current = 0
countOfIncreases = 0

with open('input.txt') as f:
    lines = f.readlines()
    for line in lines:
        current = int(line.strip())

        if current > prev:
            countOfIncreases = countOfIncreases + 1

        prev = current

print(countOfIncreases)
