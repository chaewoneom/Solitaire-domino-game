/**
 * This class represents game stack and finds the solution sequences.
 * It has foundation and regular stack arrays.
 */

public class DomSolitaire {
	
	/**
	 * array of StackADT that stores foundation
	 */
	private StackADT<Domino>[] foundation;
	
	/**
	 * array of StackADT that stores stack
	 */
	private StackADT<Domino>[] stack;
	
	/**
	 * name of foundation and stack
	 */
	private String name;
	
	/**
	 * boolean variable to check if debugging is needed
	 */
	private boolean debug;
	
	/**
	 * Constructor creates new DomSolitaire with highestNum, seed and name
	 * @param highestNum highest number of domino tiles
	 * @param seed number used to initialize pseudorandom number generator
	 * @param name name of the foundation and stack
	 */
	public DomSolitaire(int highestNum, int seed, String name) {
		foundation = new StackADT[highestNum + 1];
		stack = new StackADT[highestNum + 1];
		this.name = name;
		
		for(int i = 0; i <= highestNum; i++) {
			foundation[i] = new StackLL<Domino>("F" + i);
			stack[i] = new StackLL<Domino>("S" + i);
		}
		
		Domino[] dom = Domino.getSet(highestNum);

		Domino.shuffle(dom, seed);
		
		int index = 0;
		for(int i = 0; i <= highestNum; i++) {
			for(int j = 0; j <= i; j++) {
				stack[i].push(dom[index]);
				index++;
			}
		}
	}
	
	/**
	 * Getter method for name
	 * @return name name of the foundation and stack
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter method to set the debug 
	 * @param debug boolean variable to check if debugging is needed
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	/**
	 * Method that resets the Domino set and shuffles them
	 * @param seed number used to initialize pseudorandom number generator
	 */
	public void reset(int seed) {	
		DomSolitaire newd = new DomSolitaire(foundation.length - 1, seed, name);
		
		this.foundation = newd.foundation;
		this.stack = newd.stack;
		
		for(int i = 0; i < foundation.length - 1; i++) {
			foundation[i] = new StackLL<Domino>("F" + i);
			stack[i] = new StackLL<Domino>("S" + i);
		}

		Domino[] dom = Domino.getSet(foundation.length - 1);

		Domino.shuffle(dom, seed);
		
		int index = 0;
		for(int i = 0; i < foundation.length - 1; i++) {
			for(int j = 0; j <= i; j++) {
				stack[i].push(dom[index]);
				index++;
			}
		}
	}
	
