
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

%%%%% SECTION: constants
%%%%% You do not have to add anything to this section, but feel free to change the currentYear value to test your program

orderNames([first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, 
            eleventh, twelfth, thirteenth, fourteenth, fifteenth, sixteenth, seventeenth, eighteenth, nineteenth, twentytieth]).

currentYear(2023).

%%%%% SECTION: database
%%%%% Put statements for albumArtist, albumYear, albumGenre, and trackList below

albumArtist(out_of_control, honkai_star_rail).
albumArtist(of_snow_and_ember, honkai_star_rail).
albumArtist(hades, darren_korb).
albumArtist(celeste, lena_raine).
albumArtist(hollowknight, christopher_larkin).
albumArtist(black_flag, brian_tyler).
albumArtist(persona_5, shoji_meguro).
albumArtist(plants_vs_zombies, laura_shigihara).
albumArtist(persona_3, shoji_meguro).
albumArtist(pyre, darren_korb).
albumArtist(nevermind, nirvana).

albumYear(out_of_control, 2023).
albumYear(of_snow_and_ember, 2023).
albumYear(hades, 2018).
albumYear(celeste, 2018).
albumYear(hollowknight, 2017).
albumYear(black_flag, 2013).
albumYear(persona_5, 2017).
albumYear(plants_vs_zombies, 2010).
albumYear(persona_3, 2006).
albumYear(pyre, 2017).
albumYear(nevermind, 1991).

albumGenre(out_of_control, techno).
%albumGenre(out_of_control, pop).
albumGenre(out_of_control, gaming).

%albumGenre(of_snow_and_ember, pop).
albumGenre(of_snow_and_ember, gaming).
albumGenre(of_snow_and_ember, acoustic).
albumGenre(of_snow_and_ember, techno).
albumGenre(of_snow_and_ember, rock).

albumGenre(hades, gaming).
albumGenre(hades, metal).
albumGenre(hades, rock).

albumGenre(celeste, gaming).
albumGenre(celeste, edm).

albumGenre(hollowknight, gaming).
albumGenre(hollowknight, orchestral).
albumGenre(hollowknight, ambience).

albumGenre(black_flag, gaming).
albumGenre(black_flag, orchestral).

albumGenre(persona_5, gaming).
albumGenre(persona_5, jazz).
albumGenre(persona_5, funk).

albumGenre(plants_vs_zombies, gaming).
albumGenre(plants_vs_zombies, pop).

albumGenre(persona_3, gaming).
albumGenre(persona_3, rap).
albumGenre(persona_3, edm).

albumGenre(pyre, gaming).
albumGenre(pyre, instrumental).
albumGenre(pyre, hymn).
albumGenre(pyre, rock).

albumGenre(nevermind, grunge).

trackList(out_of_control, 
   [song(star_rail, 68),
    song(space_walk, 115),
    song(fReeStyLE, 86),
    song(call_of_the_stars, 34),
    song(science_fiction, 101),
    song(flashpoint, 74),
    song(dire_straits, 82),
    song(dawn_of_disaster, 104),
    song(timeline, 188)]
).

trackList(of_snow_and_ember,
   [song(embers, 82),
    song(hearthfire, 159),
    song(streets_abuzz, 75),
    song(faded_sun, 81),
    song(frosty_trail, 100),
    song(order, 115),
    song(braving_the_cold, 119),
    song(warm_sun, 89),
    song(sleep_tight, 109),
    song(silent, 78),
    song(cutting_mistral, 116),
    song(tempered_cord, 109),
    song(dense_floe, 139),
    song(traceless_drift, 124),
    song(ghost_from_the_past, 77),
    song(blaze, 78),
    song(crystal_tears, 130),
    song(eternal_freeze, 102),
    song(a_trap_with_no_return, 65),
    song(wildfire, 208)]
).

trackList(hades,
   [song(no_escape, 145),
    song(lament_of_orpheus, 193),
    song(the_unseen_ones, 258)]
).

