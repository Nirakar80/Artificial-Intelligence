package logic;

import java.util.Iterator;
import java.util.Vector;

public class Tester {

	public static void main(String[] args) {
		
		Constant friend = new Constant("friend");
		Constant bill = new Constant("bill");
		Constant george = new Constant("george");
		Constant kate = new Constant("kate");
		Constant mary = new Constant("mary");
		
		Variable X = new Variable("X");
		Variable Y = new Variable("Y");
		
		Vector<Unifiable> expressions = new Vector<>();
		
		expressions.add(new SimpleSentence(friend, bill, george));
		expressions.add(new SimpleSentence(friend, bill, kate));
		expressions.add(new SimpleSentence(friend, bill, mary));
		expressions.add(new SimpleSentence(friend, george, bill));
		expressions.add(new SimpleSentence(friend, george, kate));
		expressions.add(new SimpleSentence(friend, kate, mary));

		Unifiable goal = new SimpleSentence(friend, X, Y);
		System.out.println("Goal = " + goal);
		Iterator<Unifiable> iter = expressions.iterator();  
		SubstitutionSet s;
		
		while(iter.hasNext()) {
			Unifiable next = iter.next();
			s = next.unify(goal, new SubstitutionSet());
			if(s != null)
				System.out.println(goal.replaceVariables(s));
			else
				System.out.println("False");
			
		goal = new SimpleSentence(friend, bill, Y);
		
		}
	}
}
