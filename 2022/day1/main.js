var dataModule = require('./data.js');

let max = -1;
let allInputs = dataModule.data;
let counts = [];
let count = 0;

// console.log(allInputs.length)

max = -1;
allInputs = dataModule.data;
count = 0;

// console.log(allInputs.length)
for (let i = 0; i < allInputs.length; i++) {
    let input = allInputs[i];
    if (input === '') {
        max = Math.max(max, count);
        count = 0;
    } else {
        count += Number(input);
    }
}
console.log(max);


console.log('------------');

max = -1;
allInputs = dataModule.data;
count = 0;

for (let i = 0; i < allInputs.length; i++) {
    let input = allInputs[i];
    if (input === '') {
        max = Math.max(max, count);
        counts.push(count);
        count = 0;
    } else {
        count += Number(input);
    }
}
counts.push(count);
counts.sort((a, b) => a - b).reverse();
// console.log(counts)
console.log(counts[0] + counts[1] + counts[2]);


console.log('------------');

count = 0;
require('fs').readFileSync('input', 'utf-8')
             .split(/\r?\n/)
             .forEach(function(line) {
                 count++;
             });
console.log(count);