trackList(celeste,
   [song(resurrections, 578),
    song(spirit_of_hospitality, 104),
    song(reflection, 354)]
).

trackList(hollowknight,
   [song(dirtmouth, 115),
    song(city_of_tears, 178),
    song(dung_defender, 127)]
).

trackList(black_flag,
   [song(on_the_horizon, 181),
    song(fare_thee_well, 314),
    song(take_what_is_ours, 195)]
).

trackList(persona_5,
   [song(life_will_change, 262),
    song(last_surprise, 235),
    song(a_woman, 131)]
).

trackList(persona_3,
   [song(blues_in_the_velvet_room, 195),
    song(master_of_tartarus, 210),
    song(burn_my_dread, 95)]
).

trackList(plants_vs_zombies,
   [song(loonboon, 107),
    song(brainiac_maniac, 102),
    song(uraniwa_ni_zombies_ga, 159)]
).

trackList(pyre,
   [song(mourning_song, 72),
    song(thrash_pack, 234),
    song(vagrant_song, 155)]
).

trackList(nevermind,
   [song(in_bloom, 255),
    song(lithium, 257)]
).

%%%%% SECTION: helpers
%%%%% Add the predicates isSong(Song), songLength(Song, Length), onAlbum(Song, Album), albumLength(Album, Length), and atNamedIndex(List, Entry, Element)
%%%%% Another other helper predicates you wish to add for your lexicon or the parser should be added here

isSong(Song) :- trackList(Album, List), trackMember(song(Song, Length), List).

trackMember(song(Song, Length), [song(Song,Length)|T]).
trackMember(song(Song, Length), [song(Song2, Length2)|T]) :- trackMember(song(Song, Length), T).

songLength(Song, Length) :- trackList(Album, List), trackMember(song(Song, Length), List).

onAlbum(Song, Album) :- trackList(Album, List), trackMember(song(Song, Length), List).

albumLength(Album, Length) :- trackList(Album, List), trackLength(List, Length).

trackLength([], 0).
trackLength([song(Song, SongLength)|T], AlbumLength) :- trackLength(T, RestOfAlbumLength), AlbumLength is SongLength + RestOfAlbumLength.

%atNamedIndex([Element|T], first, Element).
%atNamedIndex([H1, sElement|T], second, Element).
%atNamedIndex([H1, H2, Element|T], third, Element).
%atNamedIndex([H1, H2, H3, Element|T], fourth, Element).
%atNamedIndex([H1, H2, H3, H4, Element|T], fifth, Element).
%atNamedIndex([H1, H2, H3, H4, H5, Element|T], sixth, Element).
%atNamedIndex([H1, H2, H3, H4, H5, H6, Element|T], seventh, Element).
%atNamedIndex([H1, H2, H3, H4, H5, H6, H7, Element|T], eighth, Element).
%atNamedIndex([H1, H2, H3, H4, H5, H6, H7, H8, Element|T], ninth, Element).
%atNamedIndex([H1, H2, H3, H4, H5, H6, H7, H8, H9, Element|T], tenth, Element).
%atNamedIndex([H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, Element|T], eleventh, Element).
%atNamedIndex([H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, H11, Element|T], twelfth, Element).
%atNamedIndex([H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, H11, H12, Element|T], thirteenth, Element).
%atNamedIndex([H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, H11, H12, H13, Element|T], fourteenth, Element).
%atNamedIndex([H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, H11, H12, H13, H14, Element|T], fifteenth, Element).
%atNamedIndex([H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, H11, H12, H13, H14, H15, Element|T], sixteenth, Element).
%atNamedIndex([H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, H11, H12, H13, H14, H15, H16, Element|T], seventeenth, Element).
%atNamedIndex([H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, H11, H12, H13, H14, H15, H16, H17, Element|T], eighteenth, Element).
%atNamedIndex([H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, H11, H12, H13, H14, H15, H16, H17, H18, Element|T], nineteenth, Element).
%atNamedIndex([H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, H11, H12, H13, H14, H15, H16, H17, H18, H19, Element|T], twentytieth, Element).
%%%%%%%%%%%%%%%%%%%%%%
atNamedIndex(List, IndexName, Element) :- orderNames(L), index(IndexName, L, Index), retrieveAt(Index, List, Element).
index(IndexName, [IndexName|T], 1).
index(IndexName, [H|T], Index) :- not IndexName = H, index(IndexName, T, TempIndex), Index is TempIndex + 1.
retrieveAt(1, [H|T], H).
retrieveAt(Index, [H|T], Element) :- Index > 1, NewIndex is Index - 1, retrieveAt(NewIndex, T, Element).


