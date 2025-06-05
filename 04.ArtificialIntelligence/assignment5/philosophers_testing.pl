% reachables
reachable(regular, S, [pickUp(p1, f1)]).
reachable(regular, S, [tryToEat(p1), pickUp(p1, f3), pickUp(p1, f1)]).
reachable(regular, S, [putDown(p3, f2), tryToEat(p3), pickUp(p3, f3), pickUp(p3, f2), putDown(p2, f2), tryToEat(p2), pickUp(p2, f2), pickUp(p2, f1)]).

% test if all 3 happy works
reachable(heuristic, S, [putDown(p1, f1), tryToEat(p1), pickUp(p1, f3), pickUp(p1, f1), putDown(p2, f1), putDown(p3, f3), tryToEat(p3), pickUp(p3, f3), pickUp(p3, f2), putDown(p2, f2), tryToEat(p2), pickUp(p2, f2), pickUp(p2, f1)]).

% -- 4 philosophers --
reachable(regular, S, [tryToEat(p1), pickUp(p1, f4), pickUp(p1, f1)]).
reachable(regular, S, [tryToEat(p3), pickUp(p3, f4), pickUp(p3, f3), 
    tryToEat(p1), pickUp(p1, f4), pickUp(p1, f1)]).


eating(p1, [tryToEat(p1), pickUp(p1, f4), pickUp(p1, f1)]).
poss(tryToEat(p1), [pickUp(p1, f4), pickUp(p1, f1)]).

% available 
available(f1, []). % yes
available(f1, [pickUp(p1, f1)]). % no
available(f1, [putDown(p1, f1), pickUp(p1, f1)]). % yes
available(f1, [pickUp(p2, f2)]). % yes
available(f1, [putDown(p2, f2), pickUp(p2, f2), pickUp(p1, f1)]). % no

% has
has(p1, f1, []). % no
has(p1, f1, [pickUp(p1, f1)]). % yes
has(p1, f1, [putDown(p1, f1), pickUp(p1, f1)]). % no
has(p1, f1, [tryToEat(p1), pickUp(p1, f4), pickUp(p1, f1)]). % yes

% thinking
thinking(p1, []). % yes
thinking(p1, [pickUp(p1, f1)]). % no
thinking(p1, [putDown(p1, f1), pickUp(p1, f1)]). % yes

% eating
eating(p1, []). % no
eating(p1, [tryToEat(p1)]). % no
eating(p1, [pickUp(p1, f4), pickUp(p1, f1)]). % no
eating(p1, [tryToEat(p1), pickUp(p1, f4), pickUp(p1, f1)]). % yes
eating(p3, [tryToEat(p3), pickUp(p3, f3), pickUp(p3, f2), tryToEat(p1), pickUp(p1, f4), pickUp(p1, f1)]). % yes
eating(p1, [tryToEat(p1), pickUp(p1, f1), pickUp(p1, f3), pickUp(p2, f2)]). % yes for p1
eating(p1, [tryToEat(p1), pickUp(p1, f1), pickUp(p1, f3), waiting(p2, [pickUp(p2, f2)]), waiting(p3, [pickUp(p3, f3)])]). % yes
eating(p1, [tryToEat(p1), putDown(p1, f1), pickUp(p1, f1), pickUp(p1, f3)]). % no


% eating with 7 philosophers: 
eating(p1, [tryToEat(p1), pickUp(p1, f1), pickUp(p1, f7), tryToEat(p6), pickUp(p6, f6), pickUp(p6, f5), tryToEat(p4), pickUp(p4, f4), pickUp(p4, f3)]). % no
eating(p1, [tryToEat(p1), pickUp(p1, f1), pickUp(p1, f7), putDown(p6, f6), tryToEat(p6), pickUp(p6, f6), pickUp(p6, f5), tryToEat(p4), pickUp(p4, f4), pickUp(p4, f3)]). % yes

% waiting
waiting(p1, [eating(p1, [])]). % no
waiting(p1, [thinking(p1, [])]). % no
waiting(p1, [pickUp(p1, f1)]). % yes
waiting(p1, [pickUp(p1, f2), pickUp(p1, f1)]). % yes
waiting(p1, [putDown(p1, f1), pickUp(p1, f1)]). % no
waiting(p1, [putDown(p1, f1), eating(p1, [])]). % no
waiting(p1, [pickUp(p1, f1), eating(p2, [])]). % yes
waiting(p1, [tryToEat(p1), pickUp(p1, f1), pickUp(p1, f3), eating(p2, [])]). % yes

% happy
happy(p2, []). % no
happy(p2, [tryToEat(p2), pickUp(p2, f2), pickUp(p2, f3)]). % no
happy(p2, [putDown(p2, f2), tryToEat(p2), pickUp(p2, f2), pickUp(p2, f3)]). % yes
happy(p2, [putDown(p2, f3), putDown(p2, f2), tryToEat(p2), pickUp(p2, f2), pickUp(p2, f3)]). % yes
happy(p4, [putDown(p4, f4), tryToEat(p4), pickUp(p4, f1), pickUp(p4, f4), putDown(p2, f2), tryToEat(p2), pickUp(p2, f2), pickUp(p2, f1)]). % yes, both p4 and p1 - 4 philosophers
happy(p2, [putDown(p3, f2), tryToEat(p3), pickUp(p3, f3), pickUp(p3, f2), putDown(p2, f2), tryToEat(p2), pickUp(p2, f2), pickUp(p2, f1)]). % yes, both p2 and p3

% goal state tests
solve_problem(regular, 6, 8, Plan).