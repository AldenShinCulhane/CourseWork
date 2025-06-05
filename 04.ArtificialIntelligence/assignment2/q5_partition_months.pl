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

%%%%% SECTION: partitionMonths
%%%%% Predicate definition: partitionMonths(Months, Prices, Increase, Decrease)

% Base case if one month is left
recursivePartitionMonths([FirstMonth], [FirstPrice], [], []).

% Case when the next month's price is >= the previous month's price
recursivePartitionMonths([PrevMonth, CurrentMonth | RemainingMonths], [PrevPrice, CurrentPrice | RemainingPrices],
                         [CurrentMonth | IncreaseRest], Decrease) :-
    CurrentPrice >= PrevPrice,
    recursivePartitionMonths([CurrentMonth | RemainingMonths], [CurrentPrice | RemainingPrices], IncreaseRest, Decrease).

% Case when the next month's price is < the previous month's price
recursivePartitionMonths([PrevMonth, CurrentMonth | RemainingMonths], [PrevPrice, CurrentPrice | RemainingPrices],
                         Increase, [CurrentMonth | DecreaseRest]) :-
    CurrentPrice < PrevPrice,
    recursivePartitionMonths([CurrentMonth | RemainingMonths], [CurrentPrice | RemainingPrices], Increase, DecreaseRest).

% Initiating recursion, checks for the same list length and > 1 month and price
partitionMonths(Months, Prices, Increase, Decrease) :-
    length(Months, L), length(Prices, L), L > 1,
    recursivePartitionMonths(Months, Prices, Increase, Decrease).

/*
Testing:
partitionMonths([],
          [], Increase, Decrease).

partitionMonths([january],
          [56.50], Increase, Decrease).

partitionMonths([january, february],
          [56.50, 56.45], Increase, Decrease).

partitionMonths([january, february, march, april],
          [56.20, 56.05, 56.25, 56.50], Increase, Decrease).

partitionMonths([jan, feb, mar, apr, may, jun, jul],
          [7.90, 8.00, 8.15, 8.05, 8.04, 8.05, 8.5], [feb, mar, jun, jul], [apr, may]).

partitionMonths([january, february, march, april],
          [56.20, 56.25, 56.25, 56.50], Increase, Decrease).
*/