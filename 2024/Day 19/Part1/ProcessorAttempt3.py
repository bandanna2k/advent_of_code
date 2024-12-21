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

            counts = []
            for i in range(len(line)):
                counts = counts + [0]

            index = 0
            for pattern in patterns:
                while line.find(pattern, index) >= 0:
                    indexEnd = index+len(pattern)
                    for i in range(index, indexEnd):
                        counts[i] = counts[i] + 1
                    index = indexEnd
            print(counts)
