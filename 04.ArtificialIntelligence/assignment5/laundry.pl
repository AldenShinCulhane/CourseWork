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

%%%%% SECTION: dynamic_laundry
%%%%% These lines allow you to write statements for a predicate that are not consecutive in your program
%%%%% Doing so enabkes the specification of an initial state in another file
%%%%% DO NOT MODIFY THE CODE IN THIS SECTION
:- dynamic clean/2.
:- dynamic wet/2.
:- dynamic folded/2.
:- dynamic holding/2.
:- dynamic hasSoap/2.
:- dynamic hasSoftener/2.
:- dynamic hasLint/2.
:- dynamic in/3.
:- dynamic container/1.


%%%%% SECTION: planner_laundry
%%%%% This line loads the generic planner code from the file "planner.pl"
%%%%% Just ensure that that the planner.pl file is in the same directory as this one
%%%%% DO NOT EDIT THIS SECTION
:- [planner].


%%%%% SECTION: init_laundry
%%%%% Loads the initial state from the file laundryInit.pl
%%%%% HINT: You can create other files with other initial states to more easily test individual actions
%%%%%       To do so, just replace the line below with one loading in the file with your initial state
:- [laundryInit].


%%%%% SECTION: goal_states_laundry
%%%%% Below we define different goal states, each with a different ID
%%%%% HINT: It may be useful to define "easier" goals when starting your program or when debugging
%%%%%       You can do so by adding more goal states below
goal_state(1, S) :- clean(cl1,S).
goal_state(2, S) :- clean(cl1,S), not wet(cl1,S).
goal_state(3, S) :- clean(cl1,S), not wet(cl1,S), folded(cl1,S), in(cl1,dresser,S).
goal_state(4, S) :- clean(cl1,S), clean(cl2, S).
goal_state(5, S) :- clean(cl1,S), not wet(cl1,S), clean(cl2, S), not wet(cl2,S).
goal_state(6, S) :- clean(cl1,S), not wet(cl1,S), folded(cl1,S), clean(cl2, S), not wet(cl2,S), folded(cl2,S).


%%%%% Your program is not required to produce plans for the following long
%%%%% goal state that is optional. But if you want to try it, then
%%%%% try to solve this more difficult planning problem.
goal_state(100, S) :- clean(cl1,S), clean(cl2,S), not wet(cl1,S), not wet(cl2,S),
        folded(cl1,S), folded(cl2,S), in(cl1,dresser,S), in(cl2,dresser,S).



%%%%% SECTION: precondition_axioms_laundry
%%%%% Write precondition axioms for all actions in your domain. Recall that to avoid
%%%%% potential problems with negation in Prolog, you should not start bodies of your
%%%%% rules with negated predicates. Make sure that all variables in a predicate 
%%%%% are instantiated by constants before you apply negation to the predicate that 
%%%%% mentions these variables. 

% The following defines different types of objects as containers
% You do not need to edit it
container(X) :- washer(X).
container(X) :- dryer(X).
container(X) :- cupboard(X).
container(X) :- hamper(X).
container(dresser).

% Put the rest of your precondition axioms below
poss(fetch(O1, C), S) :- container(C), in(O1, C, S), not holding(O2, S).
poss(putAway(O, C), S) :- not soap(O), not softener(O), holding(O, S).
poss(addSoap(P, W), S) :- soap(P), holding(P, S), washer(W), not hasSoap(W, S).
poss(addSoftener(T, W), S) :- softener(T), holding(T, S), washer(W), not hasSoftener(W, S).
poss(washClothes(C, W), S) :- washer(W), hasSoap(W, S), hasSoftener(W, S), clothes(C), not clean(C, S), in(C, W, S).
poss(removeLint(D), S) :- dryer(D), hasLint(D, S), not holding(O, S).
poss(dryClothes(C, D), S) :- clothes(C), wet(C, S), dryer(D), not hasLint(D, S), in(C, D, S).
poss(fold(C), S) :- clothes(C), not folded(C, S), clean(C, S), not wet(C, S), not holding(O, S).
poss(wear(C), S) :- clothes(C), folded(C, S).
poss(move(O, C, dresser), S) :- container(C), in(O, C, S), not holding(O2, S).
poss(move(O, C1, C2), S) :- container(C1), in(O, C1, S), container(C2), not C2 = cupboard, not in(O3, C2, S), not holding(O2, S).


%%%%% SECTION: successor_state_axioms_laundry 
%%%%% Write successor-state axioms that characterize how the truth value of all 
%%%%% fluents change from the current situation S to the next situation [A | S]. 
%%%%% You will need two types of rules for each fluent: 
%%%%% 	(a) rules that characterize when a fluent becomes true in the next situation 
%%%%%	as a result of the last action, and
%%%%%	(b) rules that characterize when a fluent remains true in the next situation,
%%%%%	unless the most recent action changes it to false.
%%%%% When you write successor state axioms, you can sometimes start bodies of rules 
%%%%% with negation of a predicate, e.g., with negation of equality. This can help 
%%%%% to make them a bit more efficient.
%%%%%
%%%%% Write your successor state rules here: you have to write brief comments %

% O is in C if it was put away there or moved into there. O is not in C if it was fetched from there, or moved away from there.
in(O, C, [putAway(O , C)|S]).
in(O, C, [move(O, C1, C)|S]).
in(O, C, [A|S]) :- not (A = fetch(O, C)), not (A = move(O, C, C2)), in(O, C, S).

