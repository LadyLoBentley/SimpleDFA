import java.util.ArrayList;
import java.util.Scanner;
	

public class DFA {
	
	public static void testDFA(ArrayList<State>states, ArrayList<Transition> transitions, Scanner scnr) {
		String sequence;				
		boolean isRunning;
		isRunning = true;
		
		while (isRunning) {		
			System.out.println("Please enter a sequence of characters consisting of 'a' and "
					+ "'b' : (Press Enter to Skip)\n");
			
			sequence = scnr.nextLine();	// read user input
			
			if (sequence.isEmpty()) {
				System.out.println("Empty Sequence : Skipping the DFA sequence test.\n");
				isRunning = false;
				continue;	
			}
			
			boolean isAccepted = simulateDFA(states, transitions, sequence);	// check to see if sequence is valid
			System.out.println();
			
			if (isAccepted) {		// simulateDFA() returned true, so sequence is accepted by DFA
				System.out.printf("The sequence, %s, is accepted by the DFA!\n", sequence);
			}
			else {					// simulateDFA() returned false, so sequence is rejected by DFA 
				System.out.printf("The sequence, %s, is not accepted by the DFA\n", sequence);
			}
			
			// Ask user if they want to test another sequence
			while (true) {
				System.out.println("\nDo you want to try another sequence? (y/n)");
				String userInput = scnr.nextLine().toLowerCase();
				
				// Make sure input is not empty
				if (userInput.isEmpty()) {
					System.out.println("ERROR: INVALID INPUT. PLEASE TRY AGAIN.");
					continue;
				}
				
				// process the input
				if (userInput.charAt(0) == 'n') {
					System.out.println("Testing the DFA is terminated.\n");
					isRunning = false;
					break;
				}	
				else if(userInput.charAt(0) == 'y') {
					System.out.println("Great!");
					break;
				}	
				else {
					System.out.println("ERROR: INVALID INPUT. PLEASE TRY AGAIN.");
				}
			}	
		}
	}
	
