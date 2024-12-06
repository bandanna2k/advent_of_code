scenario = 0
if 0 == scenario:
    file = 'input.txt'
elif 1 == scenario:
    file = 'test.txt'
elif 2 == scenario:
    file = 'test2.txt'

with open(file) as f:
    lines = f.readlines()
allLines = []
for line in lines:
    allLines.append(line.strip())

# Read gamemaster numbers
gameNos = allLines[0]

# Read boards
boards = []
lineNo = 2
while lineNo < len(allLines):
    board = []

    board.append(allLines[lineNo + 0])
    board.append(allLines[lineNo + 1])
    board.append(allLines[lineNo + 2])
    board.append(allLines[lineNo + 3])
    board.append(allLines[lineNo + 4])
    boards.append(board)

    lineNo = lineNo + 6

# print(gameNos)
# print(boards)


def hasWon(calledNos, board):

    maxY = len(board)

    # Check across
    for y in range(maxY):
        line = board[y]
        values = line.split()

        count = 0
        for x in range(len(values)):
            value = int(values[x])

            if value in calledNos:
                count = count + 1

        if count == 5:
            return True

    # Check down
    maxX = len(board[0].split())
    for x in range(maxX):

        count = 0
        for y in range(maxY):
            values = board[y].split()
            value = int(values[x])

            if value in calledNos:
                count = count + 1

        if count == 5:
            return True

    return False


# Loop through game nos
calledNumbers = []
toCallNos = gameNos.split(",")


def getUncalledSum(calledNumbers, board):
    maxY = len(board)
    uncalled = []
    sumUncalled = 0
    for y in range(maxY):
        line = board[y]
        values = line.split()

        for x in range(len(values)):
            value = int(values[x])
            if value not in calledNumbers:
                uncalled.append(value)
                sumUncalled = sumUncalled + value
    print("Uncalled:" + str(uncalled))
    print("Sum:" + str(sumUncalled))
    return sumUncalled

countOfBoards = len(boards)
boardsThatWon = set()

for callingNo in toCallNos:
    nCallingNo = int(callingNo)
    calledNumbers.append(nCallingNo)
    print(calledNumbers)

    # Loop through boards
    for x in range(len(boards)):
        board = boards[x]

        # Does the board win
        if hasWon(calledNumbers, board):
            boardsThatWon.add(x)

        if len(boardsThatWon) == countOfBoards:
            print("Last called no.: " + str(callingNo))
            uncalledSum = getUncalledSum(calledNumbers, board)
            print("Uncalled sum: " + str(uncalledSum))
            answer = uncalledSum * nCallingNo
            print("Answer: " + str(answer))
            exit()

