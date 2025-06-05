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

%%%%% SECTION: cost(Product, Cost)
% Put your atomic statements for the cost in this section
cost(laptop, 60).
cost(monitor, 200).
cost(keyboard, 300).

%%%%% SECTION: numPurchased(Product, Count)
% Put your atomic statements for the numPurchased predicate in this section
numPurchased(laptop, 1).
numPurchased(monitor, 1).
numPurchased(keyboard, 1).

%%%%% SECTION: shippingCost(Product, Cost)
% Put your atomic statements for the shippingCost predicate in this section
shippingCost(laptop, 6).
shippingCost(monitor, 20).
shippingCost(keyboard, 30).

%%%% SECTION: taxRate(Rate)
% Put your atomic statements for the taxRate predicate in this section
taxRate(0.13).

%%%%% SECTION: freeRegularShippingMin(Amount)
% Put your atomic statements for the freeRegularShippingMin predicate in this section
freeRegularShippingMin(300).

%%%%% SECTION: freeExpressShippingMin(Amount)
% Put your atomic statements for the freeExpressShippingMin predicate in this section
freeExpressShippingMin(700).

%%%%% SECTION: subtotal(Sub)
% Put your rules for the subtotal predicate in this section
subtotal(Sub) :-
    cost(laptop, LaptopCost), numPurchased(laptop, LaptopNumber),
    cost(monitor, MonitorCost), numPurchased(monitor, MonitorNumber),
    cost(keyboard, KeyboardCost), numPurchased(keyboard, KeyboardNumber),
    Sub is (LaptopCost * LaptopNumber) + (MonitorCost * MonitorNumber) + (KeyboardCost * KeyboardNumber).

%%%%% SECTION: calculateBaseShipping(ShippingCost)
% Put your rules for the calculateBaseShipping predicate in this section
% only include cost of shipping products, does not account for free shipping
calculateBaseShipping(ShippingCost) :- 
    numPurchased(laptop, LNum), numPurchased(monitor, MNum), numPurchased(keyboard, KNum),
    shippingCost(laptop, LShip), shippingCost(monitor, Mship), shippingCost(keyboard, KShip),
    ShippingCost is (LNum * LShip) + (MNum * Mship) + (KNum * KShip).

%%%%% SECTION: calculateShipping(ShippingType, ShippingCost)
% Put your rules for the calculateShipping predicate in this section
calculateShipping(regular, ShippingCost) :-
    subtotal(Sub),
    freeRegularShippingMin(Amount),
    Sub >= Amount,
    ShippingCost = 0.

calculateShipping(regular, ShippingCost) :-
    subtotal(Sub),
    freeRegularShippingMin(Amount),
    Sub < Amount,
    calculateBaseShipping(ShippingCost).

calculateShipping(express, ShippingCost) :-
    subtotal(Sub),
    freeExpressShippingMin(Amount),
    Sub >= Amount,
    ShippingCost = 0.

calculateShipping(express, ShippingCost) :-
    subtotal(Sub),
    freeExpressShippingMin(Amount),
    Sub < Amount,
    calculateBaseShipping(BaseCost),
    ShippingCost is 1.5 * BaseCost.

%%%%% SECTION: totalCost(ShippingType, Cost)
% Put your rules for the totalCost predicate in this section
totalCost(ShippingType, Cost) :-
    subtotal(Sub), calculateShipping(ShippingType, ShippingCost),
    taxRate(Rate), TotalTax is (Sub + ShippingCost) * Rate,
    Cost is Sub + ShippingCost + TotalTax.
