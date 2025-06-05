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

%%%%% SECTION: puzzleInterleaving
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

    % L - can be determined on its own (Guess C5, then make L = C5, then force L to be 1)
    digit(C5), C5 < 2, L = C5, not L = 0, % Guess C5, carry is either 0 or 1, L is C5 from generate_and_test, L is not a leading zero.


    % T - can be calculated from only L and C10. Guessing C10 is safe, only 2 options to choose from.
    digit(C10), C10 < 2, T is L + L + C10, % guess C10, then calculate T
    T is (L * T) mod 10, C6 is (L * T) // 10, % confirm T, then calculate C6


    % E - can be 'calculated' from L, E, and C6. C6 is calculated from earlier.
    
    digit(E), E is (L * E + C6) mod 10, C7 is (L * E + C6) // 10, % guess E, confirm E, then confirm C7
    L is (T + E) mod 10, C8 is (T + E) // 10, % confirm E and T through L, then calculate C8
    not L = E, not E = T, % alldiff


    % P - can be 'calculated' from T, E, and C1. Guessing C1 is safe, only 2 options to choose from.
    digit(P), digit(C1), C1 < 2, T is (P * E + C1) mod 10, C2 is (P * E + C1) // 10, % guess P, guess C1, confirm P through T, calculate C2
    not L = P, not E = P, not T = P, % alldiff


    % S - can be calculated from only T and P. The previous guess, C1, is confirmed.
    S is (P * T) mod 10, C1 is (P * T) // 10, % calculate S, then confirm C1
    not L = S, not E = S, not T = S, not P = S, % alldiff


    % I - can be calculated from P, L, and C2. C2 is calculated earlier.
    I is P * L + C2, % calculate I
    not L = I, not E = I, not T = I, not P = I, not I = S, % alldiff


    % A - can be 'calculated' from only E and T.
    digit(A), E is (A * T) mod 10, C3 is (A * T) // 10, % guess A, confirm A through E, calculate C3
    not L = A, not E = A, not T = A, not A = P, not A = I, not A = S, % alldiff


    % V - can be calculated from A, E, and C3. C3 is calculated earlier.
    V is (A * E + C3) mod 10, C4 is (A * E + C3) // 10, % calculate V, calculate C4
    not L = V, not E = V, not T = V, not A = V, not P = V, not I = V, not S = V, % alldiff


    % O - can be calculated from A, L, and C4. C4 is calculated earlier. 
    % Also, it's a bit late, but the C10 constraint is calculated here

    O is (A * L + C4) mod 10, % calculate O
    O is (I + V + T + C8) mod 10, C9 is (I + V + T + C8) // 10, % confirm O, calculate C9
    O is (O + E + C9) mod 10, C10 is (O + E + C9) // 10, % confirm O, confirm C10
    not L = O, not E = O, not T = O, not A = O, not P = O, not I = O, not S = O, not O = V. % all diff


% Total guesses: C10, E, P, C1, A