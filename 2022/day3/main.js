
var dict = {}
for (let i = 0; i < 26; i++) {
    var letter = String.fromCharCode(i + 97);
    dict[letter] = i + 1;
    var upperCase = String.fromCharCode(i + 65);
    dict[upperCase] = i + 27;
}

let score = 0
require('fs').readFileSync('input.txt', 'utf-8')
             .split(/\r?\n/)
             .forEach(function(line) {

                 let countsRucksack1 = new Map()
                 let countsRucksack2 = new Map()

                 let length = line.length
//                 let halfLength = length / 2

                 for (let i = 0; i < length / 2; i++) {
                     var letter = line.at(i)
                     var count = countsRucksack1.get(letter)
                     if(count)
                     {
                         countsRucksack1.set(letter, count + 1)
                     }
                     else
                     {
                         countsRucksack1.set(letter, 1)
                     }
                 }
                 for (let i = length / 2; i < length; i++) {
                     var letter = line.at(i)
                     var count = countsRucksack2[letter]
                     if(count)
                     {
                         countsRucksack2.set(letter, count + 1)
                     }
                     else
                     {
                         countsRucksack2.set(letter, 1)
                     }
                 }
                 console.log('-- RUCKSACK1')
                 console.log(countsRucksack1)
                 console.log('-- RUCKSACK2')
                 console.log(countsRucksack2)

                 var intersect = [];
                 countsRucksack1.forEach((value, key) => {
                    if(countsRucksack2.get(key))
                    {
                        intersect.push(key);
                    }
                 });

                 console.log('-- INTERSECT')
                 console.log(intersect)

                 if(intersect.length === 1)
                 {
                     let sameLetter = intersect[0];
                     score += dict[sameLetter]
                 }
                 else
                 {
                     throw 'Intersect not correct'
                 }

                 console.log('---------------')
             });
console.log("Score: " + score)



