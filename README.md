# Synopsys-Calculator
A Calculator developed as a part of the coding assessment at Synopsys

Functional Requirements:<br />
A calculator program in Java that evaluates expressions in a very simple integer expression language. The program takes an input on the command line, computes the result, and prints it to the console. For example:

Input				-			Output

add(1, 2)			-			3

add(1, mult(2, 3))		-			7

mult(add(2, 2), div(9, 3))	-			12

let(a, 5, add(a, a))		-			10

let(a, 5, let(b, mult(a, 10), add(b, a)))	-	55

let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))) -	40

An expression is one of the of the following:

•	Numbers: integers between Integer.MIN_VALUE and Integer.MAX_VALUE

•	Variables: strings of characters, where each character is one of a-z, A-Z

•	Arithmetic functions: add, sub, mult, div, each taking two arbitrary expressions as arguments.  In other words, each argument may be any of the expressions on this list.

•	A “let” operator for assigning values to variables:

	let(<variable name>, <value expression>, <expression where variable is used>)

As with arithmetic functions, the value expression and the expression where the variable is used may be an arbitrary expression from this list. 

The attached code consists of:

1) src - containing Calculator.java - 	Main Java file where logic resides, 
		    CalculatorTest.java - Containing JUnit test cases,
		    CalculatorTestRunner.java - Driver program to execute tests.
					
2) makefile - automates build process

3) pom.xml - contains project configuration details and dependencies

Steps to run the program:<br />

1) Run 'make' after cd-ing into this directory<br />

2) Invoke java -jar target/my-calculator-0.0.1-SNAPSHOT-jar-with-dependencies.jar "add(1,2)"<br />

3) To set the level of verbosity of logging via command line include a flag '-Dlog4j.configuration="file:/path-on-your-system/log4j.properties"' in addition to  the java command. Within this flag, include a path to a log4j.properties on your system with the 'log4j.rootLogger' property set to DEBUG, INFO or ERROR, as desired.

An example command, java -Dlog4j.configuration="file:/your-path/log4j.properties" -jar target/my-calculator-0.0.1-SNAPSHOT-jar-with-dependencies.jar "add(1,2)"

Frameworks used:

1) JUnit - to perform Unit testing
2) log4j - to implement logging

Further Improvements:

1) The test cases written in JUnit could be more comprehensive. It could contain tests that check if a certain method throws the appropriate exception. For example, Divide by Zero, NumberFormat Exception and so on..

2) Continous Integeration build could be set up via publicly available CI services such as Travis-CI, CodeShip etc.

Please note that due to time constraints I was unable to add the aforementioned improvements.
