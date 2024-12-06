def calculate(mashedUpDigits, mashedUpInputs):

    print("Mashed Up Digits {}, Inputs {}".format(mashedUpDigits, mashedUpInputs))

    # Configure 0 -> 9
    digits = dict()
    digits["abcefg"] = "0"
    digits["cf"] = "1"
    digits["acdeg"] = "2"

    digits["acdfg"] = "3"
    digits["bcdf"] = "4"
    digits["abdfg"] = "5"

    digits["abdefg"] = "6"
    digits["acf"] = "7"
    digits["abcdefg"] = "8"

    digits["abcdfg"] = "9"


    # Create 0 distribution
    letters = ["a", "b", "c", "d", "e", "f", "g"]
    distribution = dict()
    for letter in letters:
        distribution[letter] = 0;


    # Read config
    configs = mashedUpDigits.split(" ")
    print(configs)

    arraySize = len(configs)
    assert arraySize == 10, "Should be 10, was {}".format(arraySize)


    # Get letter distribution
    for i in range(len(configs)):
        config = configs[i]
        for j in range(len(config)):
            letter = config[j]
            distribution[letter] = distribution[letter] + 1

    print(distribution)


    # Calc A, get len 2 and 3
    for config in configs:
        if len(config) == 3:
            digit7 = config
        if len(config) == 2:
            digit1 = config

    assert digit1 != None
    assert digit7 != None

    theMap = dict()

    for letterInDigit1 in digit1:
        digit7 = digit7.replace(letterInDigit1, "")

    remainingDigit = digit7[0]
    theMap[remainingDigit] = "a"
    #print("a <- {}".format(remainingDigit))


    # Get config with 4 letters
    for config in configs:
        if len(config) == 4:
            config4 = config

    assert config4 != None


    for letter in letters:
        if distribution[letter] == 4:
            theMap[letter] = "e"
            #print("e <- {}".format(letter))
        if distribution[letter] == 6:
            theMap[letter] = "b"
            #print("b <- {}".format(letter))
        if distribution[letter] == 9:
            theMap[letter] = "f"
            #print("f <- {}".format(letter))
        if distribution[letter] == 7:
            if letter in config4:
                theMap[letter] = "d"
                #print("d <- {}".format(letter))
            else:
                theMap[letter] = "g"
                #print("g <- {}".format(letter))

        if distribution[letter] == 8:
            if letter != remainingDigit:
                theMap[letter] = "c"
                #print("c <- {}".format(letter))


    print(theMap)


    # Read settings
    unknownStrings = mashedUpInputs.split(" ")
    print("Values {}".format(unknownStrings))

    mappedInputs = []
    for digitValue in unknownStrings:
        mappedValue = ""
        for letter in digitValue:
            mapping = theMap[letter]
            mappedValue = mappedValue + mapping

        #print("{} {}".format(mappedValue, "".join(sorted(mappedValue))))
        mappedInputs.append("".join(sorted(mappedValue)))
    print("Mapped {}".format(mappedInputs))

    # Output value
    print("Output")
    output = ""
    for mappedInput in mappedInputs:
        for digitKey in digits.keys():
            digitValue = digits[digitKey]
            if digitKey == mappedInput:
                output = output + digitValue
    print(output)

    assert len(output) == len(mappedInputs), "A {} v B {}".format(len(output), len(mappedInputs))

    return output
