import unittest
from queue import PriorityQueue

import day15.solution7
from day15.solution import PathItem
from day15.solution6 import getSolution6, getSolution6Part2


class Test(unittest.TestCase):

    def testPathEquals(self):
        list = []
        list.append(PathItem(1, 2, 3, 4))
        list.append(PathItem(1, 2, 3, 5))
        self.assertTrue(PathItem(1, 2, 3, 6) in list)

    def testSolutionTest(self):
        self.assertEqual(getSolution6("test.txt"), 40)

    def testSolutionTestPart2(self):
        self.assertEqual(getSolution6Part2("test.txt"), 315)

    def testSolutionTest2(self):
        self.assertEqual(getSolution6("test2.txt"), 10)

    def testSolutionTest3(self):
        self.assertEqual(getSolution6("test3.txt"), 10)

    def testSolutionTest4(self):
        self.assertEqual(getSolution6("test4.txt"), 18)

    def testSolutionInput(self):
        self.assertEqual(getSolution6("input.txt"), 739)

    @unittest.skip("takes quite a while")
    def testSolutionInputPart2(self):
        self.assertEqual(getSolution6Part2("input.txt"), 3040)


    def testPriorityQueue(self):
        queue = PriorityQueue()
        queue.put(day15.solution6.PathItem(1, 1, 1, True))
        queue.put(day15.solution6.PathItem(2, 2, 2, False))
        queue.put(day15.solution6.PathItem(3, 3, 3, True))
        queue.put(day15.solution6.PathItem(4, 4, 4, False))
        queue.put(day15.solution6.PathItem(5, 5, 5, True))
        queue.put(day15.solution6.PathItem(5, 5, 5, True))

        self.assertEqual(queue.get().weight, 2)
        self.assertEqual(queue.get().weight, 4)
        self.assertEqual(queue.get().weight, 1)
        self.assertEqual(queue.get().weight, 3)
        self.assertEqual(queue.get().weight, 5)
        self.assertEqual(queue.get().weight, 5)
