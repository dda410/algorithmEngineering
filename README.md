# ALGORITHM ENGINEERING (XB_400620)

## Overview

Algorithm Engineering is a new paradigm that unifies the design,
analysis, implementation and experimental evaluation of practical
algorithms.

This course will introduce the basic Algorithm Engineering principles
and illustrate the power of practice-oriented algorithm design by means
of several examples from different applications including Google’s
pagerank algorithm, clustering in data analysis and network modules in
bioinformatics.

## Repository Structure

The repository is structured as follows:

- __inputs__: contains the input files for the program.

- __src__: contains the source code and the files relative to the
  assignment. DOCUMENTATION.PDF is the manuscript describing
  the challenge and the implementation design choices. parseAllInputs
  is the one line bash script that recursively calls
  percentageFromCorrectness.sh to compute the algorithm
  correctness for all the input files.
  
- __outputAlgorithmCorrectness__: contains the output of
  parseAllInputs. 

- __percentageFromCorrectness.sh__: this script outputs in percentage
  how far is the algorithm solution from the optimal one.
  
- __specifications.pdf__: This file describes the specifications of
  the assignment.

## Instructions

#### Program instructions:
1. cd to src/ folder
2. compile with `javac *.java`
3. run with `java Main <path/to/input/file>`

#### Script instructions:
1. cd to src/ folder
2. run `../percentageFromCorrectness.sh "Main" "path/to/input/file"`

#### One line script instructions:
1. cd to src/ folder
2. run `source parseAllinputs`
