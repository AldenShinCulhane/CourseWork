% Enter the names of your group members below.
% If you only have 2 group members, leave the last space blank
%
%%%%%
%%%%% NAME: Ivan Chan
%%%%% NAME: 
%%%%% NAME: 
%
% Add the required rules in the corresponding sections. 
% If you put the rules in the wrong sections, you will lose marks.
%
% You may add additional comments as you choose but DO NOT MODIFY the comment lines below
%

%%%%% SECTION: partitionMonths
%%%%% Predicate definition: partitionMonths(Months, Prices, Increase, Decrease)


% base case - 2 months and 1 price calculation left
partitionMonths([M1, M2], [P1, P2], [M2], []) :- P1<P2.
partitionMonths([M1, M2], [P1, P2], [], [M2]) :- P1>=P2.

% recursive case - price increases
partitionMonths([M1, M2|MT], [P1, P2|PT], [M2|Increase], Decrease) :-
    P1<P2, partitionMonths([M2|MT], [P2|PT], Increase, Decrease).

% recursive case - price decreases
partitionMonths([M1, M2|MT], [P1, P2|PT], Increase, [M2|Decrease]) :-
    P1>=P2, partitionMonths([M2|MT], [P2|PT], Increase, Decrease).