import sys

file = 'test.txt'
file = 'input.txt'

depth = 0   # up/down (depth)
horizontal = 0   # forward/back (horizontal)
aim = 0

with open(file) as f:
    lines = f.readlines()
    for line in lines:
        totalLine = line.strip()

        words = totalLine.split(" ")

        direction = words[0]
        distance = int(words[1])

        if direction == "forward":
            horizontal = horizontal + distance
            depth = depth + (aim * distance)

        elif direction == "up":
            aim = aim - distance
        elif direction == "down":
            aim = aim + distance

        else:
            raise RuntimeError('cannot computer direction ' + direction)

if depth == 0:
    depth = 1
if horizontal == 0:
    horizontal = 1

print(depth * horizontal)
print("depth {}, horiz {}, aim {}".format(depth, horizontal, aim))