% robot is holding O if it was fetched. robot is not holding O if it was put away, or it added it to a washer.
holding(O, [fetch(O, C)|S]).
holding(O, [A|S]) :- not (A = putAway(O, C)), not (A = addSoap(O, C)), not (A = addSoftener(O, C)), holding(O, S).

% washer has soap if soap was added, washer does not have soap if clothes are washed.
hasSoap(W, [addSoap(P, W)|S]).
hasSoap(W, [A|S]) :- not (A = washClothes(C, W)), hasSoap(W, S).

% washer has softener if softener was added, washer does not have softener if clothes are washed.
hasSoftener(W, [addSoftener(P, W)|S]).
hasSoftener(W, [A|S]) :- not (A = washClothes(C, W)), hasSoftener(W, S).

% dryer has lint if clothes were dried, dryer does not have lint if lint was removed.
hasLint(D, [dryClothes(C, D)|S]).
hasLint(D, [A|S]) :- not (A = removeLint(D)), hasLint(D, S).

% clothes are clean if they are washed, clothes are not clean if they are worn.
clean(C, [washClothes(C, W)|S]).
clean(C, [A|S]) :- not (A = wear(C)), clean(C, S).

% clothes are wet if they are washed, clothes are not wet if they are dried.
wet(C, [washClothes(C, W)|S]).
wet(C, [A|S]) :- not (A = dryClothes(C, D)), wet(C, S).

% clothes are folded if you fold them, clothes are not folded if they are worn.
folded(C, [fold(C)|S]).
folded(C, [A|S]) :- not (A = wear(C)), folded(C, S).

%%%%% SECTION: declarative_heuristics_laundry
%%%%% The predicate useless(A,ListOfPastActions) is true if an action A is useless
%%%%% given the list of previously performed actions. The predicate useless(A,ListOfPastActions)
%%%%% helps solve the planning problem by providing declarative heuristics to 
%%%%% the planner. If this predicate is correctly defined using a few rules, then it 
%%%%% helps speed-up the search that your program is doing to find a list of actions
%%%%% that solves a planning problem. Write as many rules that define this predicate
%%%%% as you can: think about useless repetitions you would like to avoid, and about 
%%%%% order of execution (i.e., use common sense properties of the application domain). 
%%%%% Your rules have to be general enough to be applicable to any problem in your domain,
%%%%% in other words, they have to help in solving a planning problem for any instance
%%%%% (i.e., any initial and goal states).
%%%%%	
%%%%% write your rules implementing the predicate  useless(Action,History) here. %

% fetching an object is useless if you have just put away the object
useless(fetch(O, C), [putAway(O, C)|S]).

% fetching an object is useless if you have just moved the object to another container
useless(fetch(O, C), [move(O, C1, C)|S]).

% fetching soap from a washer is useless
useless(fetch(P, W), S) :- soap(P), washer(W).

% fetching softener from a washer is useless
useless(fetch(T, W), S) :- softener(T), washer(W).

% fetching clothes is useless since no actions require clothes to be on hand + "move" is more efficient
useless(fetch(C, X), S) :- clothes(C).

% putting away an object is useless if you have just fetched the object
useless(putAway(O, C), [fetch(O, C)|S]).

% putting away soap is useless because we need to use the addSoap() action instead
useless(putAway(P, C), S) :- soap(P).

% putting away softener is useless because we need to use the addSoftener() action instead
useless(putAway(T, C), S) :- softener(T).

% moving an object is useless if you have just moved the object
useless(move(O, C1, C2), [move(O, C3, C1)|S]).

% it is useless to "move" soap since we need to be holding it to add it to a washer
useless(move(P, C1, C2), S) :- soap(P).

% it is useless to "move" softener since we need to be holding it to add it to a washer
useless(move(T, C1, C2), S) :- softener(T).

% it is useless to move clothes into the washer if they are clean
useless(move(C, X, W), S) :- clothes(C), clean(C, S), washer(W).

% given the above rule, it is useless to move another object when the clothes in the washer haven't even been washed!
useless(move(O, Y, Z), S) :- clothes(C), not C = O, washer(W), in(C, W, S).

% it is useless to move clothes into the dryer if they are not wet
useless(move(C, X, D), S) :- clothes(C), not wet(C, S), dryer(D).

% given the above rule, it is useless to move another object when the clothes in the dryer haven't even been dried!
useless(move(O, Y, Z), S) :- clothes(C), not C = O, dryer(D), in(C, D, S).

% moving clothes to a washer is useless if there is another washer that is already being prepared
useless(move(C, X, W), S) :- clothes(C), washer(W), washer(W2), not W = W2, hasSoap(W2).

% moving clothes to a washer is useless if there is another washer that is already being prepared
useless(move(C, X, W), S) :- clothes(C), washer(W), washer(W2), not W = W2, hasSoftener(W2).

% adding soap to a washer is useless if there is another washer that needs it instead (has clothes)
useless(addSoap(P, W), S) :- clothes(C), washer(W2), not W = W2, in(C, W2, S).

% adding soap to a washer is useless if there is another washer that needs it instead (has softener)
useless(addSoap(P, W), S) :- washer(W2), not W = W2, hasSoftener(W2, S).

% adding softener to a washer is useless if there is another washer that needs it instead (has clothes)
useless(addSoftener(T, W), S) :- clothes(C), washer(W2), not W = W2, in(C, W2, S).

% adding softener to a washer is useless if there is another washer that needs it instead (has soop)
useless(addSoftener(T, W), S) :- washer(W2), not W = W2, hasSoap(W2, S).