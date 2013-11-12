# Kata 3 - Game of Life

The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician 
John Horton Conway in 1970

For a detailed introduction, see http://en.wikipedia.org/wiki/Conway's_Game_of_Life.

## Goal

The goal of this kata is to provide a tool which computes the next generation of a given "world" by 
implementing the following rules:

* Any live cell with fewer than two live neighbours dies, as if caused by under-population.
* Any live cell with two or three live neighbours lives on to the next generation.
* Any live cell with more than three live neighbours dies, as if by overcrowding.
* Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

The world should be implementes as n * m matrix 
