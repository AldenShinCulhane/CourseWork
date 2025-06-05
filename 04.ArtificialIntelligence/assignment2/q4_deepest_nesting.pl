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

%%%%% SECTION: deepestNesting
%%%%% Predicate definition: deepestNesting(List, Depth)

% Base case for an empty list with depth 0
deepestNesting([], 0).

% Recursive rule for non-list elements with depth 0
deepestNesting([H|T], Depth) :-
    is_list(H),
    deepestNesting(H, DepthHead),
    deepestNesting(T, DepthTail),
    Depth is 1 + max(DepthHead, DepthTail).

% Recursive rule for list elements with depth 1 + depth of the list
deepestNesting([H|T], Depth) :-
    deepestNesting(T, Depth),
    not is_list(H).
