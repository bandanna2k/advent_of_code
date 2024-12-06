let count = 0;

let mapOfStacks = new Map();

let file = 'input.txt';
require('fs').readFileSync(file, 'utf-8')
             .split(/\r?\n/)
             .filter(line => line.includes('['))
             .reverse()
             .forEach(function(line) {
                 let pos = 1;
                 for (let i = 1; i <= 10; i++) {
                     let letter = line[pos];
                     let stack = mapOfStacks[i];
                     pos += 4;

                     if (!letter) {
                         continue;
                     }
                     if (!letter.trim()) {
                         continue;
                     }

                     if (stack) {
                         stack.push(letter);
                     } else {
                         stack = [letter];
                         mapOfStacks[i] = stack;
                     }
                 }
             });

console.log(mapOfStacks);


require('fs').readFileSync(file, 'utf-8')
             .split(/\r?\n/)
             .filter(line => line.includes('move'))
             .forEach(function(line) {
                 let fields = line.split(' ');
                 let count = Number(fields[1]);
                 let src = fields[3];
                 let dest = fields[5];

                 let srcStack = mapOfStacks[src];
                 let destStack = mapOfStacks[dest]
                 for (let i = 0; i < count; i++) {
                     let value = srcStack.pop()
                     destStack.push(value);
                 }
             });
console.log(mapOfStacks)

let result = ''
for (let i = 0; i < 10; i++)
{
    let stack = mapOfStacks[i];
    if(stack)
    {
        result += stack.pop()
    }
}

console.log(result)



