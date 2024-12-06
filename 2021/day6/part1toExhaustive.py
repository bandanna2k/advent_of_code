import io
import os

end = 80
end = 256

prevFile = "test.txt"
for i in range(end):
    day = i + 1

    with open(prevFile) as f:

        fileForOutput = "day" + str(day) + ".txt"
        with open(fileForOutput, "w") as output:

            print("Writing {}".format(fileForOutput))

            reading = True
            countToAdd = 0
            while reading:

                value = int(f.read(1))
                if value > 0:
                    output.write(str(value - 1))
                else:
                    countToAdd = countToAdd + 1
                    output.write("6")

                value = f.read(1) # EOF or comma
                if value != ",":
                    reading = False
                else:
                    output.write(",")

            for j in range(countToAdd):
                output.write(",")
                output.write("8")

        os.remove(prevFile)
        prevFile = fileForOutput

countOfFish = 0
with open(prevFile) as f:

    reading = True
    while reading:

        value = int(f.read(1))
        countOfFish = countOfFish + 1

        value = f.read(1) # EOF or comma
        if value != ",":
            reading = False

print("Fish {}".format(countOfFish))
