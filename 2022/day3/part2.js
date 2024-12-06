
var dict = {}
for (let i = 0; i < 26; i++) {
    var letter = String.fromCharCode(i + 97);
    dict[letter] = i + 1;
    var upperCase = String.fromCharCode(i + 65);
    dict[upperCase] = i + 27;
}

let score = 0
let strings = require('fs').readFileSync('input.txt', 'utf-8')
                           .split(/\r?\n/);
for (let i = 0; i < strings.length;  i += 3)
{
    let line1 = strings[i];
    let line2 = strings[i+1];
    let line3 = strings[i+2];
    let set1 = new Set()
    // Adding
    for (let j = 0; j < line1.length ; j++)
    {
        var letter = line1.at(j)
        set1.add(letter);
    }
//    console.log(set1)

    let set2 = new Set()
    for (let j = 0; j < line2.length ; j++)
    {
        var letter = line2.at(j)
        if(set1.has(letter))
        {
            set2.add(letter)
        }
    }
//    console.log(set2)

    let set3 = new Set()
    for (let j = 0; j < line3.length ; j++)
    {
        var letter = line3.at(j)
        if(set2.has(letter))
        {
            set3.add(letter)
        }
    }
//    console.log(set3)

    console.log('-- INTERSECT')
    console.log(set3)

    // let mySet = new Set()
    // console.log(mySet.length)
    // console.log(mySet.size)

    if(set3.size === 1)
    {
        let sameLetter = set3.values().next().value
        score += dict[sameLetter]
    }
    else
    {
        throw 'Intersect not correct'
    }
}
console.log(score)
