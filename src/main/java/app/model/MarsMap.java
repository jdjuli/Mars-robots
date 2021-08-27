package app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;
import java.util.HashSet;

@Entity
public class MarsMap {
    @Id
    private Long id;
    @OneToMany
    private Set<Point2D> scentsLeaved;
    @OneToOne
    private Point2D size;

    protected MarsMap(){}

    public MarsMap(Point2D size) {
        this.scentsLeaved = new HashSet<>();
        this.size = size;
    }

    public boolean leaveScent(Point2D point){
        if(isOnBorder(point)) {
            scentsLeaved.add(point);
            return true;
        }else{
            return false;
        }
    }

    public boolean checkScent(Point2D point){
        boolean val = scentsLeaved.contains(point);
        return val;
    }

    private boolean isOnBorder(Point2D point){
        boolean check = point.getX() == 0 || point.getX() == size.getX();
        check |= point.getY() == 0 || point.getY() == size.getY();
        return check;
    }

    public boolean isPositionOutsideBounds(Point2D point){
        boolean check = point.getX() < 0 || size.getX() < point.getX();
        check |= point.getY() < 0 || size.getY() < point.getY();
        return check;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
