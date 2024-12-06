
counts = dict()

# Create array
for i in range(8 + 1):
    counts[i] = 0

# Initial set
with open("input.txt") as f:
    reading = True
    countToAdd = 0
    while reading:

        valueRead = int(f.read(1))
        counts[valueRead] = counts[valueRead] + 1

        valueRead = f.read(1) # EOF or comma
        if valueRead != ",":
            reading = False

print(counts)

days = 256
for j in range(days):

    # record 0 count
    count0 = counts[0]
    # 1 -> 0
    # 2 -> 1
    # 3 -> 2
    # 4 -> 3
    # 5 -> 4
    # 6 -> 5
    # 7 -> 6
    # 8 -> 7
    for i in range(1, 9):
        counts[i - 1] = counts[i]
    # empty 8
    counts[8] = 0
    # 0 -> 6, add 8
    counts[6] = counts[6] + count0
    counts[8] = counts[8] + count0
    # print(counts)
    # print("{} {} {} {}  {} {} {} {}  {}".format(counts[0], counts[1], counts[2], counts[3], counts[4], counts[5], counts[6], counts[7], counts[8]))

totalCount = 0
for i in range(9):
    totalCount = totalCount + counts[i]

print("Total count {}".format(totalCount))
