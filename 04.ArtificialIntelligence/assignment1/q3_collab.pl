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

%%%%% SECTION: articleAuthor
% Put your atomic statements for the articleAuthor predicate in this section
articleAuthor(the_lightning_thief, rick_riordan).
articleAuthor(the_city_of_bones, cassandra_clare).
articleAuthor(ultimate_collab, cassandra_clare).
articleAuthor(ultimate_collab, rick_riordan).
articleAuthor(ultimate_collab_the_sequel, rick_riordan).
articleAuthor(ultimate_collab_the_sequel, jennifer).
articleAuthor(ultimate_collab_the_sequel, julian_togelius).
articleAuthor(the_2009_mario_ai_competition, julian_togelius).
articleAuthor(the_2009_mario_ai_competition, sergey_karakovskiy).
articleAuthor(the_2009_mario_ai_competition, robin_baumgarten).
articleAuthor(lonely_article, lonely_person).
articleAuthor(a_real_article, real_person).
articleAuthor(a_real_article, jerry_boris).
articleAuthor(a_real_article, joe_bob).
articleAuthor(a_real_article, rick_riordan).
articleAuthor(the_ratterday, rat_king).
articleAuthor(the_ratterday, giant_rat).
articleAuthor(the_ratterday, jerry_boris).


%%%%% SECTION: articleTopic
% Put your atomic statements for the articleTopic predicate in this section
articleTopic(the_lightning_thief, mythology).
articleTopic(the_city_of_bones, angels_and_demons).
articleTopic(the_2009_mario_ai_competition, ai).
articleTopic(ultimate_collab, collaboration).
articleTopic(ultimate_collab, books).
articleTopic(ultimate_collab_the_sequel, collaboration).
articleTopic(ultimate_collab_the_sequel, writing).
articleTopic(ultimate_collab_the_sequel, ai).
articleTopic(a_real_article, articles).
articleTopic(a_real_article, realism).
articleTopic(the_ratterday, rats_in_politics).
articleTopic(the_ratterday, rats).

%%%%% SECTION: collabDist
% Put your rules for collabDist in this section

% base case for the same author
collabDist(Author, Author, MaxDist) :-
    MaxDist >= 0, articleAuthor(X, Author).

% base case for direct collaborations
collabDist(Author1, Author2, MaxDist) :-
    MaxDist >= 1, articleAuthor(Article, Author1), articleAuthor(Article, Author2), not(Author1 = Author2).

% recursive case for indirect collaborations
collabDist(Author1, Author2, MaxDist) :-
    MaxDist > 1, articleAuthor(Article, Author1), articleAuthor(Article, IntermediateAuthor),
    not(IntermediateAuthor = Author1), NewMaxDist is MaxDist - 1,
    collabDist(IntermediateAuthor, Author2, NewMaxDist).

%%%% SECTION: collabDistWithAI
% Put your rules for collabDistWithAI in this section

% Case 1 where Author1 has at_least_one article on AI
collabDistWithAI(Author1, Author2, MaxDist, at_least_one) :- 
    articleAuthor(X, Author1), articleTopic(X, ai),
    collabDist(Author1, Author2, MaxDist).

% Case 2 where Author2 has at_least_one article on AI
collabDistWithAI(Author1, Author2, MaxDist, at_least_one) :- 
    articleAuthor(X, Author2), articleTopic(X, ai),
    collabDist(Author1, Author2, MaxDist).

% Case 3 where none of the authors have an article on AI
collabDistWithAI(Author1, Author2, MaxDist, none) :- 
    not (articleAuthor(X, Author1), articleTopic(X, ai)),
    not (articleAuthor(Y, Author2), articleTopic(Y, ai)),
    collabDist(Author1, Author2, MaxDist).
