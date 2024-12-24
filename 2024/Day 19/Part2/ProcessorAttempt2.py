from PIL.ImageChops import offset


class Processor:

    answer = 0

    def getAnswer(self):
        return self.answer

    def __init__(self, filename):

        lines = []
        with open(filename) as fp:
            for line in fp:
                lines = lines + [line.strip()]

        patterns = lines[0].split(',')
        patterns = [p.strip() for p in patterns]

        lines.pop(0)
        lines.pop(0)

        ############################################

        for line in lines:
            print(line)

            leftQ = []
            rightQ = []
            foundLeft = set()
            foundRight = set()

            # 1st Left
            for pattern in patterns:
                offsetLine = line[0:]
                if pattern == line:
                    continue
                if offsetLine.startswith(pattern):
                    leftQ.insert(0, (0, [pattern]))
                    foundLeft.add("{}-{}".format(0, pattern))
            # 1st Right
            for pattern in patterns:
                offset = max(len(line) - len(pattern), 0)
                offsetLine = line[offset:]
                if pattern == line:
                    continue
                if offsetLine.startswith(pattern):
                    rightQ.insert(offset, (offset, [pattern]))
                    foundRight.add("{}-{}".format(offset, pattern))

            # Bidirectional Loop
            while len(leftQ) > 0 or len(rightQ) > 0:
                if len(leftQ) > 0:
                    currentItem = leftQ.pop()
                    currentIndex = currentItem[0]
                    currentPatterns = currentItem[1]
                    currentKey = "{}-{}".format(currentIndex, currentPatterns[-1])

                    if currentKey in foundRight:
                        self.answer = self.answer + 1
                        print("Route found. Meet at {} {}".format(currentPatterns, foundRight))
                        break

                    for pattern in patterns:
                        offset = currentIndex + len(currentPatterns[-1])
                        offsetLine = line[offset:]
                        if pattern == line:
                            continue
                        if offsetLine.startswith(pattern):
                            leftQ.insert(0, (offset, currentPatterns + [pattern]))
                            foundLeft.add("{}-{}".format(offset, pattern))

                if len(rightQ) > 0:
                    currentItem = rightQ.pop()
                    currentIndex = currentItem[0]
                    currentPatterns = currentItem[1]
                    currentKey = "{}-{}".format(currentIndex, currentPatterns[-1])

                    if currentKey in foundLeft:
                        self.answer = self.answer + 1
                        print("Route found. Meet at {} {}".format(currentPatterns, foundLeft))
                        break

                    for pattern in patterns:
                        offset = max(currentIndex - len(pattern), 0)
                        offsetLine = line[offset:]
                        if pattern == line:
                            continue
                        if offset >= 0 and offsetLine.startswith(pattern):
                            rightQ.insert(0, (offset, currentPatterns + [pattern]))
                            foundRight.add("{}-{}".format(offset, pattern))

            # print(foundLeft)
            # print(foundRight)

