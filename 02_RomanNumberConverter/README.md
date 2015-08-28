# coding dojo - roman numerals converter

Original kata found at [codingdojo.org](http://codingdojo.org/cgi-bin/index.pl?KataRomanNumerals).

The Romans were a clever bunch. They conquered most of Europe and ruled it for hundreds of years. They invented concrete and straight roads and even bikinis. One thing they never discovered though was the number zero. This made writing and dating extensive histories of their exploits slightly more challenging, but the system of numbers they came up with is still in use today. For example the BBC uses Roman numerals to date their programmes.

The Romans wrote numbers using letters - I, V, X, L, C, D, M. (notice these letters have lots of straight lines and are hence easy to hack into stone tablets)

## goal

 The Kata says you should write a function to convert from integers to Roman Numerals, e.g.:
```
 1 --> I
 7 --> VII
10 --> X
```

For a full description of how it works, take a look at [this useful reference website](http://www.novaroma.org/via_romana/numbers.html).

**There is no need to be able to convert numbers larger than about 3000**. The Romans themselves didn't tend to go any higher.

Note that for this katga you can't write numerals like "IM" for 999. Instead write Roman Numerals by expressing each digit separately starting with the left most digit and skipping any digit with a value of zero. To see this in practice, consider the example of 1990. In Roman numerals 1990 is rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC. 2008 is written as 2000=MM, 8=VIII; or MMVIII. 
