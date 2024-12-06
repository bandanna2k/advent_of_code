def calculate(mashedUpDigits, mashedUpInputs):

    print("Mashed Up Digits {}, Inputs {}".format(mashedUpDigits, mashedUpInputs))

    # Configure 0 -> 9
    digits = dict()
    digits["abcdeg"] = "0"
    digits["ab"] = "1"
    digits["acdfg"] = "2"

    digits["abcdf"] = "3"
    digits["abef"] = "4"
    digits["bcdef"] = "5"

    digits["bcdefg"] = "6"
    digits["abd"] = "7"
    digits["abcdefg"] = "8"

    digits["abcdef"] = "9"

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


    # Calc D, get len 2 and 3
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
    theMap[remainingDigit] = "d"
    #print("a <- {}".format(remainingDigit))


    # Get config with 4 letters
    for config in configs:
        if len(config) == 4:
            config4 = config

    assert config4 != None


    for letter in letters:
        if distribution[letter] == 4:
            theMap[letter] = "g"
            #print("e <- {}".format(letter))
        if distribution[letter] == 6:
            theMap[letter] = "e"
            #print("b <- {}".format(letter))
        if distribution[letter] == 9:
            theMap[letter] = "b"
            #print("f <- {}".format(letter))
        if distribution[letter] == 7:
            if letter in config4:
                theMap[letter] = "f"
                #print("d <- {}".format(letter))
            else:
                theMap[letter] = "c"
                #print("g <- {}".format(letter))

        if distribution[letter] == 8:
            if letter != remainingDigit:
                theMap[letter] = "a"
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
