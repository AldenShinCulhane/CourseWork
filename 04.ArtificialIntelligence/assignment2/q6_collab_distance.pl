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

%% To help you test your collabDistance, you may put your knowledge base below. Do not put it under the colladDistance
%% section. You may also delete your test knowledge based before submission, as we will not be marking it

%%%%% SECTION: articleAuthor
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

%%%%% SECTION: collabDistance
%%%%% Predicate definition: collabDist(Author1, Author2, MaxDist, Authors, Articles)

% base case for the same author where we simply return the author and na article they worked on
collabDist(Author1, Author1, MaxDist, [Author1], [Article]) :-
    MaxDist >= 0, articleAuthor(Article, Author1).

% base case for direct collaborations, and end of the list where: 
% the last two authors to collaborate are placed and the related article is added
collabDist(Author1, Author2, MaxDist, [Author1, Author2], [Article]) :-
    MaxDist >= 1, 
    articleAuthor(Article, Author1), 
    articleAuthor(Article, Author2), 
    not(Author1 = Author2).

% recursive case for indirect collaborations where Author1 and Article are not within ListAu and ListAr respectively
collabDist(Author1, Author2, MaxDist, [Author1 | ListAu], [Article | ListAr]) :-
    MaxDist > 1, articleAuthor(Article, Author1), articleAuthor(Article, IntermediateAuthor),
    not IntermediateAuthor = Author1, not IntermediateAuthor = Author2, 
    NewMaxDist is MaxDist - 1,
    collabDist(IntermediateAuthor, Author2, NewMaxDist, ListAu, ListAr),
    not member(Author1, ListAu),
    not member(Article, ListAr).

/*
Accounting for cases where an Author is part of the list, or an Article is part of the list, is unnecessary 
since if the author/article is already in the list, then there is an unnecessary loop.

For example: (Clare -> Riordan -> Bob -> Riordan -> Togelius) is the same as (Clare -> Riordan -> Togelius)

Another example: (ultimate_collab_the_sequel -> ultimate_collab -> ultimate_collab_the_sequel -> the_2009_mario_ai_competition)
                 is the same as (ultimate_collab_the_sequel -> the_2009_mario_ai_competition)
*/