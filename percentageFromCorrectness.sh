#!/bin/bash
programToExecute="$1"
input="$2"
expectedOutput=$(echo "${input::-2}OUT" | xargs -I 'file' cat 'file' | head -1)
realOutput=$(java "$programToExecute" "$input" | tail -2 | head -1)
echo "This is the expected correct output: "
echo "$expectedOutput"
echo "this is the obtained output: "
echo "$realOutput"
outputDifference=$(echo "($realOutput-$expectedOutput) * 100 / $expectedOutput" | bc -l)
echo "The solution found by the program is $outputDifference% more expensive than the correct one"
