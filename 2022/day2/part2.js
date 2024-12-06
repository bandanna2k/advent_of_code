let loss = 0
let draw = 3
let win = 6

let score = 0

let ME_ROCK = 1;
let ME_PAPER = 2;
let ME_SCISSORS = 3;

let LOSE = 'X';
let DRAW = 'Y';
let WIN = 'Z';

let THEM_ROCK = 'A';
let THEM_PAPER = 'B';
let THEM_SCISSORS = 'C';

require('fs').readFileSync('input.txt', 'utf-8')
             .split(/\r?\n/)
             .forEach(function(line) {
                 console.log(line);
                 let plays = line.split(" ");
                 let them = plays[0];
                 let outcome = plays[1];

                 if(them === THEM_ROCK)
                 {
                     if(outcome === WIN)
                     {
                         score += win
                         score += ME_PAPER
                     }
                     if(outcome === LOSE)
                     {
                         score += loss
                         score += ME_SCISSORS
                     }
                     if(outcome === DRAW)
                     {
                         score += draw
                         score += ME_ROCK
                     }
                 }
                 if(them === THEM_PAPER)
                 {
                     if(outcome === WIN)
                     {
                         score += win
                         score += ME_SCISSORS
                     }
                     if(outcome === LOSE)
                     {
                         score += loss
                         score += ME_ROCK
                     }
                     if(outcome === DRAW)
                     {
                         score += draw
                         score += ME_PAPER
                     }
                 }
                 if(them === THEM_SCISSORS)
                 {
                     if(outcome === WIN)
                     {
                         score += win
                         score += ME_ROCK
                     }
                     if(outcome === LOSE)
                     {
                         score += loss
                         score += ME_PAPER
                     }
                     if(outcome === DRAW)
                     {
                         score += draw
                         score += ME_SCISSORS
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