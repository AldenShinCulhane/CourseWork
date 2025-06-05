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

%%%%% SECTION: puzzleGenerateAndTest
%%%%% Below, you should define rules for the predicate "solve", which takes in your list of
%%%%% variables and finds an assignment for each variable which solves the problem.
%%%%%
%%%%% You should also define rules for the predicate "solve_and_print" which calls your
%%%%% solve predicate and prints out the results in an easy to read, human readable format.
%%%%% The predicate "solve_and_print" should take in no arguments

digit(0). digit(1). digit(2). digit(3). digit(4). digit(5). digit(6). digit(7). digit(8). digit(9).

solve_and_print :- 
    solve(L, E, T, A, P, I, S, O, V),
    write('L is '), write(L), nl,
    write('E is '), write(E), nl,
    write('T is '), write(T), nl,
    write('A is '), write(A), nl,
    write('P is '), write(P), nl,
    write('I is '), write(I), nl,
    write('S is '), write(S), nl,
    write('O is '), write(O), nl,
    write('V is '), write(V), nl.

solve(L, E, T, A, P, I, S, O, V) :-
    digit(L), digit(E), digit(T), digit(A), digit(P), digit(I), digit(S), digit(O), digit(V),
    not L = 0, not T = 0,
    
    % all diff
    all_diff([L, E, T, A, P, I, S, O, V]),

    % ITS
    S is (P * T) mod 10, C1 is (P * T) // 10,
    T is (P * E + C1) mod 10, C2 is (P * E + C1) // 10,
    I is P * L + C2,

    % LOVE
    E is (A * T) mod 10, C3 is (A * T) // 10,
    V is (A * E + C3) mod 10, C4 is (A * E + C3) // 10,
    O is (A * L + C4) mod 10, C5 is (A * L + C4) // 10,
    L is C5,

    % LET
    T is (L * T) mod 10, C6 is (L * T) // 10,
    E is (L * E + C6) mod 10, C7 is (L * E + C6) // 10,
    L is L * L + C7,

    % TOOLS
%   S is S.
    L is (T + E) mod 10, C8 is (T + E) // 10,
    O is (I + V + T + C8) mod 10, C9 is (I + V + T + C8) // 10,
    O is (O + E + C9) mod 10, C10 is (O + E + C9) // 10,
    T is L + L + C10.

% helper predicates
all_diff([]).

all_diff([H|T]) :-
    not member(H, T),
    all_diff(T).