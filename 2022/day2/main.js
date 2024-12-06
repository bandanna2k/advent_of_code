score = 0;

let loss = 0
let draw = 3
let win = 6

require('fs').readFileSync('input.txt', 'utf-8')
             .split(/\r?\n/)
             .forEach(function(line) {
                 console.log(line);
                 let plays = line.split(" ");
                 let them = plays[0];
                 let me = plays[1];

                 let ME_ROCK = 'X';
                 let ME_PAPER = 'Y';
                 let ME_SCISSORS = 'Z';
                 let THEM_ROCK = 'A';
                 let THEM_PAPER = 'B';
                 let THEM_SCISSORS = 'C';
                 if(me === ME_ROCK)
                 {
                     score += 1
                     if(them === THEM_ROCK)
                     {
                         score += draw
                     }
                     if(them === THEM_PAPER)
                     {
                         score += loss
                     }
                     if(them === THEM_SCISSORS)
                     {
                         score += win
                     }
                 }

                 if(me === ME_PAPER)
                 {
                     score += 2
                     if(them === THEM_ROCK)
                     {
                         score += win
                     }
                     if(them === THEM_PAPER)
                     {
                         score += draw
                     }
                     if(them === THEM_SCISSORS)
                     {
                         score += loss
                     }
                 }

                 if(me === ME_SCISSORS)
                 {
                     score += 3
                     if(them === THEM_ROCK)
                     {
                         score += loss
                     }
                     if(them === THEM_PAPER)
                     {
                         score += win
                     }
                     if(them === THEM_SCISSORS)
                     {
                         score += draw
                     }
                 }
             });
console.log(score);

/*

A Rock
B Paper
C Scissors

X Rock
Y Paper
Z Scissors

 */