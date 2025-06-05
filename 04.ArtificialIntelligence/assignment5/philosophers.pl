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

%%%%% SECTION: dynamic_philosophers
%%%%% These lines allow you to write statements for a predicate that are not consecutive in your program
%%%%% Doing so enabkes the specification of an initial state in another file
%%%%% DO NOT MODIFY THE CODE IN THIS SECTION
:- dynamic thinking/2.
:- dynamic waiting/2.
:- dynamic eating/2.
:- dynamic available/2. 
:- dynamic has/3.


%%%%% SECTION: planner_philosophers
%%%%% This line loads the generic planner code from the file "planner.pl"
%%%%% Just ensure that that the planner.pl file is in the same directory as this one
%%%%% DO NOT EDIT THIS SECTION
:- [planner].


%%%%% SECTION: init_philosophers
%%%%% Loads the initial state from the file philosophersInit.pl
%%%%% HINT: You can create other files with other initial states to more easily test individual actions
%%%%%       To do so, just replace the line below with one loading in the file with your initial state
:- [philosophersInit].


%%%%% SECTION: goal_states_philosophers
%%%%% Below we define different goal states, each with a different ID
%%%%% HINT: It may be useful to define "easier" goals when starting your program or when debugging
%%%%%       You can do so by adding more goal states below
goal_state(1, S) :- waiting(p1, S).
goal_state(2, S) :- happy(p1, S).
goal_state(3, S) :- happy(p1, S), waiting(p2, S).
goal_state(4, S) :- happy(p1, S), happy(p2, S).
goal_state(5, S) :- happy(p1, S), happy(p2, S), happy(p3,S).

goal_state(6, S) :- eating(p1, S).
goal_state(7, S) :- thinking(p1, S).
goal_state(8, S) :- happy(p1, S), happy(p2, S), waiting(p3, S).


%%%%% SECTION: precondition_axioms_philosophers
%%%%% Write precondition axioms for all actions in your domain. Recall that to avoid
%%%%% potential problems with negation in Prolog, you should not start bodies of your
%%%%% rules with negated predicates. Make sure that all variables in a predicate 
%%%%% are instantiated by constants before you apply negation to the predicate that 
%%%%% mentions these variables.

%  pickUp: philosopher can pick up a fork if either their left or right fork is available + they are thinking
poss(pickUp(P, F), S) :- philosopher(P), fork(F), between(P, F, P2), available(F, S).

% putDown: can put down a fork if there is one in their hand
poss(putDown(P, F), S) :- philosopher(P), fork(F), has(P, F, S).

% tryToEat: try to eat if the philosopher has two different forks
poss(tryToEat(P), S) :- philosopher(P), fork(F1), has(P, F1, S), fork(F2), not F1 = F2, has(P, F2, S).
    % I think some of the conditions for the eating() axiom are related to this

%%%%% SECTION: successor_state_axioms_philosophers 
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

% available: when someone puts it down or if it wasn't picked up
available(F, [putDown(P, F) | S]).
available(F, [A | S]) :- not A = pickUp(P, F), available(F, S). 


% has: true when philosopher picks fork up or did not put it down
has(P, F, [pickUp(P, F) | S]).
has(P, F, [A | S]) :- not A = putDown(P, F), has(P, F, S). 


% thinking: true if they had one fork and put it down, or if they were already thinking and didnt pick up another fork
thinking(P, [putDown(P, F1) | S]) :- between(P, F2, P2), not F1 = F2, not has(P, F2, S). % they dont have the other fork

thinking(P, [A, S]) :- not A = pickUp(P, F), thinking(P, S).


% eating: can eat if they tried to eat, have two different forks, and < 2 philosophers are already eating
eating(P, [tryToEat(P) | S]) :- 
    not (philosopher(OtherP1), not OtherP1 = P, eating(OtherP1, S),
        philosopher(OtherP2), not OtherP2 = P, not OtherP2 = OtherP1, eating(OtherP2, S), write(' OtherP2: ')).

% continue eating if they did not put down a fork and they were already eating
eating(P, [A | S]) :- not A = putDown(P, F), eating(P, S).


% waiting: wait when they pick up a fork. 
waiting(P, [pickUp(P, F) | S]).

% Remain waiting if they didnt put down a fork + were waiting in previous situation 
waiting(P, [A | S]) :- not A = putDown(P, F), waiting(P, S). % this succeeds only for the case where P is currently thinking.

% or if they couldnt start to eat
waiting(P, [tryToEat(P) | S]) :- philosopher(P1), not P1 = P, eating(P1, S),
     philosopher(P2), not P2 = P, not P2 = P1, eating(P2, S). % if tryToEat succeeds, then they currently have 2 forks.


% happy: if they successfully eat and put down at least one fork
happy(P, [putDown(P, F) | S]) :- eating(P, S).
happy(P, [A | S]) :- happy(P, S). % remain happy no matter what


%%%%% SECTION: declarative_heuristics_philosophers
%%%%% The predicate useless(A,ListOfPastActions) is true if an action A is useless
%%%%% given the list of previously performed actions. The predicate useless(A,ListOfPastActions)
%%%%% helps to solve the planning problem by providing declarative heuristics to 
%%%%% the planner. If this predicate is correctly defined using a few rules, then it 
%%%%% helps to speed-up the search that your program is doing to find a list of actions
%%%%% that solves a planning problem. Write as many rules that define this predicate
%%%%% as you can: think about useless repetitions you would like to avoid, and about 
%%%%% order of execution (i.e., use common sense properties of the application domain). 
%%%%% Your rules have to be general enough to be applicable to any problem in your domain,
%%%%% in other words, they have to help in solving a planning problem for any instance
%%%%% (i.e., any initial and goal states).
%%%%%	
%%%%% write your rules implementing the predicate  useless(Action,History) here. %

% it is useless to put down something you just picked up
useless(putDown(P, F), [pickUp(P, F) | S]).

% it is useless to put down a fork before trying to eat
% useless(putDown(P, F), [A|S]) :- not happy(P, S), not A = tryToEat(P), useless(putDown(P, F), S).

% it is useless to pick up something you just put down
useless(pickUp(P, F), [putDown(P, F) | S]).

% it is useless to pick up a fork when the other fork is unavailable
% useless(pickUp(P1, F1), S) :- fork(F2), not F1 = F2, between(P1, F2, P2), not available(F2, S).

% it is useless to eat twice in a row
useless(tryToEat(P), [tryToEat(P) | S]).

% it is useless to pick up a fork if you are happy
useless(pickUp(P, F), S) :- happy(P, S).

% it is useless to not eat if you have two forks

