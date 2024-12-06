import unittest

from grid import Grid

class Test(unittest.TestCase):

    def testCreation(self):
        myGrid = Grid()
        myGrid.createFromDimensions(10, 11)
        self.assertEqual(myGrid.getSizeX(), 10)
        self.assertEqual(myGrid.getSizeY(), 11)
        print(myGrid)
        myGrid.setValue(4, 0, "X")
        self.assertEqual(myGrid.getValue(4, 0), "X")
        print(myGrid)
        myGrid.addBorder("X")
        self.assertEqual(myGrid.getValue(5, 1), "X")
        print(myGrid)


    def testCopy(self):
        grid1 = Grid()
        grid1.createFromDimensions(10, 11)
        grid1.setValue(4, 0, "X")
        self.assertEqual(grid1.getValue(4, 0), "X")

        grid2 = grid1.getCopy()
        self.assertEqual(grid2.getValue(4, 0), "X")

        grid1.setValue(4, 2, "Y")
        self.assertEqual(grid1.getValue(4, 2), "Y")
        self.assertEqual(grid2.getValue(4, 2), 0)


