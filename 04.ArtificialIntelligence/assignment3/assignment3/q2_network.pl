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

%%%%% SECTION: networkSolve
%%%%% Below, you should define rules for the predicate "solve", which takes in your list of
%%%%% variables and finds an assignment for each variable which solves the problem.
%%%%%
%%%%% You should also define rules for the predicate "solve_and_print" which calls your
%%%%% solve predicate and prints out the results in an easy to read, human readable format.
%%%%% The predicate "solve_and_print" should take in no arguments

% domains
computer(1). computer(2). computer(3). computer(4). computer(5). computer(6).

% solve_and_print
solve_and_print :- 
    solve([Bananas, Firstbank, Netvue, Pricenet, Sysworld, Univmoose,
        Catarina, Lizzie, Mona, 
        Anthony, Daniel, Jaime,
        Elby, Kim, Osborne, Tsuji, Wolverton, Zickerman]),

    write('----- ADDRESSSES -----'), nl,
    write('bananas.com is '), write(Bananas), nl,
    write('firstbank.com is '), write(Firstbank), nl,
    write('netvue.com is '), write(Netvue), nl,
    write('pricenet.com is '), write(Pricenet), nl,
    write('sysworld.com is '), write(Sysworld), nl,
    write('univmoose.com is '), write(Univmoose), nl, nl,

    write('----- ADMIN WOMEN -----'), nl,
    write('catarina is '), write(Catarina), nl,
    write('lizzie is '), write(Lizzie), nl,
    write('mona is '), write(Mona), nl, nl,

    write('----- ADMIN MEN -----'), nl,
    write('Anthony is '), write(Anthony), nl,
    write('Daniel is '), write(Daniel), nl,
    write('Jaime is '), write(Jaime), nl, nl,
    
    write('----- ADMIN LAST NAMES -----'), nl,
    write('Elby is '), write(Elby), nl,
    write('Kim is '), write(Kim), nl,
    write('Osborne is '), write(Osborne), nl,
    write('Tsuju is '), write(Tsuji), nl,
    write('Wolverton is '), write(Wolverton), nl,
    write('Zickerman is '), write(Zickerman), nl, nl.

