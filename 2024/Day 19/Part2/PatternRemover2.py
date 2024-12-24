

class PatternRemover2:

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

        print(patterns)

        ############################################

        for line in lines:

            offset = 0
            q = [line[offset:]]

            while len(q) > 0:
                pop = q.pop()

                for pattern in patterns:
                    offsetLine = line[offset:]
                    # if pattern == offsetLine:



