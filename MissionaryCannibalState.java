package nshrestha;

import java.util.Set;  
import java.util.HashSet;

import nshrestha.AbstractState;
import nshrestha.State;
  
public class MissionaryCannibalState extends AbstractState {
	
	private int eMissionary, wMissionary, eCannibal, wCannibal;
	
	
	enum Side {
        EAST { public Side getOpposite() { return WEST; } },
        WEST { public Side getOpposite() { return EAST; } };
        
        abstract public Side getOpposite();
    }
   
    private Side boat = Side.EAST;
    

    /**
     * Constructs a new default state.  Everyone is on the east side.
     */
    public MissionaryCannibalState() {
    	eMissionary = 3;
    	eCannibal = 3;
    	wMissionary = 0;
    	wCannibal = 0;
    	boat = Side.EAST;
    }

    /**
     * Constructs a move state from a parent state     
     */
    public MissionaryCannibalState(MissionaryCannibalState parent, 
    		int deltaMissionary, int deltaCannibal) {
       
    	super(parent);
        
    	if (parent.boat == Side.EAST) {
    		this.boat = Side.WEST;
    	}
    	else {
    		this.boat = Side.EAST;
    	}
        
    	if (this.boat == Side.EAST) {
    		this.eMissionary = parent.eMissionary + deltaMissionary;
    		this.wMissionary = parent.wMissionary - deltaMissionary; 
    		this.eCannibal = parent.eCannibal + deltaCannibal;
    		this.wCannibal = parent.wCannibal - deltaCannibal; 
    	}
    	
    	if (this.boat == Side.WEST) {
    		this.eMissionary = parent.eMissionary - deltaMissionary;
    		this.wMissionary = parent.wMissionary + deltaMissionary; 
    		this.eCannibal = parent.eCannibal - deltaCannibal;
    		this.wCannibal = parent.wCannibal + deltaCannibal; 
    	}
    		
    }
    
   

    /**
     * Returns a set of all possible moves from this state.
     */
    public Iterable<State> getPossibleMoves() {
        Set<State> moves = new HashSet<State>();
        // Move 
       new MissionaryCannibalState(this, 1, 0).addIfSafe(moves);
       new MissionaryCannibalState(this, 2, 0).addIfSafe(moves);
       new MissionaryCannibalState(this, 0, 1).addIfSafe(moves);
       new MissionaryCannibalState(this, 0, 2).addIfSafe(moves);
       new MissionaryCannibalState(this, 1, 1).addIfSafe(moves);

        return moves;
    }
    
    private final void addIfSafe(Set<State> moves) {
      /**
       * Another way to calidate the moves
       *   boolean unsafe = (eMissionary > eCannibal) ||
       *   (wMissionary > wCannibal) ||
       *   (eMissionary > 0)  || 
       *   (wMissionary < 0)  || 
       *   (eCannibal < 0)  || 
       * 	(wCannibal < 0);      
       */
        
        boolean safe = ((eMissionary <= eCannibal) || (eCannibal == 0)) && 
        			   ((wMissionary <= wCannibal) || (wCannibal == 0)) && 
        			   (eMissionary >= 0) &&
        			   (wMissionary >= 0) &&
        			   (eCannibal >= 0) &&
        			   (wCannibal >= 0);

        if (safe)
            moves.add(this);
    }

    /**
     * The solution is specified as everyone being on the west side
     * @return true if this state is a solution
     */
    public boolean isSolution() {
        return wMissionary == 3 && 
               wCannibal == 3;
          }

    /**
     * Returns a heuristic approximation of the number of moves required
     * to solve this problem from this state.  This is implemented as
     * the number of characters on the east side.
     */
    public double getHeuristic() {
        return 2*(eMissionary + eCannibal);
    }
    /**
     * Compares whether two states are equal.
     */
    public boolean equals(Object o) {
        if (o==null || !(o instanceof MissionaryCannibalState))
            return false;
        MissionaryCannibalState mcs = (MissionaryCannibalState)o;
        return eMissionary  == mcs.eMissionary && 
        		wMissionary  == mcs.wMissionary && 
        		eCannibal  == mcs.eCannibal &&
        		wCannibal  == mcs.wCannibal &&
        		boat == mcs.boat;
    }
    /**
     * Returns a hashcode for this state (for lookup optimization).
     */
    public int hashCode() {
    	boolean boatOnEast = (boat == Side.EAST);
        return ((boatOnEast ? 1: 0)<< 16)|
        		(eMissionary << 8 )|
        		(eCannibal);
    }
    /**
     * Returns a string representation of this state
     */
    public String toString() {
        return  (eMissionary  == 0 ? "   " : "")+
        		(eMissionary  == 1 ? "M  " : "")+
        		(eMissionary  == 2 ? "MM " : "")+
        		(eMissionary  == 3 ? "MMM" : "")+
        		(eCannibal  == 0 ? "   " : "")+
        		(eCannibal  == 1 ? "C  " : "")+
        		(eCannibal  == 2 ? "CC " : "")+
        		(eCannibal  == 3 ? "CCC" : "")+
                " | ~~~~~ | "+
                (wMissionary  == 0 ? "   " : "")+
        		(wMissionary  == 1 ? "M  " : "")+
        		(wMissionary  == 2 ? "MM " : "")+
        		(wMissionary  == 3 ? "MMM" : "")+
        		(wCannibal  == 0 ? "   " : "")+
        		(wCannibal  == 1 ? "C  " : "")+
        		(wCannibal  == 2 ? "CC " : "")+
        		(wCannibal  == 3 ? "CCC" : "")+

                " (heuristic: "+getHeuristic()+")";
    }

}