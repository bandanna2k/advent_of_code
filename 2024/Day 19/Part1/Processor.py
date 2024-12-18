

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
            if self.search(root):
                self.answer = self.answer + 1


    def search(self, currentNode):

        line = currentNode[1]
        children = currentNode[2]
        if not line and not children:
            print(currentNode)
            return True

        result = False
        for pattern in self.patterns:
            if line.startswith(pattern):
                childNode = (currentNode[0] + [pattern], line[pattern.__len__()::], [])
                currentNode[2].extend([childNode])

                result = self.search(childNode)
            if result:
                break

        return result



    def buildGraph(self, currentNode):

        line = currentNode[1]
        if not line:
            return

        for pattern in self.patterns:
            if line.startswith(pattern):
                childNode = (currentNode[0] + [pattern], line[pattern.__len__()::], [])
                currentNode[2].extend([childNode])

                self.buildGraph(childNode)

        print(currentNode[0])

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