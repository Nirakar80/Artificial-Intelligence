package search;

import java.util.List;
import search.AbstractSolver;
import search.DepthFirstSolver;
import search.BreadthFirstSolver;
import search.BestFirstSolver;
import search.State;

public class SolutionTester
{
	public static void main(String[] args)
	{
		System.out.println("Farmer Goat Cabbage Puzzle");
		trySolvers(new FarmerWolfGoatState());
	}
	
	private static void trySolvers(State initialState)
	{
		trySolver(initialState, new DepthFirstSolver());
		trySolver(initialState, new BreadthFirstSolver());
		trySolver(initialState, new BestFirstSolver());
	}
	
	private static void trySolver(State initialState, AbstractSolver solver)
	{
		System.out.println("Solving with " + solver);
		List<State> solution = solver.solve(initialState);
		System.out.println("States visited: " + solver.getVisitedStateCount());
		System.out.println("Solution :");
		if(solution == null)
		{
			System.out.println("Not found.");
		}
		else
		{
			for(State s : solution)
			{
				System.out.println("   " + s);
			}
			System.out.println("   " + solution.size() + " state(s)");
		}
	}

}
