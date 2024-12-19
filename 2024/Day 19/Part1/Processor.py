

class Processor:

    answer = 0

    patterns = None

    def getAnswer(self):
        return self.answer

    def __init__(self, filename):

        lines = []
        with open(filename) as fp:
            for line in fp:
                lines = lines + [line.strip()]

        self.patterns = lines[0].split(',')
        self.patterns = [p.strip() for p in self.patterns]

        lines.pop(0)
        lines.pop(0)

        ############################################

        for line in lines:

            print("Building graph for {}".format(line))

            root = ([], line, [])   # Patterns used, Line left, Children
            self.buildGraph(root)

            print("Counting graph for {}".format(line))

            countOfPaths = self.countCompletePaths(root)
            if countOfPaths > 0:
                self.answer = self.answer + 1



    def buildGraph(self, currentNode):

        for pattern in self.patterns:
            line = currentNode[1]
            if line.startswith(pattern):
                childNode = (currentNode[0] + [pattern], line[pattern.__len__()::], [])
                currentNode[2].extend([childNode])

                self.buildGraph(childNode)

    def countCompletePaths(self, currentNode):

        children = currentNode[2]
        if not children:
            line = currentNode[1]
            return 1 if line.__len__() == 0 else 0

        count = 0
        for i in range(children.__len__()):
            child = children[i]
            count = self.countCompletePaths(child)
        return count