package logic;

import java.util.Iterator;
import java.util.Vector;

public class Tester {

	public static void main(String[] args) {
		
		Constant bluebird = new Constant("bluebird");
		Constant bird = new Constant("bird");
		Constant blue = new Constant("blue");
		Constant vertebrate = new Constant("vertebrate");
		Constant small = new Constant("small"); 
		Constant flies = new Constant("flies");
		Constant feathers = new Constant("feathers");
		Constant animal = new Constant("animal");
		
		Variable hascolor = new Variable("hascolor");
		Variable hassize = new Variable("hassize");
		
		Vector<Unifiable> expressions = new Vector<>();
		expressions.add(new SimpleSentence(vertebrate, bird, animal));
		expressions.add(new SimpleSentence(bird, feathers, flies));
		expressions.add(new SimpleSentence(bluebird, blue, small));

		Unifiable goal = new SimpleSentence(bluebird, hascolor, hassize);
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
			
		goal = new SimpleSentence(bluebird, blue, small);
		
		}
	}
}
