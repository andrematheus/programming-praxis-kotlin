# Programming Praxis in Kotlin

This project is my attempt at solving Programming Praxis problems
using Kotlin.

### Running

To compile all problems:

	gradle build

To compile and test all problems:

	gradle test

## RPN Calculator

This problem is solved. The solution class is parameterized on the 
operands type, and accepts an init body to register the operations.

> February 19, 2009
>
> Implement an RPN calculator that takes an expression like 19 2.14 + 4.5 2 4.3 / - * 
which is usually expressed as (19 + 2.14) * (4.5 - 2 / 4.3) and responds with 85.2974. The program should read expressions from standard input and print the top of the stack to standard output when a newline is encountered. The program should retain the state of the operand stack between expressions.

## Babbage Number

This problem is solved.

> October 9, 2018
> 
> Charles Babbage, whose Analytical Engine was a direct predecessor of today’s digital 
computer, gave this example of a problem that his Analytical Engine could solve in an 1837 letter to Lord Bowden:
> 
> What is the smallest positive integer whose square ends in the digits 269,696?
> 
> Babbage knew that 99,736 has a square with the required ending, but didn’t know if 
there was a smaller number.
> 
> Your task is to find Babbages’s number. When you are finished, you are welcome to read
 or run a suggested solution, or to post your own solution or discuss the exercise in the comments below.