	/**
	 * Method that returns if there is a winner or not
	 * @return true if there is a winner
	 * 		   false if there is no winner 
	 */
	public boolean winner() {
		for(int i = 0; i < stack.length; i++) {
			if(stack[i].isEmpty()) {
				continue;
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Method stores moves from a regular stack to a foundation stack
	 * @return SF list contatining all permissible moves from a regular stack to a foundation stack
	 */
	public UnorderedListADT<Move> findSFMoves() {
		UnorderedListADT<Move> SF = new UnorderedList<>();
		for(int i = 0; i < stack.length; i++) {
			for(int j = 0; j < foundation.length; j++) {
				
				Move move = new Move(stack[i], foundation[j]);
				
				if(stack[i].isEmpty()) {
					continue;
				}
				if(foundation[j].isEmpty()) {
					if((stack[i].peek().getHigh() == stack[i].peek().getLow()) && 
							(stack[i].peek().getHigh() == j)) {
						SF.addToFront(move);
					}	
				}
				else if((stack[i].peek().getHigh() == j) && 
						(stack[i].peek().isOneDown(foundation[j].peek()))) {
					SF.addToFront(move);
				}
			}
		}
		return SF;
	}
	
	/**
	 * Method stores moves from a regular stack to an empty regular stack
	 * @return SES list contatining all permissible moves from a regular stack to an empty regular stack
	 */
	public UnorderedListADT<Move> findSESMoves() {
		UnorderedListADT<Move> SES = new UnorderedList<>();
		for(int i = 0; i < stack.length; i++) {
			if(stack[i].isEmpty() ) {
				for(int j = 0; j < stack.length; j++) {
					Move move = new Move(stack[j], stack[i]);
					if(stack[j].isEmpty()) {
						continue;
					}
					
					if(stack[j].peek().getLow() == 0) {
						SES.addToFront(move);
					}
				}
			}
		}
		return SES;	
	}
	
	/**
	 * Method stores moves from a regular stack to a non empty regular stack
	 * @return SS list contatining all permissible moves from a regular stack to a non empty regular stack
	 */
	public UnorderedListADT<Move> findSSMoves() {
		UnorderedListADT<Move> SS = new UnorderedList<>();
		for(int i = 0; i < stack.length; i++) {
			if(!stack[i].isEmpty()) {
				for(int j = 0; j < stack.length; j++) {
					if(!stack[j].isEmpty()) {
						Move move = new Move(stack[j], stack[i]);
						if((stack[i].peek().getHigh() == stack[j].peek().getHigh()) && 
								(stack[j].peek().isOneUp(stack[i].peek()))) {
							SS.addToFront(move);
						}
					}
				}
			}
		}
		return SS;
	}
	
	/**
	 * Method adds all moves in order (SES, SS, SF)
	 * @param st stack that stores all the moves in order
	 */
	public void findMoves(StackADT<Move> st) { 
		UnorderedListADT<Move> SF = findSFMoves();
		UnorderedListADT<Move> SES = findSESMoves();
		UnorderedListADT<Move> SS = findSSMoves();
		
		for(int i = 0; i < SES.size(); i++) {
			st.push(SES.last());
			if(!SES.isEmpty()) {
				SES.removeLast();
			}
		}
		
		for(int i = 0; i < SS.size(); i++) {
			st.push(SS.last());
			if(!SS.isEmpty()) {
				SS.removeLast();
			}
		}
		
		for(int i = 0; i < SF.size(); i++) {
			st.push(SF.last());
			if(!SF.isEmpty()) {
				SF.removeLast();
			}
		}
	}
	
	/**
	 * Method creates move by from and to Strings and link them to corresponding StackADTs
	 * @param from stack name that is going to be moved from
	 * @param to stack name that is going to be moved to
	 * @return m Move class 
	 */
	public Move createMove(String from, String to) {
		Move m = null;
		
		if(from.startsWith("S") && to.startsWith("F")) {
			m = new Move(stack[Integer.parseInt(from.substring(1))], 
					foundation[Integer.parseInt(to.substring(1))]);
		}
		
		return m;
		
	}
	
	/**
	 * Method to format the DomSolitaire in String type
	 */
	public String toString() {
		String res = name + " ";
		for(int i = 0; i < foundation.length; i++) {
			res += ((StackLL<Domino>)foundation[i]).getName() + " " + (foundation[i].size() + "/" + (i + 1) + "  ");
		}
		
		res += "\n";
		
		for(int i = 0; i < stack.length; i++) {
			res += stack[i].toString() + "\n";
		}

		return res;
		
	}
	
	/**
	 * Method to format the current state using Domino names 
	 * @return res String type of condensed format of the current state
	 */
	public String showNamedContent() {
		String res = toString();
		for(int i = 0; i < foundation.length; i++) {
			res += foundation[i].size();	
		}
		
		res += "|";
		
		for(int i = 0; i < stack.length; i++) {
			res += ((StackLL<Domino>)stack[i]).showNamedContent();
		}
		
		return res;
		
	}
	
	/**
	 * Method finds a Stack containing sequence of moves of solution
	 * @param maxSteps maximum number of steps that the algorithm can take
	 * @return empty stack 
	 */
	public StackADT<Move> findSolution(int maxSteps) {
		StackADT<Move> mvSeq = new StackLL<Move>();
		StackADT<Move> moves = new StackLL<Move>();
		StackADT<Move> mvSeqNew = new StackLL<Move>();
		
		findMoves(moves);
		
		int steps = 0;
		while(!winner()) {	// while there is no winner
			
			if(moves.isEmpty()) {	// if there is no solution, return empty stack
				return mvSeqNew;
			}
			
			if(!moves.peek().isCompleted()) {	// if the move has not been completed
				moves.peek().doIt();	// do the move
				mvSeq.push(moves.peek());	// add more moves to the stack
				
				moves = new StackLL<Move>();
				findMoves(moves);
			}
			else {	// if the move has been completed
				mvSeqNew.push(mvSeq.pop());	// backtrack
				moves.pop();	// remove the completed move from the stack
			}
			
			if(steps > maxSteps) { // if the maxSteps has been exceeded, return null
				return null;
			}
			steps++;
		}
		
		while(!mvSeq.isEmpty()) {
			mvSeqNew.push(mvSeq.pop());
			
		}
		
		return mvSeqNew;
		
	}
}
