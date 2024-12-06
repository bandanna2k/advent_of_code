import sys

from fileIO import getAllLines, myPrint

def getSolution(filename):

    lines = getAllLines(filename)

    myPrint(lines)

    return "X"

def getPacketVersion(int8bit):

    # bit shift 5 and get value of the int
    return int8bit >> 5



def getPacketVersionFromString(input):

    hexValue = input[0:2]
    hexValueAsString = "0x{}".format(hexValue)
    return getPacketVersion(int(hexValueAsString, base=16))




def getPacketVersionFromStringUsingPointer(input):

    reader = Reader(input)
    return reader.packetVersion



class Reader:

    packetVersions = None
    literalValues = None
    results = None

    # theInput = None
    # theAllocation = None
    #
    def __init__(self, input):

        inputAsBinary = ""
        for i in range(0, len(input), 2):
            hexValue = input[i:i+2]

            binValue = format(int(hexValue, base=16), '08b')

            inputAsBinary = inputAsBinary + binValue
        print(inputAsBinary)

        self.packetVersions = []
        self.literalValues = []
        self.results = Results()

        self.processBinary(inputAsBinary, sys.maxsize, self.results)

        print(self.results)

    def getSum(self):
        return self.results.sum

    def getProduct(self):
        return self.results.product

    def getMinimum(self):
        return self.results.minimum

    def getMaximum(self):
        return self.results.maximum

    def getGreaterThan(self):
        return self.results.greater

    def getLessThan(self):
        return self.results.less

    def getEquals(self):
        return self.results.equals

    def processBinary(self, binaryInput, countOfSubPacketsExpected, results):

        inputLen = len(binaryInput)

        binaryAllocation = ""

        pointer = 0
        countOfSubPackets = 0
        while pointer + 7 < inputLen and countOfSubPackets < countOfSubPacketsExpected:

            # if pointer + 6 > inputLen:
            #     break

            # Get packet version
            packetVersion = self.getValueFromBinary(binaryInput[pointer:pointer + 3])
            pointer = pointer + 3
            binaryAllocation = getNewAllocation(binaryAllocation, 3)

            print("Packet version:{}".format(packetVersion))
            self.packetVersions.append(packetVersion)
            print("Packet versions:" + str(self.packetVersions))

            packetTypeId = self.getValueFromBinary(binaryInput[pointer:pointer + 3])
            pointer = pointer + 3
            binaryAllocation = getNewAllocation(binaryAllocation, 3)

            print("Packet type ID:{}".format(packetTypeId))

            countOfSubPackets = countOfSubPackets + 1

            # Number
            if packetTypeId == 4:
                processing = True

                binaryValues = ""
                while processing:
                    partial = binaryInput[pointer:pointer + 5]
                    pointer = pointer + 5
                    binaryAllocation = getNewAllocation(binaryAllocation, 5)

                    if partial[0] == '0':
                        processing = False

                    binaryValues = binaryValues + partial[1:5]

                #assert len(binaryValues) % 4 == 0, "Len not divisible by 4 " + str(len(binaryValues))

                packetType4Value = int(binaryValues, base=2)
                print("Literal value: {}".format(packetType4Value))
                self.literalValues.append(packetType4Value)
                print("Literal values:" + str(self.literalValues))

                results.literals.append(packetType4Value)
            else:
                lengthTypeId = binaryInput[pointer]
                pointer = pointer + 1
                binaryAllocation = getNewAllocation(binaryAllocation, 1)

                if lengthTypeId == '0':

                    print("Length type ID:{} (by length)".format(lengthTypeId))

                    partial = binaryInput[pointer:pointer+15]
                    pointer = pointer + 15
                    binaryAllocation = getNewAllocation(binaryAllocation, 15)
                    lengthSubPacket = int(partial, base=2)

                    print("Length sub packet:" + str(lengthSubPacket))

                    partial = binaryInput[pointer:pointer+lengthSubPacket]
                    pointer = pointer + lengthSubPacket
                    binaryAllocation = getNewAllocation(binaryAllocation, lengthSubPacket)

                    print("--- Sub packet ---")
                    newResults = Results()
                    self.processBinary(partial, sys.maxsize, newResults)
                else:
                    print("Length type ID:{} (by count of packets)".format(lengthTypeId))

                    partial = binaryInput[pointer:pointer+11]
                    pointer = pointer + 11
                    binaryAllocation = getNewAllocation(binaryAllocation, 11)
                    countOfSubPackets = int(partial, base=2)

                    print("Count of sub packets:" + str(countOfSubPackets))

                    partial = binaryInput[pointer:inputLen]
                    # Don't increment pointer

                    print("--- Sub packet (2) ---")
                    newResults = Results()
                    bitsRead = self.processBinary(partial, countOfSubPackets, newResults)
                    pointer = pointer + bitsRead
                    binaryAllocation = getNewAllocation(binaryAllocation, bitsRead)

                if packetTypeId == 0:
                    results.sum = 0
                    for literal in newResults.literals:
                        results.sum = results.sum + literal
                    results.literals.append(results.sum)
                if packetTypeId == 1:
                    results.product = 1
                    for literal in newResults.literals:
                        results.product = results.product * literal
                    results.literals.append(results.product)
                if packetTypeId == 2:
                    results.minimum = sys.maxsize
                    for literal in newResults.literals:
                        results.minimum = min(results.minimum, literal)
                    results.literals.append(results.minimum)
                if packetTypeId == 3:
                    results.maximum = 1 - sys.maxsize
                    for literal in newResults.literals:
                        results.maximum = max(results.maximum, literal)
                    results.literals.append(results.maximum)
                if packetTypeId == 5:
                    results.greater = -1
                    assert len(newResults.literals) == 2, "GT length no good"
                    if newResults.literals[0] > newResults.literals[1]:
                        results.greater = 1
                    else:
                        results.greater = 0
                    results.literals.append(results.greater)
                if packetTypeId == 6:
                    results.less = -1
                    assert len(newResults.literals) == 2, "LT length no good"
                    if newResults.literals[0] < newResults.literals[1]:
                        results.less = 1
                    else:
                        results.less = 0
                    results.literals.append(results.less)
                if packetTypeId == 7:
                    results.equals = -1
                    assert len(newResults.literals) == 2, "EQ length no good"
                    if newResults.literals[0] == newResults.literals[1]:
                        results.equals = 1
                    else:
                        results.equals = 0
                    results.literals.append(results.equals)

        print("Input:{}".format(binaryInput))
        print("Alloc:{}".format(binaryAllocation))

        return pointer

    def getValueFromBinary(self, binary):
        return int(binary, base=2)

    def getVersionSum(self):
        result = 0
        for version in self.packetVersions:
            result = result + version
        return result

    def getAnswer(self):
        answer = None
        if self.results.less is not None:
            answer = self.results.less
        if self.results.greater is not None:
            answer = self.results.greater
        if self.results.equals is not None:
            answer = self.results.equals
        if self.results.sum is not None:
            answer = self.results.sum
        if self.results.product is not None:
            answer = self.results.product
        if self.results.minimum is not None:
            answer = self.results.minimum
        if self.results.maximum is not None:
            answer = self.results.maximum
        return answer

def getNewAllocation(allocation, count):
    length = len(allocation)
    result = allocation
    if length == 0:
        for i in range(count):
            result = result + "A"
    else:
        char = chr(ord(allocation[length - 1]) + 1)
        for i in range(count):
            result = result + char
    return result

class Results:

    def __init__(self):
        self.literals = []
        self.sum = None
        self.product = None
        self.minimum = None
        self.maximum = None
        self.greater = None
        self.less = None
        self.equals = None

    def __str__(self):
        return "Σ {}, X {}, ↑ {}, ↓ {}, = {}, > {}, < {}, Lit {}".format(self.sum, self.product, self.maximum, self.minimum, self.equals, self.greater, self.less, self.literals)

    def __repr__(self):
        return self.__str__()