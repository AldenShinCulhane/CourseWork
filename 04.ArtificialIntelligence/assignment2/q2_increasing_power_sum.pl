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

%%%%% SECTION: increasingPowerSum
%%%%% Predicate definition: increasingPowerSum(List, Power, PowerInc, Sum)

%[1, 2 ,3, 4], 1, 2 => 1^1 + 2^3 + 3^5 + 4^7 = 16636
%power starts at 1 and increases by 2 each time

%base case 1
increasingPowerSum([], Power, PowerInc, Sum) :- Sum is 0.

%recursive case
increasingPowerSum([H|T], Power, PowerInc, Sum) :- 
    NewPower is Power + PowerInc, 
    increasingPowerSum(T, NewPower, PowerInc, SubSum),
    Sum is H^Power + SubSum.

