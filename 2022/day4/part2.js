

let count = 0
require('fs').readFileSync('input.txt', 'utf-8')
             .split(/\r?\n/)
             .forEach(function(line) {
                 let assignments = line.split(",");
                 let elf1 = assignments[0].split("-")
                 let elf2 = assignments[1].split("-")
                 let elf1start = Number(elf1[0])
                 let elf1end = Number(elf1[1])
                 let elf2start = Number(elf2[0])
                 let elf2end = Number(elf2[1])

                 if(elf1start >= elf2start && elf1start <= elf2end)
                 {
                     count++;
                 }
                 else if(elf2start >= elf1start && elf2start <= elf1end)
                 {
                     count++;
                 }
             });
console.log("Count: " + count)



