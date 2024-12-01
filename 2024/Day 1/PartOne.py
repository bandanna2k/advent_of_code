class PartOne:

    file = None
    lefties = []
    righties = []

    def __init__(self, filename):
        self.file = filename

    def read(self):
        file1 = open(self.file, 'r')

        for line in file1:
            values = line.split(' ')
            values = [v for v in values if v != '']

            self.lefties = self.lefties + [int(values[0])]
            self.righties = self.righties + [int(values[1])]

        self.lefties.sort()
        self.righties.sort()

        # Closing files
        file1.close()

    def differences(self):

        localLefties = self.lefties
        localRighties = self.righties

        sumOfDifference = 0
        while localLefties.__len__() != 0:

            left = localLefties.pop(0)
            right = localRighties.pop(0)

            difference = abs(left - right)
            sumOfDifference = sumOfDifference + difference

        print("Sum of diffs: {}".format(sumOfDifference))

        return sumOfDifference