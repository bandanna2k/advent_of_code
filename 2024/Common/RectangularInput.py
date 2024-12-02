class RectangularInput:

    rows = []
    cols = []

    def get(self, x, y):
        return self.rows[y][x]

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
        self.width = (self.rows[0]).__len__()

        # print(width)
        # print(height)

        for y in range(self.height):
            col = []
            self.cols = self.cols + [col]

        for y in range(self.height):
            for x in range(self.width):
                value = self.rows[y][x]
                self.cols[x] = self.cols[x] + [value]

        # print(self.rows)
        # print(self.cols)

        # Closing files
        file1.close()
