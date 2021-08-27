package app.persistence.database;

import app.model.Movement;
import app.model.Orientation;

import java.util.*;

public class DefaultMovements {

    public static Collection<Movement> getAll(){
        Set<Movement> movements = new HashSet<>();
        //Forward
        Map<Orientation,Integer> dxForward = new HashMap<>();
        Map<Orientation,Integer> dyForward = new HashMap<>();
        Map<Orientation,Orientation> rotationTableForward = new HashMap<>();
        dyForward.put(Orientation.N,1);
        dxForward.put(Orientation.N,0);
        dyForward.put(Orientation.E,0);
        dxForward.put(Orientation.E,1);
        dyForward.put(Orientation.S,-1);
        dxForward.put(Orientation.S,0);
        dyForward.put(Orientation.W,0);
        dxForward.put(Orientation.W,-1);
        rotationTableForward.put(Orientation.N,Orientation.N);
        rotationTableForward.put(Orientation.E,Orientation.E);
        rotationTableForward.put(Orientation.S,Orientation.S);
        rotationTableForward.put(Orientation.W,Orientation.W);
        //Right
        Map<Orientation,Integer> dxRight = new HashMap<>();
        Map<Orientation,Integer> dyRight = new HashMap<>();
        Map<Orientation,Orientation> rotationTableRight = new HashMap<>();
        dyRight.put(Orientation.N,0);
        dxRight.put(Orientation.N,0);
        dyRight.put(Orientation.E,0);
        dxRight.put(Orientation.E,0);
        dyRight.put(Orientation.S,0);
        dxRight.put(Orientation.S,0);
        dyRight.put(Orientation.W,0);
        dxRight.put(Orientation.W,0);
        rotationTableRight.put(Orientation.N,Orientation.E);
        rotationTableRight.put(Orientation.E,Orientation.S);
        rotationTableRight.put(Orientation.S,Orientation.W);
        rotationTableRight.put(Orientation.W,Orientation.N);
        //Left
        Map<Orientation,Integer> dxLeft = new HashMap<>();
        Map<Orientation,Integer> dyLeft = new HashMap<>();
        Map<Orientation,Orientation> rotationTableLeft = new HashMap<>();
        dyLeft.put(Orientation.N,0);
        dxLeft.put(Orientation.N,0);
        dyLeft.put(Orientation.E,0);
        dxLeft.put(Orientation.E,0);
        dyLeft.put(Orientation.S,0);
        dxLeft.put(Orientation.S,0);
        dyLeft.put(Orientation.W,0);
        dxLeft.put(Orientation.W,0);
        rotationTableLeft.put(Orientation.N,Orientation.W);
        rotationTableLeft.put(Orientation.E,Orientation.N);
        rotationTableLeft.put(Orientation.S,Orientation.E);
        rotationTableLeft.put(Orientation.W,Orientation.S);
        //Put them on the set
        movements.add(new Movement('F', dxForward, dyForward, rotationTableForward));
        movements.add(new Movement('R', dxRight, dyRight, rotationTableRight));
        movements.add(new Movement('L', dxLeft, dyLeft, rotationTableLeft));
        return movements;
    }
}
