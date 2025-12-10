--- Day 8: Playground ---
Equipped with a new understanding of teleporter maintenance, you confidently step onto the repaired teleporter pad.

You rematerialize on an unfamiliar teleporter pad and find yourself in a vast underground space which contains a giant playground!

Across the playground, a group of Elves are working on setting up an ambitious Christmas decoration project. 
Through careful rigging, they have suspended a large number of small electrical junction boxes.

Their plan is to connect the junction boxes with long strings of lights. Most of the junction boxes don't provide electricity; 
however, when two junction boxes are connected by a string of lights, electricity can pass between those two junction boxes.

The Elves are trying to figure out which junction boxes to connect so that electricity can reach every junction box. 
They even have a list of all of the junction boxes' positions in 3D space (your puzzle input).

For example:
```
162,817,812
57,618,57
906,360,560
592,479,940
352,342,300
466,668,158
542,29,236
431,825,988
739,650,466
52,470,668
216,146,977
819,987,18
117,168,530
805,96,715
346,949,466
970,615,88
941,993,340
862,61,35
984,92,344
425,690,689
```
This list describes the position of 20 junction boxes, one per line. Each position is given as X,Y,Z coordinates. 
So, the first junction box in the list is at X=162, Y=817, Z=812.

To save on string lights, the Elves would like to focus on connecting pairs of junction boxes that are as close together as 
possible according to straight-line distance. In this example, the two junction boxes which are closest together are 162,817,812 and 425,690,689.

By connecting these two junction boxes together, because electricity can flow between them, they become part of the same circuit. 
After connecting them, there is a single circuit which contains two junction boxes, 
and the remaining 18 junction boxes remain in their own individual circuits.

Now, the two junction boxes which are closest together but aren't already directly connected are 162,817,812 and 431,825,988. 
After connecting them, since 162,817,812 is already connected to another junction box, there is now a single circuit which 
contains three junction boxes and an additional 17 circuits which contain one junction box each.

The next two junction boxes to connect are 906,360,560 and 805,96,715. After connecting them, there is a circuit containing 3 junction boxes, 
a circuit containing 2 junction boxes, and 15 circuits which contain one junction box each.

The next two junction boxes are 431,825,988 and 425,690,689. Because these two junction boxes were already in the same circuit, nothing happens!

This process continues for a while, and the Elves are concerned that they don't have enough extension cables for all these circuits. 
They would like to know how big the circuits will be.

After making the ten shortest connections, there are 11 circuits: one circuit which contains 5 junction boxes, one circuit which contains 4 junction boxes, 
two circuits which contain 2 junction boxes each, and seven circuits which each contain a single junction box. 
Multiplying together the sizes of the three largest circuits (5, 4, and one of the circuits of size 2) produces 40.

Your list contains many junction boxes; connect together the 1000 pairs of junction boxes which are closest together. 
Afterward, what do you get if you multiply together the sizes of the three largest circuits?

To begin, get your puzzle input.



-----
Distance[point1=[162, 817, 812], point2=[425, 690, 689], distance=316.90219311326956]
New circuit. [162,817,812, 425,690,689]
Circuit sizes: 2
-----
Distance[point1=[162, 817, 812], point2=[431, 825, 988], distance=321.560258738545]
Removing: [162,817,812, 425,690,689]
Putting: [162,817,812, 425,690,689, 431,825,988]
Circuit sizes: 3
-----
Distance[point1=[906, 360, 560], point2=[805, 96, 715], distance=322.36935338211043]
New circuit. [805,96,715, 906,360,560]
Circuit sizes: 3,2
-----
Distance[point1=[431, 825, 988], point2=[425, 690, 689], distance=328.11888089532425]
Removing: [162,817,812, 425,690,689, 431,825,988]
Putting: [162,817,812, 425,690,689, 431,825,988]
Circuit sizes: 2,3
-----
Distance[point1=[862, 61, 35], point2=[984, 92, 344], distance=333.6555109690233]
New circuit. [862,61,35, 984,92,344]
Circuit sizes: 2,3,2
-----
Distance[point1=[52, 470, 668], point2=[117, 168, 530], distance=338.33858780813046]
New circuit. [117,168,530, 52,470,668]
Circuit sizes: 2,3,2,2
-----
Distance[point1=[819, 987, 18], point2=[941, 993, 340], distance=344.3893145845266]
New circuit. [819,987,18, 941,993,340]
Circuit sizes: 2,3,2,2,2
-----
Distance[point1=[906, 360, 560], point2=[739, 650, 466], distance=347.59890678769403]
Removing: [805,96,715, 906,360,560]
Putting: [739,650,466, 805,96,715, 906,360,560]
Circuit sizes: 3,2,2,2,3
-----
Distance[point1=[346, 949, 466], point2=[425, 690, 689], distance=350.786259708102]
Removing: [162,817,812, 425,690,689, 431,825,988]
Putting: [162,817,812, 346,949,466, 425,690,689, 431,825,988]
Circuit sizes: 2,2,2,3,4
-----
Distance[point1=[906, 360, 560], point2=[984, 92, 344], distance=352.936254867646]
Removing: [862,61,35, 984,92,344]
Removing: [739,650,466, 805,96,715, 906,360,560]
Putting: [739,650,466, 805,96,715, 862,61,35, 906,360,560, 984,92,344]
Circuit sizes: 2,2,4,5
5,4,2,2