%%%%% SECTION: articles
%%%%% Put the rules/statements defining the proper_nouns below

article(the).
article(a).
article(an).
article(any).

%%%%% SECTION: proper_nouns
%%%%% Put the rules/statements defining the proper_nouns below

proper_noun(honkai_star_rail).
proper_noun(out_of_control). proper_noun(2023).
proper_noun(of_snow_and_ember). %proper_noun(2023). Already in KB!

proper_noun(star_rail). proper_noun(68).
proper_noun(space_walk). proper_noun(115).
proper_noun(fReeStyLE). proper_noun(86).
proper_noun(call_of_the_stars). proper_noun(34).
proper_noun(science_fiction). proper_noun(101).
proper_noun(flashpoint). proper_noun(74).
proper_noun(dire_straits). proper_noun(82).
proper_noun(dawn_of_disaster). proper_noun(104).
proper_noun(timeline). proper_noun(188).

proper_noun(embers). %proper_noun(82). Already in KB!
proper_noun(hearthfire). proper_noun(159).
proper_noun(streets_abuzz). proper_noun(75).
proper_noun(forsty_trail). proper_noun(100).
proper_noun(order). %proper_noun(115). Already in KB!
proper_noun(braving_the_cold). proper_noun(119).
proper_noun(sleep_tight). proper_noun(109).
proper_noun(dense_floe). proper_noun(139).
proper_noun(ghost_from_the_past). proper_noun(77).
proper_noun(crystal_tears). proper_noun(130).
proper_noun(eternal_freeze). proper_noun(102).
proper_noun(a_trap_with_no_return). proper_noun(65).
proper_noun(wildfire). proper_noun(208).

proper_noun(hades). proper_noun(pyre). proper_noun(darren_korb). proper_noun(2018).
proper_noun(celeste). proper_noun(lena_raine). 
proper_noun(hollowknight). proper_noun(christopher_larkin). proper_noun(2017).
proper_noun(black_flag). proper_noun(brian_tyler). proper_noun(2013).
proper_noun(persona_3). proper_noun(persona_5). proper_noun(shoji_meguro). proper_noun(2006).
proper_noun(plants_vs_zombies). proper_noun(laura_shigihara). proper_noun(2010).
proper_noun(nirvana). proper_noun(nevermind). proper_noun(2017).

proper_noun(no_escape). proper_noun(145).
proper_noun(lament_of_orpheus). proper_noun(193).
proper_noun(the_unseen_ones). proper_noun(258).

proper_noun(resurrections). proper_noun(578).
proper_noun(spirit_of_hospitality). proper_noun(104).
proper_noun(reflection). proper_noun(354).

proper_noun(dirtmouth). proper_noun(115).
proper_noun(city_of_tears). proper_noun(178).
proper_noun(dung_defender). proper_noun(127).

proper_noun(on_the_horizon). proper_noun(181).
proper_noun(fare_thee_well). proper_noun(314).
proper_noun(take_what_is_ours). proper_noun(195).

proper_noun(life_will_change). proper_noun(262).
proper_noun(last_surprise). proper_noun(235).
proper_noun(a_woman). proper_noun(131).

