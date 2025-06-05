% Enter the names of your group members below.
% If you only have 2 group members, leave the last space blank
%
%%%%%
%%%%% NAME: Ivan Chan
%%%%% NAME: Hanna Frances Bobis
%%%%% NAME: Alden Shin-Culhane
%
% Add the required rules in the corresponding sections. 
% If you put the rules in the wrong sections, you will lose marks.
%
% You may add additional comments as you choose but DO NOT MODIFY the comment lines below
%

%%%%% SECTION: foodSolve
%%%%% Below, you should define rules for the predicate "solve", which takes in your list of
%%%%% variables and finds an assignment for each variable which solves the problem.
%%%%%
%%%%% You should also define rules for the predicate "solve_and_print" which calls your
%%%%% solve predicate and prints out the results in an easy to read, human readable format.
%%%%% The predicate "solve_and_print" should take in no arguments

meal(egg_roll). meal(chow_mein). meal(sour_soup). meal(fried_rice). meal(peking_duck).

drink(milk). drink(coffee). drink(tea). drink(water). drink(juice).


solve_and_print :-
    solve(M1, M2, D1, M3, M4, D2, M5, M6, D3, M7, M8, D4, M9, M10, D5),
    write('Monday\'s first course meal is '), write(M1), nl,
    write('Monday\'s second course meal is '), write(M2), nl,
    write('Monday\'s drink is '), write(D1), nl,
    write('Tuesday\'s first course meal is '), write(M3), nl,
    write('Tuesday\'s second course meal is '), write(M4), nl,
    write('Tuesday\'s drink is '), write(D2), nl,
    write('Wednesday\'s first course meal is '), write(M5), nl,
    write('Wednesday\'s second course meal is '), write(M6), nl,
    write('Wednesday\'s drink is '), write(D3), nl,
    write('Thursday\'s first course meal is '), write(M7), nl,
    write('Thursday\'s second course meal is '), write(M8), nl,
    write('Thursday\'s drink is '), write(D4), nl,
    write('Friday\'s first course meal is '), write(M9), nl,
    write('Friday\'s second course meal is '), write(M10), nl,
    write('Friday\'s drink is '), write(D5), nl.


% start with least guesses, lots of constraints, to lots of guesses, minimum constraints.
solve(M1, M2, D1, M3, M4, D2, M5, M6, D3, M7, M8, D4, M9, M10, D5) :-

    D5 = juice, % constraint 8
    drink(D1), not D1 = D5, % guess D1
    drink(D2), not D1 = D2, not D2 = D5, % guess D2
    drink(D3), not D1 = D3, not D2 = D3, not D3 = D5, % guess D3
    drink(D4), not D1 = D4, not D2 = D4, not D3 = D4, not D4 = D5, % guess D4
    
    append(Start1, [milk, coffee, tea | T1], [D1, D2, D3, D4, D5]), % constraint 5
    append(Start2, [tea, water | T2], [D1, D2, D3, D4, D5]), % constraint 8

    M3 = chow_mein, % constraint 4
    meal(M7), not M7 = sour_soup, not M7 = chow_mein, not M7 = egg_roll, % guess M7, add constraints related to 3, 4, 5
    meal(M1), not M1 = chow_mein, not M1 = egg_roll, % guess M1, add constraints related to 4, 5
    meal(M8), not M8 = sour_soup, not M8 = egg_roll, % guess M8, add constraints related to 3, 5, 7
    meal(M2), not M2 = egg_roll, not M2 = sour_soup, % guess M2, add constraints related to 3, 5, 7
    not same([M1, M2], [M7, M8]), % constraint 1
    meal(M4), not M4 = sour_soup, % guess M4, add constraint related to 7
    not (not M3 = peking_duck, not M4 = peking_duck), % constraint 2
    not same([M1, M2], [M3, M4]), not same([M3, M4], [M7, M8]), % constraint 1
    meal(M5), not M5 = sour_soup, not M5 = chow_mein, % guess M5, add constraint related to 3, 4
    meal(M9), not M9 = chow_mein, % guess M9, add constraint related to 4
    meal(M6), not M6 = sour_soup, % guess M6, add constraint related to 3, 7
    not (not M5 = peking_duck, not M6 = peking_duck), % constraint 2
    not same([M1, M2], [M5, M6]), not same([M3, M4], [M5, M6]), not same([M5, M6], [M7, M8]), % constraint 1
    meal(M10), not M10 = sour_soup, % guess M10, add constraint related to 7
    not same([M1, M2], [M9, M10]), not same([M3, M4], [M9, M10]), not same([M5, M6], [M9, M10]), not same([M7, M8], [M9, M10]), % constraint 1

    append(Start3, [peking_duck, X1, X2, chow_mein | T3], [M1, M2, M3, M4, M5, M6, M7, M8, M9, M10]), % constraint 4
    at_least_two([M1, M2, M3, M4, M5, M6, M7, M8, M9, M10], egg_roll). % constraint 6

% helper functions
same([X1, X2], [Y1, Y2]) :- X1 = Y1, X2 = Y2.

at_least_two([H | T], H) :- member(H, T).

at_least_two([H | T], X) :- not X = H, at_least_two(T, X).