%%%%% NAME: Ivan Chan
%%%%% NAME: 
%%%%% NAME: 
%
% Add the required rules in the corresponding sections. 
% If you put the rules in the wrong sections, you will lose marks.
%
% You may add additional comments as you choose but DO NOT MODIFY the comment lines below
%

%%%%% SECTION: deepestNesting
%%%%% Predicate definition: deepestNesting(List, Depth)

% base cases
deepestNesting([], 0). % empty list
deepestNesting(X, 0) :- not (is_list(X)). % single term

% recursive case
deepestNesting([H|T], Depth) :-
    deepestNesting(H, TempDepth), TempDepthH is TempDepth + 1, deepestNesting(T, TempDepthT),
    Depth is max(TempDepthH, TempDepthT).