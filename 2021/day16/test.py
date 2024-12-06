import unittest

from day16.solutions import getSolution, getPacketVersionFromString, getPacketVersionFromStringUsingPointer, Reader
from fileIO import getStringFromFile


class Test(unittest.TestCase):

    def testPacketVersionFromString(self):
        self.assertEqual(6, getPacketVersionFromString("D2FE28"))

    def testPacketVersionFromStringUsingPointer(self):
        reader = Reader("D2FE28")
        self.assertEqual(6, reader.packetVersions[0])

    def testGetLiteralsFromLengthType0(self):
        reader = Reader("38006F45291200")
        self.assertEqual(10, reader.literalValues[0])
        self.assertEqual(20, reader.literalValues[1])

    def testGetLiteralsFromLengthType1(self):
        reader = Reader("EE00D40C823060")
        self.assertEqual(1, reader.literalValues[0])
        self.assertEqual(2, reader.literalValues[1])
        self.assertEqual(3, reader.literalValues[2])

    def testVersionSum1(self):
        reader = Reader("8A004A801A8002F478")
        self.assertEqual(16, reader.getVersionSum())

    def testVersionSum2(self):
        reader = Reader("620080001611562C8802118E34")
        self.assertEqual(12, reader.getVersionSum())

    def testVersionSum3(self):
        reader = Reader("C0015000016115A2E0802F182340")
        self.assertEqual(23, reader.getVersionSum())

    def testVersionSum4(self):
        reader = Reader("A0016C880162017C3686B18A3D4780")
        self.assertEqual(31, reader.getVersionSum())

    def testVersionInput(self):
        input = getStringFromFile("input.txt").strip()
        reader = Reader(input)
        self.assertEqual(871, reader.getVersionSum())

        self.assertGreater(reader.getAnswer(), 49099018863)
        self.assertEqual(68703010504, reader.getAnswer())
        self.assertLess(reader.getAnswer(), 121901217182)

# C200B40A82 finds the sum of 1 and 2, resulting in the value 3.
# 04005AC33890 finds the product of 6 and 9, resulting in the value 54.
# 880086C3E88112 finds the minimum of 7, 8, and 9, resulting in the value 7.
# CE00C43D881120 finds the maximum of 7, 8, and 9, resulting in the value 9.
# D8005AC2A8F0 produces 1, because 5 is less than 15.
# F600BC2D8F produces 0, because 5 is not greater than 15.
# 9C005AC2F8F0 produces 0, because 5 is not equal to 15.
# 9C0141080250320F1802104A08

    def testSum(self):
        reader = Reader("C200B40A82")
        self.assertEqual(3, reader.getSum())

    def testProduct(self):
        reader = Reader("04005AC33890")
        self.assertEqual(54, reader.getProduct())

    def testMinimum(self):
        reader = Reader("880086C3E88112")
        self.assertEqual(7, reader.getMinimum())

    def testMaximum(self):
        reader = Reader("CE00C43D881120")
        self.assertEqual(9, reader.getMaximum())

    def testGreaterThan(self):
        reader = Reader("F600BC2D8F")
        self.assertEqual(0, reader.getGreaterThan())

    def testLessThan(self):
        reader = Reader("D8005AC2A8F0")
        self.assertEqual(1, reader.getLessThan())

    def testEqualTo(self):
        reader = Reader("9C005AC2F8F0")
        self.assertEqual(0, reader.getEquals())

    def testEqualTo2(self):
        reader = Reader("9C0141080250320F1802104A08")
        self.assertEqual(1, reader.getEquals())

    @unittest.skip("Not implemented")
    def testPart1Test(self):
        self.assertEqual(getSolution("test.txt"), 0)

    @unittest.skip("Not implemented")
    def testPart1Input(self):
        self.assertEqual(getSolution("input.txt"), 0)

    @unittest.skip("Not implemented")
    def testPart2Test(self):
        self.assertEqual(getSolution("test.txt"), -1)

    @unittest.skip("Not implemented")
    def testPart2Input(self):
        self.assertEqual(getSolution("input.txt"), -1)
