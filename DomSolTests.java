
public class DomSolTests {
	private static int passed = 0;
	private static int tested = 0;

	public static void main(String[] args) {
		StackADT<Domino> from = new StackArray<Domino>();
		StackADT<Domino> to = new StackArray<Domino>();
		Move mv = new Move(from,to);
		boolean good = true;
		try {
				mv.doIt();
				good = false;
		} catch(EmptyCollectionException e) {}
		String result = mv+" "+good +" "+mv.isCompleted()+" "+from+" "+to;

		// ********** Move: 1
		test(1,"Move: 1", result.equals("mAStack:->AStack:? true false AStack: AStack:"));

		from = new StackLL<Domino>("From");
		to = new StackLL<Domino>("To");

		Domino d = new Domino(0,1);
		from.push(d);
		mv = new Move(from,to,"good");
		try {
			good =true;
			mv.doIt();
		} catch(EmptyCollectionException e) {good = false;}	
		result = mv+" "+good +" "+mv.isCompleted()+" "+from+" "+to;


		// ********** Move: 2
		test(2,"Move: 2", result.equals("goodFrom->To! true true From |top><| To |top>[1: ] <|"));

		DomSolitaire ds = new DomSolitaire(2, 9,"simple");
		result = ds+ds.showNamedContent();


		// ********** DomSolitaire: toString showNamedContent
		test(3,"DomSolitaire: toString showNamedContent", result.contains("simple F0 0/1  F1 0/2  F2 0/3  ") &&
		result.contains("S0 |top>[ : ] <|") &&
		result.contains("S1 |top>[2:1] [1:1] <|") &&
		result.contains("S2 |top>[1: ] [2:2] [2: ] <|") &&
		result.contains("000|A |E C |B F D |") );

		mv = ds.createMove("S0","F0");
		mv.doIt();
		result = ds+ds.showNamedContent();

		// ********** DomSolitaire: simple move
		test(4,"DomSolitaire: simple move", result.contains("simple F0 1/1  F1 0/2  F2 0/3  ") &&
		result.contains("S0 |top><|") &&
		result.contains("S1 |top>[2:1] [1:1] <|") &&
		result.contains("S2 |top>[1: ] [2:2] [2: ] <|") &&
		result.contains("100||E C |B F D |") );

		ds.reset(9);
		result = ds+ds.showNamedContent();

		// ********** DomSolitaire: reset
		test(5,"DomSolitaire: reset", result.contains("simple F0 0/1  F1 0/2  F2 0/3  ") &&
		result.contains("S0 |top>[ : ] <|") &&
		result.contains("S1 |top>[2:1] [1:1] <|") &&
		result.contains("S2 |top>[1: ] [2:2] [2: ] <|") &&
		result.contains("000|A |E C |B F D |") );

		ds = new DomSolitaire(3, 6,"medium");
		ListADT<Move> list= ds.findSFMoves();
		boolean all = list.contains(ds.createMove("S2","F3")) &&  list.contains(ds.createMove("S1","F2")) &&  list.contains(ds.createMove("S0","F1"));

		// ********** DomSolitaire: findSFMoves
		test(6,"DomSolitaire: findSFMoves", all);

		ds = new DomSolitaire(3, 6,"medium");
		try {
//			ds.createMove("S2","F3").doIt();
			ds.createMove("S1","F2").doIt();
			ds.createMove("S0","F1").doIt();
		} catch(Exception e) {}
		result = ds.findSSMoves()+" "+ds.findSESMoves();

		// ********** DomSolitaire: findSSMoves findSESMoves
		test(7,"DomSolitaire: findSSMoves findSESMoves", result.equals("List [mS2->S1? ] List [mS3->S0? ]"));

		ds = new DomSolitaire(3, 1373,"win");	
		result = ds.findSolution(1000)+"";

		// ********** DomSolitaire: findSolution 1
		test(8,"DomSolitaire: findSolution 1", result.equals("Stack |top>mS1->F2! mS3->F0! mS0->S2! mS3->S1! mS3->S2! mS3->F2! mS2->S0! mS2->F2! mS0->S3! mS2->S0! mS2->F1! mS0->F1! mS2->F3! mS1->F3! mS1->F3! mS3->F3! <|"));

		ds = new DomSolitaire(3, 1998,"max");	
		result = ds.findSolution(1000)+""+ds;

		// ********** DomSolitaire: findSolution 2
		test(9,"DomSolitaire: findSolution 2", result.contains("nullmax F0 1/1  F1 1/2  F2 3/3  F3 1/4  ") &&
		result.contains("S0 |top>[1: ] <|") &&
		result.contains("S1 |top>[3:1] [3: ] [3:2] <|") &&
		result.contains("S2 |top><|") &&
		result.contains("S3 |top><|") );

		ds = new DomSolitaire(3, 1566,"lose");	
		result = ds.findSolution(1000)+"";

		// ********** DomSolitaire: findSolution 3
		test(10,"DomSolitaire: findSolution 3", result.equals("Stack |top><|"));


		System.out.println("\n Your code passed " + passed + " out of " + tested);

	}

	public static void test(int testNumber, String message, boolean testStatus) {
		tested++;
		System.out.println("Test " + testNumber + " (" + message + ") " + (testStatus ? "passed" : "failed"));
		if (testStatus)
			passed++;
	}
}