proper_noun(blues_in_the_velvet_room). % in KB: proper_noun(195).
proper_noun(master_of_tartarus). proper_noun(210).
proper_noun(burn_my_dread). proper_noun(95).

proper_noun(loonboon). proper_noun(107).
proper_noun(brainiac_maniac). % in KB: proper_noun(102).
proper_noun(uraniwa_ni_zombies_ga). % in KB: proper_noun(159).

proper_noun(mourning_song). proper_noun(72).
proper_noun(thrash_pack). proper_noun(234).
proper_noun(vagrant_song). proper_noun(155).

proper_noun(in_bloom). proper_noun(255).
proper_noun(lithium). proper_noun(257).

%%%%% SECTION: common_nouns
%%%%% Put the rules/statements defining the common_nouns below

common_noun(song, X) :- isSong(X).
common_noun(album, X) :- trackList(X, SongList).
common_noun(artist, X) :- albumArtist(Album, X).
common_noun(year, X) :- albumYear(Album, X).
common_noun(genre, X) :- albumGenre(Album, X).
common_noun(tracklist, X) :- trackList(Album, X).
common_noun(length, X) :- songLength(Song, X).
common_noun(length, X) :- albumLength(Album, X).
common_noun(record, X) :- trackList(X, SongList).
common_noun(track, X) :- isSong(X).
common_noun(release_year, X) :- albumYear(Album, X).

%%%%% SECTION: adjectives
%%%%% Put the rules/statements defining the adjectives below

adjective(current, X) :- currentYear(X).
adjective(Year, Song) :- albumYear(Album, Year), onAlbum(Song, Album).
adjective(Year, Album) :- albumYear(Album, Year).
adjective(Genre, Song) :- albumGenre(Album, Genre), onAlbum(Song, Album).
adjective(Genre, Album) :- albumGenre(Album, Genre).
adjective(IndexName, Song) :- trackList(Album, SongList), atNamedIndex(SongList, IndexName, song(Song, Length)).
adjective(short, Song) :- songLength(Song, Length), Length < 180. % less than 3 minutes long
adjective(short, Album) :- albumLength(Album, Length), Length < 600. % less than 10 minutes long
adjective(long, Song) :- songLength(Song, Length), Length >= 360. % at least 6 minutes long
adjective(long, Album) :- albumLength(Album, Length), Length >= 3600. % at least 1 hour long
adjective(old, Song) :- albumYear(Album, Year), Year < 2000, onAlbum(Song, Album).
adjective(old, Album) :- albumYear(Album, Year), Year < 2000.
adjective(new, Song) :- currentYear(Current), albumYear(Album, Current), onAlbum(Song, Album).
adjective(new, Album) :- currentYear(Current), albumYear(Album, Current).
adjective(Artist, Album) :- albumArtist(Album, Artist).
adjective(Artist, Song) :- albumArtist(Album, Artist), onAlbum(Song, Album).

% oldest works by processing the rest of the phrase and compares the results to each other. We're aiming to compare a subset of "valid" albums
adjective(oldest, Album, Constraints) :- 
    callAlbumConstraints(Album, Constraints), albumYear(Album, Year), 
    not (callAlbumConstraints(Album2, Constraints), not Album = Album2, albumYear(Album2, Year2), Year2 < Year).

adjective(oldest, Song, Constraints) :- onAlbum(Song, Album),
    callAlbumConstraints(Album, Constraints), albumYear(Album, Year), 
    not (callAlbumConstraints(Album2, Constraints), not Album = Album2, albumYear(Album2, Year2), Year2 < Year).

