import java.util.Random;

public class Domino implements Named{
	private int high, low, id;
	private static int serialID = 0;

	public Domino(int high, int low) {
		id =serialID++;  // creating unique numbers for each Domino
		if (high < low) { // just in case...
			int temp = high;
			high = low;
			low = temp;
		}
		this.high = high;
		this.low = low;
	}
    public int getHigh() {
    	return high;
    }
    public int getLow() {
    	return low;
    }
	public String toString() {
		return "[" + (high==0?" ":high)  + ":" +(low==0?" ":low) + "]";
	}

	public boolean hasCommonNumber(Domino other) {  // returns whether the two dominos share a number
		return high == other.high || high == other.low || low == other.high || low == other.low;
	}
	 
	public boolean isOneUp(Domino other) { // returns whether this domino is one up from other
		return hasCommonNumber(other) && (high+low -1 == other.low+other.high);
	}
	public boolean isOneDown(Domino other) { // returns whether this domino is down up from other
		return hasCommonNumber(other) && (high+low +1 == other.low+other.high);
	}
	public String getName() { // a single character that could be useful in debugging for tracing a domino's movement 
		return (char)('A'+id)+"";
	}
	public String showNamedContent() { // a String that links the name and the domino
		return getName()+toString();
	}
	public static Domino[] getSet(int highest) {  // returns a complete set of dominoes 
		Domino[] set = new Domino[(highest+2)*(highest+1)/2];
		serialID =0;
		int count = 0;
		for(int i=0;i<highest+1;i++) 
			for(int j=0;j<=i;j++) 
			    set[count++] = new Domino(j,i);
		
		return set;
	}
	
	public static void shuffle(Domino[] set, int seed) {  // in-place shuffling that uses a seed so that the same shuffle can be repeated
		Random generator = new Random(seed);
		
		for(int i=set.length-1;i>0;i--) {
			int randomSpot = generator.nextInt(i+1); // this reproducible unlike (int)(Math.random()*(i+1))

			Domino temp = set[i];
			set[i] = set[randomSpot];
			set[randomSpot] = temp;
		}
	}
	public static void main(String[] args) { //quick test
		Domino[] set = getSet(2);
		shuffle(set,14);  // always returns [1: ] [ : ] [1:1] [2: ] [2:1] [2:2] 
		for(int i=0;i<set.length;i++)
			System.out.print(set[i].showNamedContent()+ " ");
		System.out.println("\n"+set.length); // 6
		
	    set = getSet(6);
		System.out.println(set.length); //28 
	}
}
