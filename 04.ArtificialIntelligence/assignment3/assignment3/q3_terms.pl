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

%%%%% SECTION: appendT
%%%%% Add your rules for appendT(Term1,Term2,Result) below
% Base case when Term1 is empty
appendT(nil, Term2, Term2).

% Recursive case to break down Term1 into Head & Tail, then append Tail & Term2
appendT(next(Head, Tail), Term2, next(Head, Result)) :-
    appendT(Tail, Term2, Result).

%%%%% SECTION: list2term
%%%%% Add your rules for list2term(List,Term) below
% Base case when List is empty
list2term([], nil).

% Recursive case when Head is a List
list2term([Head|Tail], next(HeadTerm, TailTerm)) :-
    is_list(Head),
    list2term(Head, HeadTerm),
    list2term(Tail, TailTerm).

% Recursive case when Head is an atom
list2term([Head|Tail], next(Head, TailTerm)) :-
    atom(Head),
    list2term(Tail, TailTerm).

%%%%% SECTION: flat
%%%%% Add your rules for flat(Term,FlatTerm) below
% Base case when Term is empty
flat(nil, nil).

% Recursive case when Head is a term flatten Head & Tail separately, then append
flat(next(Head, Tail), FlatTerm) :-
    isTerm(Head),
    flat(Head, FlatHead),
    flat(Tail, FlatTail),
    appendT(FlatHead, FlatTail, FlatTerm).

% Recursive case when Head is an atom, flatten the Tail & append Head
flat(next(Head, Tail), next(Head, FlatTail)) :-
    atom(Head),
    flat(Tail, FlatTail).

% Helper predicate to check for term representation
isTerm(next(Head, Tail)).
