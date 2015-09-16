# coding dojo - Mastermind

Mastermind or Master Mind is a code-breaking game for two players.

For a detailed introduction, check out the entry on [wikipedia](http://en.wikipedia.org/wiki/Mastermind_%28board_game%29).


## Goal

The goal of this kata is to create a PatternValidator, which compares a secret pattern provided by the 
_codemaker_ to the pattern provided by the _codebreaker_. The validator must return a list of key pegs based on the
following rules:
- A black key peg is added for each code peg which is correct in both color and position
- A white key peg is added for each code peg which is correct in color but which is placed in the wrong position

To not give too many hints about the secret pattern, the validator result should always list all black pegs before all
white pegs. 

To create a pattern, both players can choose pegs from 6 colors: RED, YELLOW, GREEN, BLUE, ORANGE, BROWN. 
For convenience, these colors may be represented by numbers in this kata, e.g. 0 = RED, 1 = YELLOW and so on.


## Level 1

Implement a validator that adheres to the rules described above. You can assume that both patterns consist of 4 pegs,
at which each peg has a different color and there are no color duplicates within one pattern.


## Level 2

Same as level 1, plus: The guess pattern provided by the _codebreaker_ may contain two or more pegs of the same color.


## Level 3

Same as level 2, plus: The secret pattern provided by the _codemakers_ may contain two or more pegs of the same color.


## Level 4

Same as level 3, plus: The number of colored pegs in a pattern increases from 4 to 5 and the number of available
colors increases to 8 (by adding GREY and CYAN)
