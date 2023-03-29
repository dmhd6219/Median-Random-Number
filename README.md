# Median Random Number
A pseudorandom number generation is often done using linear congruent method.
The idea is to start with a random seed and then to have a function that computes the next value in a sufficiently
"random" manner. Consider the following function for some constants <i>a</i>, <i>c</i>, and <i>m</i>:
<p align="center">
  <i>h</i>(X) = (<i>a</i>X + <i>c</i>) mod <i>m</i>
</p>
The GNU C Compiler (GCC) uses <i>a</i> = 1103515245, <i>c</i> = 12345, <i>m</i> = 231. Starting with seed 123,
the next 5 values are
<p align="center">
  440917656, 1476151025, 1668141782, 864299351, 1143491652
</p>
With sufficiently well-chosen constants *a*, *c*, *m*, we can assume that this
method to produce values uniformly in range from 0 to 2^{31} − 1.
<h3>Median via Bucket-Sort</h3>
Write a program that generates N random numbers in the range [0, 1), sorts them, and returns the index of the median
value in the original (random) sequence.

Consider the random number generator to produce random numbers X_i using
the following method:
<p align="center">
  X_<i>0</i> = seed; X_{<i>n+1</i>} = (<i>a</i>X_<i>n</i> + <i>c</i>) mod <i>m</i>
</p>
The random numbers Y_<i>i</i> are generated from X_<i>i</i> as follows:
<p align="center">
  Y_<i>i</i> = | (2X_<i>i</i> / <i>m</i>) − 1 |
</p>

<b>Requirement</b>. You must implement Bucket-Sort to solve this exercise.

<b>Warning</b>: although the values X_<i>i</i> are all strictly less than <i>m</i>, the value
of <i>a</i>X_<i>i</i> + <i>c</i> might grow larger than <i>m</i>. This might cause integer overflow if
not handled properly. Make sure you use the proper width of integers in your calculation.

<b>Input format</b>. First line of the input specifies parameters for the random
number generator: "a c m seed" (2 ≤ <i>a</i>, <i>c</i>, <i>m</i> < 2^{31} , 0 ≤ seed < <i>m</i>).
Second line contains a single number <N> (1 ≤ N ≤ 10^9).

<b>Output format</b>. The output must contain a single number <i>j</i>, which is
the index of the median of the N generated numbers. That is Y_<i>j</i> must be the
median value.
