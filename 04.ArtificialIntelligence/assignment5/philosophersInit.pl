%%%%% Do NOT copy this file into philosophers.pl
%%%%% Feel free to make new initial states for testing/debugging purposes
%%%%% However, we suggest to do so, you should create a new file and load it 
%%%%% instead of this one in philosophers.pl
%%%%% DO NOT SUBMIT THIS FILE

% --- 3 philosophers ---

philosopher(p1).	philosopher(p2).	philosopher(p3).
left(p1,p2).	left(p2,p3).	left(p3,p1).
right(p1,p3).	right(p3,p2).	right(p2,p1).
fork(f1).	fork(f2).	fork(f3).
/*
                p2
               /  \
              f1  f2
             /     \
           p1--f3--p3
*/           
between(p1,f1,p2).	between(p2,f2,p3).	between(p3,f3,p1).
between(p2,f1,p1).	between(p3,f2,p2).	between(p1,f3,p3).

thinking(p1, []).	thinking(p2, []).	thinking(p3, []).
available(f1, []).	available(f2, []).	available(f3, []).


% --- 4 philosophers ---

% philosopher(p1).	philosopher(p2).	philosopher(p3). philosopher(p4).
% left(p1,p2).	left(p2,p3).	left(p3,p4). left(p4,p1).
% right(p1,p4).	right(p4, p3). right(p3,p2).	right(p2,p1).
% fork(f1).	fork(f2).	fork(f3). fork(f4).
% /*
%            p2--f2--p3
%             |       |
%            f1      f3
%             |       |
%            p1--f4--p4
% */           
% between(p1,f1,p2).	between(p2,f2,p3).	between(p3,f3,p4). between(p4,f4,p1).
% between(p2,f1,p1).	between(p3,f2,p2).	between(p4,f3,p3). between(p1,f4,p4).

% thinking(p1, []).	thinking(p2, []).	thinking(p3, []). thinking(p4, []).
% available(f1, []).	available(f2, []).	available(f3, []). available(f4, []).


% --- 7 philosophers ---
% philosopher(p1).	philosopher(p2).	philosopher(p3).  philosopher(p4). philosopher(p5). philosopher(p6). philosopher(p7).
% left(p1, p2). left(p2,p3).	left(p3,p4). left(p4, p5). left(p5, p6). left(p6, p7). left(p7, p1).
% right(p2, p1). right(p3, p2). right(p4, p3). right(p5, p4). right(p6, p5). right(p7, p6). right(p1, p7).
% fork(f1).	fork(f2).	fork(f3). fork(f4). fork(f5).	fork(f6).	fork(f7). 

% /*
%            p1 -- f1 -- p2 -- f2 -- p3 -- f3 -- p4
%             |                                   |
%            f7                                  f4
%             |                                   |
%            p7 -- f6 -- p6 -- f5 -- p5-----------
% */           

% between(p1,f1,p2).	between(p2,f2,p3).	between(p3,f3,p4). between(p4,f4,p5). between(p5,f5,p6). between(p6,f6,p7). between(p7,f7,p1).
% between(p2,f1,p1).	between(p3,f2,p2).	between(p4,f3,p3). between(p5,f4,p4). between(p6,f5,p5). between(p7,f6,p6). between(p1,f7,p7).
% thinking(p1, []).	thinking(p2, []).	thinking(p3, []). thinking(p4, []). thinking(p5, []). thinking(p6, []). thinking(p7, []).
% available(f1, []).	available(f2, []).	available(f3, []). available(f4, []). available(f5, []). available(f6, []). available(f7, []).