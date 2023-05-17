/**
 * This class represents information about a particular move in a sequence of moves.
 * It has stacks that store element being moved from and being moved to.
 * @author CS1027 Chaewon Eom
 */

public class Move {
	
	/**
	 * stack that stores element being moved from
	 */
	private StackADT<Domino> from;
	
	/**
	 * stack that stores element being moved to
	 */
	private StackADT<Domino> to;
	
	/**
	 * boolean returns true if the move is completed
	 */
	private boolean completed;
	
	/**
	 * name of the move
	 */
	private String name;
	
	/**
	 * Constructor creates a new Move with from and to
	 * and calls other constructor with name "m"
	 * @param from stack being moved from
	 * @param to stack being moved to
	 */
	public Move(StackADT<Domino> from, StackADT<Domino> to) {
		this(from, to, "m");
	}
	
	/**
	 * Constructor creates a new Move with from, to and name
	 * and sets completed to false
	 * @param from stack being moved from
	 * @param to stack being moved to
	 * @param name name of the move
	 */
	public Move(StackADT<Domino> from, StackADT<Domino> to, String name) {
		this.from = from;
		this.to = to;
		this.name = name;
		this.completed = false;
	}

	/**
	 * Method to pop an element in from and push it on to
	 * and sets completed to true
	 */
	public void doIt() {
		to.push(from.pop());
		completed = true;
	}
	
	/**
	 * Method to pop an element in from and push it on from
	 * and sets completed to false
	 */
	public void undolt() {
		from.push(to.pop());
		completed = false;
	}
	
	/**
	 * Getter method for completed
	 */
	public boolean isCompleted() {
		return completed;
	}
	
	/**
	 * Method checks if this from and to are the same as obj's from and to
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Move) {
			if(this.from == ((Move)obj).from && this.to == ((Move)obj).to) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	/**
	 * Method to format the Move in String type
	 */
	public String toString() {
		String res = name;
		
		if(from instanceof Named) {
			res += ((Named)from).getName();
		}
		else {
			res += from.toString();
		}
		
		res += "->";
		
		if(to instanceof Named) {
			res += ((Named)to).getName();
		}
		else {
			res += to.toString();
		}
		
		if(isCompleted()) {
			res += "!";
		}
		else {
			res += "?";
		}
		return res;
	}
	
}
