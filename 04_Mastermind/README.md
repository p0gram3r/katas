# Kata 4 - Mastermind

Mastermind or Master Mind is a code-breaking game for two players.

For a detailed introduction, see http://en.wikipedia.org/wiki/Mastermind_(board_game).


## Goal

The goal of this kata is to create a PatternValidator, which compares a secret pattern provided by the 
_codemaker_ to the provided by the _codebreaker_. The validator must returns a list of key pegs based on the
following rules:
- A black key peg is placed for each code peg from the guess which is correct in both color and position
- A white key peg indicates the existence of a correct color code peg placed in the wrong position

To create a code pattern you can choose from 6 colors: RED, YELLOW, GREEN, BLUE, ORANGE, BROWN. For convenience
these colors may be represented by numbers, e.g. 0 = RED, 1 = YELLOW and so on.


### Level 1

Implement a validator that adheres to the rules described above. You can assume that both patterns consist of 4 pegs,
at which each peg has a different color and there are no duplicates.


### Level 2

Same as level 1, plus: The guess pattern provided by the _codebreaker_'s may contain two or more pegs of the same color.


### Level 3

Same as level 2, plus: The secret pattern provided by the _codemakers_ may contain two or more pegs of the same color.


### Level 4

Same as level 3, plus: The number of colored pegs in a pattern rises from 4 to 5 and the number of available
colors is increased to 8 (by adding PINK and CYAN)
