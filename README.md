# SimpleDFA
DFA Project - README

1. Overview

The objective of the DFA project is to create and simulate a Deterministic Finite Automata (DFA) for three different languages in a single program:

	- L1 accepts strings: "abba", "abbba", "abbbaa", and "abbbaaa".
	- L2 accepts strings: "baab", "baaab", "baaabb", and "baaabbb".
	- L1 ∪ L2 is the union of L1 and L2 and accepts strings from either language.

State objects and Transition objects are used in the construction of the DFA for each language. A helper static method simulates the DFA, assisting a static testing method to ensure that the correct behavior is demonstrated for each input sequence.

2. Files

The DFA project consists of three files:

	I.   State.java: Defines the State class which contstructs new State objects 	
	     representing states in the DFA. It includes methods for managing and accessing the 	     state identifiers and determining whether a state is an accepting state.

	II.  Transition.java: Defines the Transition class which constructs new Transition 
	     objects representing each transition in the DFA. It includes methods for accessing 	     the starting State, input symbol that triggers a transition, and a State that the       	     transition leads to. Additionally, it provides a method to retrieve a string 
	     representation of the Transition object. 

	III. DFA.java: Defines the main logic for simulating and testing the DFAs including two
	     static methods: testDFA() and simulateDFA().

3. How It Works

	- State objects represents the states in the DFA. Each state includes a unique 			  identifier and a boolean determining whether it is a final state.
	
	- Transition objects represents the transitiona in the DFA. Each transition consists of 
	  state where transition begins, input symbol that triggers a transition, and a state 	
	  where the transition ends. 

	- The testDFA() method gathers input sequences from the user to test the DFA. It calls 
	  the simulateDFA() method to run the simulation. The simulateDFA() processes the input
	  sequence and determines whether it is accepted by the DFA. The program will 	 		  automatically test all three languages.

4. Running the Program

	Step 1: Compile all of the Java files: State.java, Transition.java, and DFA.java

	Step 2: Run the DFA.java class from command line or in IDE.

	Step 3: The program will prompt user to input a sequence of characters consisting of
		'a' and 'b'. User can press enter to skip DFA test.

	Step 4: Program allows user to test additional input sequences by entering'y'. 		        		Entering 'n' exits the program.

	Step 5: Process is repeated for DFA tests for L2 and  L1 ∪ L2.

5. Requirements

	- Operating System
	- Java Development Kit (JDK) : version ≥ 1.8
	- Java IDE/Command-line tool

