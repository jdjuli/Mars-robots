package app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Robot {
    @Id
    private Long id;
    @OneToOne
    private Position initialPos;
    @OneToOne
    private Position currentPos;
    @OneToMany
    private List<Movement> movementList;

    protected Robot(){}

    public Robot(Position initialPos, List<Movement> movementList) {
        this.initialPos = initialPos;
        this.currentPos = initialPos.clone();
        this.movementList = movementList;
    }

    public RobotStats moveOver(MarsMap map){
        List<Position> pathFollowed = new LinkedList<>();
        Position posAhead;
        boolean scentDetected = map.checkScent(currentPos.getPoint2D());
        boolean outOfBounds = map.isPositionOutsideBounds(currentPos.getPoint2D());
        boolean scentLeaved = false;

        Iterator<Movement> movementIterator = movementList.listIterator();
        while (movementIterator.hasNext() && !outOfBounds){
            pathFollowed.add(currentPos);
            Movement movement = movementIterator.next();
            posAhead = currentPos.apply(movement);
            scentDetected = map.checkScent(currentPos.getPoint2D());
            if(!(scentDetected && map.isPositionOutsideBounds(posAhead.getPoint2D()))){
                currentPos = posAhead;
            }
            outOfBounds = map.isPositionOutsideBounds(currentPos.getPoint2D());
        }
        if(outOfBounds && !pathFollowed.isEmpty()){
            Point2D scentPoint = pathFollowed.get(pathFollowed.size()-1).getPoint2D();
            scentLeaved = map.leaveScent(scentPoint);
        }else{
            pathFollowed.add(currentPos);
        }
        return new RobotStats(pathFollowed,outOfBounds, scentLeaved);
    }

}
