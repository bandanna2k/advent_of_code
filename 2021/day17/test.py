import unittest

from day17.solutions import Projectiles


class Test(unittest.TestCase):

    def testProject1(self):
        projectiles = Projectiles(20, 30, -10, -5, 0, 0)
        self.assertTrue(projectiles.project(7, 2))

    def testProject2(self):
        projectiles = Projectiles(20, 30, -10, -5, 0, 0)
        self.assertTrue(projectiles.project(6, 3))

    def testProject3(self):
        projectiles = Projectiles(20, 30, -10, -5, 0, 0)
        self.assertTrue(projectiles.project(9, 0))

    def testProject4(self):
        projectiles = Projectiles(20, 30, -10, -5, 0, 0)
        self.assertFalse(projectiles.project(17, -4))

    def testProject5(self):
        projectiles = Projectiles(20, 30, -10, -5, 0, 0)
        self.assertTrue(projectiles.project(6, 9))

    def testProject6(self):
        projectiles = Projectiles(20, 30, -10, -5, 0, 0)
        self.assertFalse(projectiles.project(6, 26))

    def testFindMaxProjection(self):
        projectiles = Projectiles(20, 30, -10, -5, 0, 0)
        self.assertEqual("Max reach (6,9), Count 112", projectiles.findMaxProjection())



    def testInputFindMaxProjection(self):
        projectiles = Projectiles(277, 318, -92, -53, 0, 0)
        self.assertEqual("Max reach (24,91), Count 2709", projectiles.findMaxProjection())

    def testInputProject(self):
        projectiles = Projectiles(277, 318, -92, -53, 0, 0)
        self.assertTrue(projectiles.project(24, 91))

