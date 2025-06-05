%%%%% SECTION: constants
orderNames([first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, 
            eleventh, twelfth, thirteenth, fourteenth, fifteenth, sixteenth, seventeenth, eighteenth, nineteenth, twentieth]).

currentYear(2023).

% album constants
albumArtist(out_of_control, honkai_star_rail).
albumArtist(of_snow_and_ember, honkai_star_rail).

albumYear(out_of_control, 2023).
albumYear(of_snow_and_ember, 2023).

albumGenre(out_of_control, techno).
albumGenre(out_of_control, gaming).

albumGenre(of_snow_and_ember, gaming).
albumGenre(of_snow_and_ember, acoustic).
albumGenre(of_snow_and_ember, techno).
albumGenre(of_snow_and_ember, rock).

% song constants
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
    song(frosty_trail, 100),
    song(order, 115),
    song(braving_the_cold, 119),
    song(sleep_tight, 109),
    song(dense_floe, 139),
    song(ghost_from_the_past, 77),
    song(crystal_tears, 130),
    song(eternal_freeze, 102),
    song(a_trap_with_no_return, 65),
    song(wildfire, 208)]
).

%%%%% SECTION: helpers

isSong(Song) :- trackList(Album, List), trackMember(song(Song, Length), List).

trackMember(song(Song, Length), [song(Song,Length)|T]).
trackMember(song(Song, Length), [song(Song2, Length2)|T]) :- trackMember(song(Song, Length), T).

songLength(Song, Length) :- trackList(Album, List), trackMember(song(Song, Length), List).

onAlbum(Song, Album) :- trackList(Album, List), trackMember(song(Song, Length), List).

albumLength(Album, Length) :- trackList(Album, List), trackLength(List, Length).

trackLength([], 0).
trackLength([song(Song, SongLength)|T], AlbumLength) :- trackLength(T, RestOfAlbumLength), AlbumLength is SongLength + RestOfAlbumLength.

atNamedIndex(List, IndexName, Element) :- orderNames(L), index(IndexName, L, Index), retrieveAt(Index, List, Element).
index(IndexName, [IndexName|T], 1).
index(IndexName, [H|T], Index) :- not IndexName = H, index(IndexName, T, TempIndex), Index is TempIndex + 1.
retrieveAt(1, [H|T], H).
retrieveAt(Index, [H|T], Element) :- NewIndex is Index - 1, retrieveAt(NewIndex, T, Element).

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


%%%%% SECTION: lexicon

% articles
article(the).
article(a).
article(an).
article(any).

% proper nouns
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
proper_noun(wildifre). proper_noun(208).

proper_noun(hades). proper_noun(pyre). proper_noun(darren_korb).
proper_noun(celeste). proper_noun(lena_raine). 
proper_noun(hollowknight). proper_noun(christopher_larkin).
proper_noun(black_flag). proper_noun(brian_tyler).
proper_noun(persona_3). proper_noun(persona_5). proper_noun(shoji_meguro).
proper_noun(plants_vs_zombies). proper_noun(laura_shigihara).
proper_noun(nirvana). proper_noun(nevermind).

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

% common_nouns
common_noun(song, X) :- isSong(X).
common_noun(track, X) :- isSong(X).
common_noun(album, X) :- trackList(X, SongList).
common_noun(record, X) :- trackList(X, SongList).
common_noun(artist, X) :- albumArtist(Album, X).
common_noun(release_year, X) :- albumYear(Album, X).
common_noun(genre, X) :- albumGenre(Album, X).
common_noun(tracklist, X) :- trackList(Album, X).
common_noun(length, X) :- songLength(Song, X).
common_noun(length, X) :- albumLength(Album, X).
common_noun(Number, Number) :- number(Number).

% adjectives
adjective(length, Length) :- songLength(Song, Length).
adjective(length, Length) :- albumLength(Album, Length).
adjective(short, Song) :- songLength(Song, Length), Length < 180.
adjective(short, Album) :- albumLength(Album, Length), Length < 600.
adjective(long, Song) :- songLength(Song, Length), Length > 360.
adjective(long, Album) :- albumLength(Album, Length), Length > 3600.
adjective(old, Song) :- albumYear(Album, Year), onAlbum(Song, Album), Year < 2000.
adjective(old, Album) :- albumYear(Album, Year), Year < 2000.
adjective(new, Song) :- albumYear(Album, Year), onAlbum(Song, Album), currentYear(Year).
adjective(new, Album) :- albumYear(Album, Year), currentYear(Year).
adjective(current, Year) :- currentYear(Year).
adjective(Year, Song) :- albumYear(Album, Year), onAlbum(Song, Album).
adjective(Year, Album) :- albumYear(Album, Year).
adjective(Genre, Song) :- albumGenre(Album, Genre), onAlbum(Song, Album).
adjective(Genre, Album) :- albumGenre(Album, Genre).
adjective(Artist, Song) :- albumArtist(Album, Artist), onAlbum(Song, Album).
adjective(Artist, Album) :- albumArtist(Album, Artist).
adjective(IndexName, Song) :- trackList(Album, SongList), atNamedIndex(SongList, IndexName, song(Song, Length)).

adjective(oldest, Album, Constraints) :- 
    callAlbumConstraints(Album, Constraints), albumYear(Album, Year), 
    not (callAlbumConstraints(Album2, Constraints), not Album = Album2, albumYear(Album2, Year2), Year2 < Year).

adjective(oldest, Song, Constraints) :- onAlbum(Song, Album),
    callAlbumConstraints(Album, Constraints), albumYear(Album, Year), 
    not (callAlbumConstraints(Album2, Constraints), not Album = Album2, albumYear(Album2, Year2), Year2 < Year).

% prepositions
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

what(Words, Ref) :- np(Words, Ref).

/* Noun phrase can be a proper name, a number, or can start with an article
   For now, the adjective "oldest" must always be the first adjective! */

np([Name], Name) :- proper_noun(Name).
np([Number], Number) :- number(Number).
np([Art, oldest|Rest], What) :- 
    article(Art), np2(Rest, What, Adjectives), adjective(oldest, What, Adjectives).
np([Art|Rest], What) :- article(Art), np2(Rest, What, Adjectives).

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