% solve
solve([Bananas, Firstbank, Netvue, Pricenet, Sysworld, Univmoose,
    Catarina, Lizzie, Mona, 
    Anthony, Daniel, Jaime,
    Elby, Kim, Osborne, Tsuji, Wolverton, Zickerman]) :-

    % constraint 2: Netvue is Mona or Wolverton. Start with small domain for netvue
    computer(Mona), computer(Wolverton), not Mona = Wolverton, computer(Netvue),
    not (not Mona = Netvue, not Wolverton = Netvue),

    % constraint 8: check netvue with zickerman and catarina, we check netvue with previous constraint, so we can solve netvue first.
    computer(Zickerman), not Zickerman = Netvue, not Zickerman = Wolverton, 
    trip(Zickerman, Netvue, [Zickerman, Netvue]),
    computer(Catarina), not Catarina = Zickerman, not Catarina = Netvue, not Catarina = Mona,
    trip(Catarina, Zickerman, [Catarina, Zickerman]),
    trip(Catarina, Netvue, CN_trip), count(CN_trip, CN_count), CN_count = 3,

    % now we check all the men admins so we can find the women admins later

    % finding jaime now, since he is linked with two constraints
    % constraint 3: Jaime -> Anthony must pass through Elby's
    computer(Jaime), not Jaime = Catarina, not Jaime = Mona,
    computer(Anthony), not Anthony = Jaime, not Anthony = Catarina, not Anthony = Mona,
    computer(Elby), not Elby = Wolverton, not Elby = Zickerman,
    trip(Jaime, Anthony, JA_trip), is_member(Elby, JA_trip),
    
    % constraint 5 part 1: only checking trip between Jaime and Tsuji which pass thru univmoose. 
    computer(Tsuji), not Tsuji = Wolverton, not Tsuji = Zickerman, not Tsuji = Elby, not Tsuji = Jaime,
    computer(Univmoose), not Univmoose = Tsuji, not Univmoose = Jaime, not Univmoose = Netvue,
    trip(Jaime, Tsuji, JT_trip), is_member(Univmoose, JT_trip),
    

    % constraint 4: Daniel only exchanges with Sysworld. Finding the last man admin.
    computer(Daniel), not Daniel = Catarina, not Daniel = Mona, not Daniel = Jaime, not Daniel = Anthony,
    computer(Sysworld), not Daniel = Sysworld, not Sysworld = Netvue,
    trip(Daniel, Sysworld, [Daniel, Sysworld]), trip(X, Sysworld, XS_trip), 
    % check that no other computer is connected to Daniel
    not (computer(X), not X = Daniel, not X = Sysworld, trip(X, Daniel, L)),

    % now that we set all the men admins, we can solve for the women admins:

    % constraint 5 part 2: check if tsuji is a womam,
    not Tsuji = Daniel, not Tsuji = Anthony, % already checked Jaime in constraint 5 part 1

    % constraint 6: check that bananas is a woman
    computer(Bananas), 
    not Bananas = Daniel, not Bananas = Jaime, not Bananas = Anthony,
    not Bananas = Netvue, not Bananas = Univmoose, not Bananas = Netvue, not Bananas = Sysworld,

    % constraint 7: Kim -> rest of world contains firstbank. Checking this before 1 since we only have to guess two variables
    computer(Kim), 
    not Kim = Elby, not Kim = Wolverton, not Kim = Tsuji, not Kim = Zickerman,
    computer(Firstbank),
    not Firstbank = Univmoose, not Firstbank = Sysworld, not Firstbank = Bananas, not Firstbank = Netvue, not Firstbank = Kim,
    trip(Kim, 0, KF_trip), is_member(FirstBank, KF_trip),

    % constraint 1: Do this last since we have to guess 3 values which arent explicitly correlated to the other contraints
    computer(Lizzie),
    not Lizzie = Mona, not Lizzie = Catarina, not Lizzie = Jaime, not Lizzie = Daniel, not Lizzie = Anthony,
    computer(Osborne),
    not Osborne = Lizzie, not Osborne = Kim, not Osborne = Tsuji, not Osborne = Elby, not Osborne = Wolverton, not Osborne = Zickerman,
    computer(Pricenet),
    not Pricenet = Lizzie, not Pricenet = Osborne, 
    not Pricenet = Bananas, not Pricenet = Netvue, not Pricenet = Firstbank, not Pricenet = Sysworld, not Pricenet = Univmoose,
    trip(Lizzie, Osborne, LO_trip), is_member(Pricenet, LO_trip).



%%%%% SECTION: trip
%%%%% Below, you should define rules for the predicate "trip", which takes in 
%%%%% two computer systems and returns the list of locations in between them.
%%%%% The signature of this predicate is trip(From,To,Path)


% network model
path([6, 5, 3, 2, 1, 0]).
path([6, 5, 3, 4]).
path([4, 3, 2, 1, 0]).


% "base case" :)
trip(C, C, [C]).

% case 1 - guess a path L such that L contains C1 and C2. Get subarray of L.
trip(C1, C2, Path) :- path(L), append(Start, [C2 | T], L), is_member(C1, Start), subpath(L, C1, C2, Path).

% case 2 - guess a path L such that L contains C2 and C1. Get subarray of L.
trip(C1, C2, Path) :- path(L), append(Start, [C1 | T], L), is_member(C2, Start), subpath(L, C2, C1, Path).


% gets a subarray
% base case - return element at end of subarray
subpath([H|T], H, H, [H]).

% recursive case 1 - add element onto Path when head of array contains "X1"
subpath([H, H2 | T], H, X2, [H | Path]) :- subpath([H2 | T], H2, X2, Path).

%recursive case 2 - move onto the next element if head of array does not contain X1
subpath([H | T], X1, X2, Path) :- not X1 = H, subpath(T, X1, X2, Path).

% member() helper predicate
is_member(H, [H|T]).
is_member(X, [H|T]) :- not X = H, is_member(X, T).

% count() helper predicate
count([], 0). % count is 0 base case
count([H | T], Sum) :- count(T, TempSum), Sum is TempSum + 1. % recursive case