dependent(ivan_late, train_strike).
dependent(chan_late, train_strike).
dependent(chan_late, chan_overslept).

p([chan_overslept], 0.1).
p([train_strike], 0.2).


p([ivan_late|Events], 0.3) :- member(tru(chan_overslept, P1), Events), member(tru(train_strike, P2), Events).

p([ivan_late, tru(chan_overslept, P1), tru(train_strike, P2)], 0.3).
p([ivan_late, tru(chan_overslept, P1), fals(train_strike, P2)], 0.2).
p([ivan_late, fals(chan_overslept, P1), tru(train_strike, P2)], 0.2).
p([ivan_late, fals(chan_overslept, P1), fals(train_strike, P2)], 0.1).

p([chan_late, tru(chan_overslept, P1), tru(train_strike, P2)], 0.3).
p([chan_late, tru(chan_overslept, P1), fals(train_strike, P2)], 0.2).
p([chan_late, fals(chan_overslept, P1), tru(train_strike, P2)], 0.2).
p([chan_late, fals(chan_overslept, P1), fals(train_strike, P2)], 0.1).

tru(Event, Prob) :- p(Event, Prob).

fals(Event, FalsProb) :- p(Event, Prob), FalsProb is 1 - Prob.

solve(Query, Facts, Prob) :- Query = tru(Event, EProb).