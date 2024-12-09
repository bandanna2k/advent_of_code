# Divisible Palindromes (Problem 655)

The numbers 545, 5995, and 15151 are the three smallest palindromes divisible by 109. 
There are nine palindromes less than 100,000 which are divisible by 109.

How many palindromes less than 10^32 are divisible by 10,000,019?

## Strategy

8-15 Chars (Brute force)
16-32 Chars


## Questions

Do I need a map mapping for multipliers for all numbers from 10 000 019 to ???

## Notes

less than (10^32)
99999999999999999999999999999999 = 32 chars

1 char              = 9 palindrome
2 char              = 9
3 char              = ?
4 char              = ?
...
32 chars            = 10^31 palindromes


Divisor
10_000_019 is 9 chars 

TODO List 9 char palindromes
TODO List 10 char palindromes
TODO List 11 char palindromes


10000000001


Length: 8, Test Count: 7380, Palindrome Count: 0,
Length: 10, Test Count: 66429, Palindrome Count: 0,
Length: 12, Test Count: 597870, Palindrome Count: 0,
Length: 14, Test Count: 5380839, Palindrome Count: 5,
Length: 16, Test Count: 48427560, Palindrome Count: 53,
Length: 18, Test Count: 435848049, Palindrome Count: 411,
Length: 20, Test Count: -372334846, Palindrome Count: 3677,





# Notes

1000001910000019/10000019 ✓
9100000110000019/10000019 X

9100000110000019/10000019 X


define trunc(x)   {auto s; s=scale; scale=0; x=x/1; scale=s; return x}
define mod(x,y)   {return x-(y*trunc(x/y))}
mod(9100000110000019,10000019) = 32661
9100000110032680/10000019 X + 32661

9100000109967358/10000019   - 32661
9100000109967358/10000019 ✓ (909998282 * 10000019)

9100000109967358 + (9 * 10000019) = 9100000199967529  (Ends the same)

(16 chars)
91000001010000019 + (000 * 10000019) = 9100000110000019
91000001010000019 + (100 * 10000019) = 9100001110001919
91000001010000019 + (200 * 10000019) = 9100002110003819