	public static boolean simulateDFA(ArrayList<State> states, ArrayList<Transition> transitions, 
			String input) {
		
		char symbol;
		boolean transitionFound;
		State current = states.get(0); 	// Start at q_start
		
		for(int i = 0; i < input.length(); i++) {	// iterate through each symbol in input sequence
			
			symbol = input.charAt(i);
			transitionFound = false;
			
			for (Transition transition : transitions) {
				
				// If transition of current state is found for symbol, get transition state
				if (transition.getStartState().equals(current) && transition.getInput() == symbol) {
					System.out.println("Transition found: " + transition.toString());
					current = transition.getNextState();
					transitionFound = true;
					break;
				}
			}			
			
			if (!transitionFound) {	 // otherwise, the DFA rejects the sequence
				return false;
			}		
		}
		return current.isFinal();	// True if sequence is at final state
	}

		
	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);
		
		// 1. L1 = {abba, abbba, abbbaa,abbbaaa}
		
		ArrayList<State> l1States = new ArrayList<>();	
		
		l1States.add(new State("qL1_start", false));	// Starting state
		l1States.add(new State("qL1_1", false));		// State for 'a'
		l1States.add(new State("qL1_2", false));		// State for 'ab'
		l1States.add(new State("qL1_3", false));		// State for 'abb'
		l1States.add(new State("qL1_4", true));			// Final State for 'abba' or 'abbba'
		l1States.add(new State("qL1_5", false));		// State for 'abbb'
		l1States.add(new State("qL1_6", true));			// Final State for 'abbba'
		l1States.add(new State("qL1_7", true));			// Final State for 'abbbaa'
		l1States.add(new State("qL1_rej", false));		// Reject State
		
		ArrayList<Transition> l1Transitions = new ArrayList<Transition>();
		
		l1Transitions.add(new Transition(l1States.get(0), 'a', l1States.get(1))); // Transition to qL1_1 on 'a' 
		l1Transitions.add(new Transition(l1States.get(1), 'b', l1States.get(2))); // Transition to qL1_2 on 'b' 
		l1Transitions.add(new Transition(l1States.get(2), 'b', l1States.get(3))); // Transition to qL1_3 on 'b' 
		l1Transitions.add(new Transition(l1States.get(3), 'a', l1States.get(4))); // Transition to qL1_4 on 'a' 
		l1Transitions.add(new Transition(l1States.get(3), 'b', l1States.get(5))); // Transition to qL1_5 on 'b' 
		l1Transitions.add(new Transition(l1States.get(5), 'a', l1States.get(6))); // Transition to qL1_6 on 'b' 
		l1Transitions.add(new Transition(l1States.get(6), 'b', l1States.get(8))); // Transition to qL1_rej on 'b' 
		l1Transitions.add(new Transition(l1States.get(6), 'a', l1States.get(7))); // Transition to qL1_7 on 'a' 
		l1Transitions.add(new Transition(l1States.get(7), 'b', l1States.get(8))); // Transition to qL1_rej on 'b' 
		l1Transitions.add(new Transition(l1States.get(7), 'a', l1States.get(4))); // Transition to qL1_4 on 'a' 
		
		// Rejected if more input follows after qL1_4
		l1Transitions.add(new Transition(l1States.get(4), 'a', l1States.get(8))); 
		l1Transitions.add(new Transition(l1States.get(4), 'b', l1States.get(8)));
		
		// Loop at qL1_rej if more input follows
		l1Transitions.add(new Transition(l1States.get(8), 'a', l1States.get(8)));
		l1Transitions.add(new Transition(l1States.get(8), 'b', l1States.get(8)));
	
		System.out.println("Let's test the DFA for L1: \n");
		testDFA(l1States, l1Transitions, scnr); // test the DFA
		
		// 2.	L2 = {baab,baaab,baaabb,baaabbb}
		
		ArrayList<State> l2States = new ArrayList<>();
	
		l2States.add(new State("qL2_start", false));	// Starting state
		l2States.add(new State("qL2_1", false));		// State for 'b'
		l2States.add(new State("qL2_2", false));		// State for 'ba'
		l2States.add(new State("qL2_3", false));		// State for 'baa'
		l2States.add(new State("qL2_4", true));			// Final state for 'baab' or 'baaabbb'
		l2States.add(new State("qL2_5", false));		// State for 'baaa'
		l2States.add(new State("qL2_6", true));			// Final state for 'baaab'
		l2States.add(new State("qL2_7", true));			// Final state for 'baaabb'
		l2States.add(new State("qL2_rej", false));		// Reject State
		
		ArrayList<Transition> l2Transitions = new ArrayList<Transition>();
		
		l2Transitions.add(new Transition(l2States.get(0), 'b', l2States.get(1))); // Transition to qL2_1 on 'b' 
		l2Transitions.add(new Transition(l2States.get(1), 'a', l2States.get(2))); // Transition to qL2_2 on 'a' 
		l2Transitions.add(new Transition(l2States.get(2), 'a', l2States.get(3))); // Transition to qL2_3 on 'a' 
		l2Transitions.add(new Transition(l2States.get(3), 'b', l2States.get(4))); // Transition to qL2_4 on 'b' 
		l2Transitions.add(new Transition(l2States.get(3), 'a', l2States.get(5))); // Transition to qL2_5 on 'a' 
		l2Transitions.add(new Transition(l2States.get(5), 'b', l2States.get(6))); // Transition to qL2_6 on 'b' 
		l2Transitions.add(new Transition(l2States.get(6), 'a', l2States.get(8))); // Transition to qL2_rej on 'a' 
		l2Transitions.add(new Transition(l2States.get(6), 'b', l2States.get(7))); // Transition to qL2_7 on 'b' 
		l2Transitions.add(new Transition(l2States.get(7), 'a', l2States.get(8))); // Transition to qL2_rej on 'a' 
		l2Transitions.add(new Transition(l2States.get(7), 'b', l2States.get(4))); // Transition to qL2_4 on 'b' 
		
		// Rejected if more input follows after qL2_4
		l2Transitions.add(new Transition(l2States.get(4), 'a', l2States.get(8))); 
		l2Transitions.add(new Transition(l2States.get(4), 'b', l2States.get(8)));
		
		// Loop at qL2_rej if more input follows
		l2Transitions.add(new Transition(l2States.get(8), 'a', l2States.get(8)));
		l2Transitions.add(new Transition(l2States.get(8), 'b', l2States.get(8)));
		
		System.out.println("Let's test the DFA for L2: \n");
		testDFA(l2States, l2Transitions, scnr); 
					
		// 3. L1 U L2
		
		ArrayList<State> combinedStates = new ArrayList<>();	// Stores states from L1 & L2
		
		combinedStates.addAll(l1States);		// Combine all states		
		combinedStates.addAll(l2States);		
		

		ArrayList<Transition> combinedTransitions = new ArrayList<Transition>();
		
		combinedTransitions.addAll(l1Transitions);	// combine all transitions
		combinedTransitions.addAll(l2Transitions);	
		
		
		// Transition from qL1_start to qL2_1
			// CombinedStates order is guaranteed to list L1 states first, then L2 states.
			// Thus, qL2_1 is at the size of the l1States ArrayList + 1 (skip L2's starting state)
			// If ordered is not guaranteed, search for state using while loop 
		combinedTransitions.add(new Transition(combinedStates.get(0), 'b', combinedStates.get(l1States.size() + 1))); 

		System.out.println("Let's test the DFA for L1 U L2: \n");
		testDFA(combinedStates, combinedTransitions, scnr); 
		
		scnr.close();
	}
}