callAlbumConstraints(Album, []) :- albumYear(Album, Y).
callAlbumConstraints(Album, [C|Rest]) :- albumArtist(Album, C), callAlbumConstraints(Album, Rest).
callAlbumConstraints(Album, [C|Rest]) :- albumGenre(Album, C), callAlbumConstraints(Album, Rest).
callAlbumConstraints(Album, [C|Rest]) :- albumYear(Album, C), callAlbumConstraints(Album, Rest).
callAlbumConstraints(Album, [C|Rest]) :- albumLength(Album, C), callAlbumConstraints(Album, Rest).
callAlbumConstraints(Album, [short|Rest]) :- albumLength(Album, Length), Length < 600, callAlbumConstraints(Album, Rest).
callAlbumConstraints(Album, [long|Rest]) :- albumLength(Album, Length), Length > 3600, callAlbumConstraints(Album, Rest).
callAlbumConstraints(Album, [L|Rest]) :- is_a_list(L), callAlbumConstraints(Album, L), callAlbumConstraints(Album, Rest).

is_a_list([E]).
is_a_list([H|T]).
% for debugging:
% adjective(oldest, Album, Phrase) :- np2(Phrase, Album), albumYear(Album, Year), write(Album), write(' '), write(Year), write(' '), not (np2(Phrase, Album2), albumYear(Album2, Year2), write(Album2), write(' '), write(Year2), nl, not Album = Album2, Year2 < Year).

%%%%% SECTION: prepositions
%%%%% Put the rules/statements defining the prepositions below

preposition(of, Artist, Song) :- albumArtist(Album, Artist), onAlbum(Song, Album).
preposition(of, Artist, Album) :- albumArtist(Album, Artist).
preposition(from, Song, Artist) :- albumArtist(Album, Artist), onAlbum(Song, Album).
preposition(from, Album, Artist) :- albumArtist(Album, Artist).
preposition(by, Song, Artist) :- albumArtist(Album, Artist), onAlbum(Song, Album).
preposition(by, Album, Artist) :- albumArtist(Album, Artist).

preposition(from, Song, Year) :- albumYear(Album, Year), onAlbum(Song, Album).
preposition(from, Album, Year) :- albumYear(Album, Year).
preposition(released_in, Song, Year) :- albumYear(Album, Year), onAlbum(Song, Album).
preposition(released_in, Album, Year) :- albumYear(Album, Year).
preposition(with, Song, Year) :- albumYear(Album, Year), onAlbum(Song, Album).
preposition(with, Album, Year) :- albumYear(Album, Year).

preposition(released_before, Song, YearConstraint) :- albumYear(Album, Year), onAlbum(Song, Album), Year < YearConstraint.
preposition(released_before, Album, YearConstraint) :- albumYear(Album, Year), Year < YearConstraint.
preposition(released_before, Song1, Song2) :- not Song1 = Song2,
    albumYear(Album1, Year1), onAlbum(Song1, Album1),
    albumYear(Album2, Year2), onAlbum(Song2, Album2),
    Year1 < Year2.
preposition(released_before, Album, Song) :-
    albumYear(Album, Year1), albumYear(Album2, Year2),
    not Album1 = Album2, onAlbum(Song, Album2), Year1 < Year2.
preposition(released_before, Song, Album) :-
    albumYear(Album1, Year1), onAlbum(Song, Album1),
    albumYear(Album, Year2),  not Album = Album1,
    Year1 < Year2.
preposition(released_before, Album1, Album2) :- not Album1 = Album2, albumYear(Album1, Year1), albumYear(Album2, Year2), Year1 < Year2.

preposition(released_after, Song, YearConstraint) :- albumYear(Album, Year), onAlbum(Song, Album), Year > YearConstraint.
preposition(released_after, Album, YearConstraint) :- albumYear(Album, Year), Year > YearConstraint.
preposition(released_after, Song1, Song2) :- not Song1 = Song2,
    albumYear(Album1, Year1), onAlbum(Song1, Album1),
    albumYear(Album2, Year2), onAlbum(Song2, Album2),
    Year1 > Year2.
preposition(released_after, Album, Song) :-
    albumYear(Album, Year1), albumYear(Album2, Year2),
    not Album1 = Album2, onAlbum(Song, Album2), Year1 > Year2.
