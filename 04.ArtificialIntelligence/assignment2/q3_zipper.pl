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

%%%%% SECTION: zipper
%%%%% Predicate definition: zipper(List1, List2, Zipper)

% Base Case

% zipper([], [], []).
% zipper(List1, [], List1).
zipper([], List2, List2).

% Recursive Case
% flip around which list is first each time.
zipper([H|T], List2, [H|Result]) :- zipper(List2, T, Result).

/*
Testing

zipper([1], [2], X).
X = [1, 2]
Yes (0.00s cpu)

zipper([1, 2, a, b], [f, g, c, d], [1, f, 2, g, a, c, b, d]).
Yes (0.00s cpu)

zipper([a, b, c, d], [1, 2], Zipper).
Zipper = [a, 1, b, 2, c, d]
Yes (0.00s cpu)

zipper([1, 2, 3], X, [1, a, 2, b, 3]).
X = [a, b]
Yes (0.00s cpu)

zipper([a, b], [1, 2, 3], Y).
Y = [a, 1, b, 2, 3]
Yes (0.00s cpu)

zipper(X, [a, b, c], [1, a, 2, b, c]).
X = [1, 2]
Yes (0.00s cpu, solution 1, maybe more)
No (0.00s cpu)
*/