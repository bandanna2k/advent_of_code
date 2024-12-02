class RowInput:

    rows = []
    height = 0

    def getRow(self, y):
        return self.rows[y]

    def __init__(self, filename):
        file1 = open(filename, 'r')

        for line in file1:
            values = line.split(' ')
            values = [v for v in values if v != '']

            row = []
            for value in values:
                row = row + [int(value)]
            self.rows = self.rows + [row]

        self.height = self.rows.__len__()

        # Closing files
        file1.close()