{316.90219311326956=Connection[j1=JunctionBox[x=425, y=690, z=689], j2=JunctionBox[x=162, y=817, z=812]],
321.560258738545=Connection[j1=JunctionBox[x=431, y=825, z=988], j2=JunctionBox[x=162, y=817, z=812]], 
322.36935338211043=Connection[j1=JunctionBox[x=805, y=96, z=715], j2=JunctionBox[x=906, y=360, z=560]], 
328.11888089532425=Connection[j1=JunctionBox[x=425, y=690, z=689], j2=JunctionBox[x=431, y=825, z=988]], 
333.6555109690233=Connection[j1=JunctionBox[x=984, y=92, z=344], j2=JunctionBox[x=862, y=61, z=35]], 

338.33858780813046=Connection[j1=JunctionBox[x=117, y=168, z=530], j2=JunctionBox[x=52, y=470, z=668]], 
344.3893145845266=Connection[j1=JunctionBox[x=941, y=993, z=340], j2=JunctionBox[x=819, y=987, z=18]], 
347.59890678769403=Connection[j1=JunctionBox[x=739, y=650, z=466], j2=JunctionBox[x=906, y=360, z=560]], 
350.786259708102=Connection[j1=JunctionBox[x=425, y=690, z=689], j2=JunctionBox[x=346, y=949, z=466]], 
352.936254867646=Connection[j1=JunctionBox[x=984, y=92, z=344], j2=JunctionBox[x=906, y=360, z=560]], 

367.9823365326113=Connection[j1=JunctionBox[x=425, y=690, z=689], j2=JunctionBox[x=592, y=479, z=940]], 
371.70552861102294=Connection[j1=JunctionBox[x=542, y=29, z=236], j2=JunctionBox[x=352, y=342, z=300]], 
372.02284876066415=Connection[j1=JunctionBox[x=117, y=168, z=530], j2=JunctionBox[x=352, y=342, z=300]], 
373.41130138226936=Connection[j1=JunctionBox[x=466, y=668, z=158], j2=JunctionBox[x=352, y=342, z=300]], 
379.242666376029=Connection[j1=JunctionBox[x=862, y=61, z=35], j2=JunctionBox[x=542, y=29, z=236]], 
384.6309919910251=Connection[j1=JunctionBox[x=431, y=825, z=988], j2=JunctionBox[x=592, y=479, z=940]], 
387.2014979309868=Connection[j1=JunctionBox[x=425, y=690, z=689], j2=JunctionBox[x=739, y=650, z=466]], 
391.46519640959144=Connection[j1=JunctionBox[x=52, y=470, z=668], j2=JunctionBox[x=162, y=817, z=812]], 
407.53527454687895=Connection[j1=JunctionBox[x=970, y=615, z=88], j2=JunctionBox[x=819, y=987, z=18]], 
411.9441709746601=Connection[j1=JunctionBox[x=984, y=92, z=344], j2=JunctionBox[x=805, y=96, z=715]], 
411.96723170660067=Connection[j1=JunctionBox[x=739, y=650, z=466], j2=JunctionBox[x=466, y=668, z=158]], 
413.5166260260886=Connection[j1=JunctionBox[x=346, y=949, z=466], j2=JunctionBox[x=162, y=817, z=812]], 
417