# Divisible Palindromes (Problem 655)

The numbers 545, 5995, and 15151 are the three smallest palindromes divisible by 109. 
There are nine palindromes less than 100,000 which are divisible by 109.

How many palindromes less than 10^32 are divisible by 10,000,019?

# Abstract extended

10^32 = 100 00000 00000 00000 00000 00000 00000
less than means
99 99999 99999 99999 99999 99999 99999 = 32 chars

## Strategy

| Chars | Strategy             | Palindrome Count | Strategies | Time Taken             |
|-------|----------------------|------------------|------------|------------------------|
| 8     | Brute Force          | 0                |            | 0.02 secs              |
| 9     | "                    | 0                |            | 0.2 secs               |
| 10    | "                    | 0                |            | 0.15 secs              |
| 11    | "                    | 0                |            | 0.9 secs               |
| 12    | "                    | 0                |            | 1.2 secs               |
| 13    | "                    | 6 (need 2nd)     |            | 4.2 secs               |
| 14    | "                    | 0                |            | 13 secs                |
| 15    | "                    | 48 (need 2nd)    |            | 43 secs                |
| 16    | Optimised (x2 & x4)  | 8                | 4          | 1 sec / .2             |
| 16    | Palindrome Extractor | 8                | 4          | 32 secs                |
| 17    | Brute Force          | 482 (need 2nd)   |            | 6 mins                 |
| 18    | Optimised (x2 & x4)  | 101              | 3          | 48 secs / 1.5 s        |
| 19    | "                    |                  |            |                        |
| 20    | "                    | 10 711 706       |            | 52 secs / 7 mins / 53s |
| 21    | "                    |                  |            |                        |
| 22    | "                    |                  |            |                        |
| 23    | "                    |                  |            |                        |
| 24    | ??????               |                  |            |                        |
| 25    | "                    |                  |            |                        |
| 26    | "                    |                  |            |                        |
| 27    | "                    |                  |            |                        |
| 28    | "                    |                  |            |                        |
| 29    | "                    |                  |            |                        |
| 30    | "                    |                  |            |                        |
| 31    | "                    |                  |            |                        |
| 32    | "                    |                  |            |                        |
 
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

10000000009963919
19 -> 01 = *78

10000000009963919  A:10 B:0 C:0 D:19 
12345678987654321
         10000019

10 != 19 but 0 == 0
multiply by greater

*78
10000000009963919 + (78 * 10000019) = 10000000789965401


Next
10000000789965401 A:10 B:0 C:8 D:01   
12345678987654321
         10000019 

*2                                   12345678987654321
10000000789965401 + (2 * 10000019) = 10000000809965439  A:10 B:0 C:0 D:39 

*2 *100                                    12345678987654321
10000000789965401 + (2 * 100 * 10000019) = 10000002789969201  A:10 B:2 C:8 D:01 

*6 *100                                    12345678987654321
10000002789969201 + (6 * 100 * 10000019) = 10000008789980601  A:10 B:8 C:8 D:01     Check Palindrome

*100                                       12345678987654321
10000008789980601 + (100 * 10000019)     = 10000009789982501  A:10 B:9 C:8 D:01     
*10 *100                                   12345678987654321
10000008789980601 + (10 * 100 * 10000019)= 10000018789999601  A:10 B:8 C:8 D:01     Check Palindrome

*10 *100                                   12345678987654321
10000018789999601 + (10 * 100 * 10000019)= 10000028790018601  A:10 B:8 C:9 D:01     

*9 *100                                    12345678987654321 (Shouldn't have done this multiply)
10000028790018601 + (9 * 100 * 10000019) = 10000037790035701  A:10 B:7 C:9 D:01 (Shouldn't have done this multiply)
*1 *100                                    12345678987654321
10000028790018601 + (1 * 100 * 10000019) = 10000029790020501  A:10 B:9 C:9 D:01

*2 *100                                    12345678987654321
10000037790035701 + (2 * 100 * 10000019) = 10000039790039501  A:10 B:9 C:9 D:01     Check palindrome     


1000 0006 8999 7701

86301271172103680   8630 1271 1721 0368 0
90651833338156090   9065 1833 3381 5609 0
103180238832081301  1031 8023 88 3208 1301
116991379973199611  116991379973199611
120840781187048021  120840781187048021



A        B       C        D 
               10 00 00 91
10 00 01 47 97 92 45 06 01   (18 chars)

A        B        C        D
                  10 00 00 91
10 00 00 01 18 57 56 15 30 01


100000000100054980790001 + (10000019 * 10000) + (10000019 * 80000000)

                    1000 0019
1000 0000 0300 1553 6098 0001
1000 0000 0900 1565 0098 0001

19 1584 1086 68 0148 5191



Palindrome found: AD:Record[number=10000002, modulus=1202099], BC:Record[number=49381075, modulus=8797920], Mod:10000019
10000002 49381075 75018394 20000001
(10000002493810757501839420000001) / 10000019

Palindrome found: AD:Record[number=10000000, modulus=1202096], BC:Record[number=53779944, modulus=8797923], Mod:10000019
(10000000537799444499773500000001) / 10000019




/usr/lib/jvm/java-1.21.0-openjdk-amd64/bin/java -ea -Didea.test.cyclic.buffer.size=4194304 -javaagent:/snap/intellij-idea-community/539/lib/idea_rt.jar=43137:/snap/intellij-idea-community/539/bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /snap/intellij-idea-community/539/lib/idea_rt.jar:/snap/intellij-idea-community/539/plugins/junit/lib/junit5-rt.jar:/snap/intellij-idea-community/539/plugins/junit/lib/junit-rt.jar:/home/northd/Code/advent_of_code/out/test/java:/home/northd/Code/advent_of_code/out/production/java:/home/northd/.m2/repository/junit/junit/4.13.2/junit-4.13.2.jar:/home/northd/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar:/home/northd/.m2/repository/org/assertj/assertj-core/3.26.3/assertj-core-3.26.3.jar:/home/northd/.m2/repository/net/bytebuddy/byte-buddy/1.14.18/byte-buddy-1.14.18.jar:/home/northd/Code/advent_of_code/out/test/project-euler:/home/northd/Code/advent_of_code/out/production/project-euler com.intellij.rt.junit.JUnitStarter -ideVersion5 -junit4 dnt.maths.problems600.problem655.byQuarters.NumberModuliTest,testModDeltas
1234567012345670123456701234567 modN = 5487282
1234568012345670123456701234567 modN = 8628301
Pos 25, mod delta = 3141019
1234567012345670123456701234567 modN = 5487282
1234567012345680123456701234567 modN = 5523382
Pos 17mod delta = 36100
1234567012345670123456701234567 modN = 5487282
1234567012345670123456801234567 modN = 5487092
Pos 8mod delta = -190
1234567012345670123456701234567 modN = 5487282
1234567012345670123456701234568 modN = 5487283
Pos 1mod delta = 1

Process finished with exit code 0



10000002000000000000000010000002 % 10000019 = 4547221?

1000000000000000000000000