preposition(released_after, Song, Album) :-
    albumYear(Album1, Year1), onAlbum(Song, Album1),
    albumYear(Album, Year2),  not Album = Album1,
    Year1 > Year2.
preposition(released_after, Album1, Album2) :- not Album1 = Album2, albumYear(Album1, Year1), albumYear(Album2, Year2), Year1 > Year2.
%preposition(of, Year, Year) :- number(Year). Already included from prepositoin(of, Length, Length)

preposition(of, Genre, Song) :- albumGenre(Album, Genre), onAlbum(Song, Album).
preposition(of, Genre, Album) :- albumGenre(Album, Genre).
preposition(of, Song, Genre) :- albumGenre(Album, Genre), onAlbum(Song, Album).
preposition(of, Album, Genre) :- albumGenre(Album, Genre).
preposition(with, Song, Genre) :- albumGenre(Album, Genre), onAlbum(Song, Album).
preposition(with, Album, Genre) :- albumGenre(Album, Genre).

preposition(of, Length, Song) :- songLength(Song, Length).
preposition(of, Length, Album) :- albumLength(Album, Length).
preposition(of, Song, Length) :- songLength(Song, Length).
preposition(of, Album, Length) :- albumLength(Album, Length).
preposition(with, Song, Length) :- songLength(Song, Length).
preposition(with, Album, Length) :- albumLength(Album, Length).
preposition(of, Length, Length) :- number(Length).
preposition(with, Length, Length) :- number(Length).

preposition(in, Song, Album) :- onAlbum(Song, Album).
preposition(on, Song, Album) :- onAlbum(Song, Album).
preposition(from, Song, Album) :- onAlbum(Song, Album).
preposition(with, Album, Song) :- onAlbum(Song, Album).

preposition(between, Number, Min, Max) :-
    number(Number), number(Min), number(Max), Max >= Min, Number >= Min, Number =< Max.
preposition(between, Number, Max, Min) :-
    number(Number), number(Min), number(Max), Max >= Min, Number >= Min, Number =< Max.

%%%%% SECTION: PARSER
%%%%% For testing your lexicon for question 3, we will use the default parser initially given to you.
%%%%% For testing your answers for question 4, we will use your parser below

what(Words, Ref) :- np(Words, Ref).

/* Noun phrase can be a proper name or can start with an article */

% helper predicate
is_member(E, [E | T]).
is_member(E, [H | T]) :- is_member(E, T).

np([Name], Name) :- proper_noun(Name).
np([Number], Number) :- number(Number).
np([Art, oldest|Rest], What) :- 
    article(Art), np2(Rest, What, Adjectives), adjective(oldest, What, Adjectives).
np([Art|Rest], What) :- article(Art), np2(Rest, What, Adjectives).
% np([Art|Rest], What) :- article(Art), np2([oldest | Rest], What).

/* If a noun phrase starts with an article, then it must be followed
   by another noun phrase that starts either with an adjective
   or with a common noun. */


np2([Adj|Rest], What, [Adj|Adjectives]) :- adjective(Adj,What), not Adj = oldest, np2(Rest, What, Adjectives).
np2([Noun|Rest], What, Mods) :- common_noun(Noun, What), mods(Rest, What, Mods).

/* Modifier(s) provide an additional specific info about nouns.
   Modifier can be a prepositional phrase followed by none, one or more
   additional modifiers.  */

mods([], _, []).
mods(Words, What, [Mod|Mods]) :-
	appendLists(Start, Rest, Words),
	prepPhrase(Start, What, Mod), mods(Rest, What, Mods).

prepPhrase([Prep|Rest], What, Mod) :-
	np(Rest, Mod), preposition(Prep, What, Mod).

prepPhrase([between|Rest], What, []) :-
    append(Number1, [and|Number2], Rest),
	np(Number1, Ref1), np(Number2, Ref2), preposition(between, What, Ref1, Ref2).

appendLists([], L, L).
appendLists([H|L1], L2, [H|L3]) :-  appendLists(L1, L2, L3).