# coding dojo - Game of Life

The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970

For a detailed introduction, check out the entry on [wikipedia](http://en.wikipedia.org/wiki/Conway's_Game_of_Life).

## Goal

The goal of this kata is to provide a tool which computes the next generation of a given "world" by 
implementing the following rules:

* Any live cell with fewer than two live neighbors dies, as if caused by under-population.
* Any live cell with two or three live neighbors lives on to the next generation.
* Any live cell with more than three live neighbors dies, as if by overcrowding.
* Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

The world should be implemented as n * m matrix 
