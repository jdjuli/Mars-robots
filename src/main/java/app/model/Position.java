package app.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Position implements Cloneable, Serializable {
    @Id
    private Long id;
    @OneToOne
    private Point2D point2D;
    private Orientation orientation;

    protected Position(){}

    public Position(Point2D point2D, Orientation orientation){
        this.point2D = point2D;
        this.orientation = orientation;
    }

    public Position(int y, int x, Orientation orientation){
        this(new Point2D(y, x), orientation);
    }

    public Point2D getPoint2D() {
        return point2D;
    }

    public void setPoint2D(Point2D point2D) {
        this.point2D = point2D;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Position apply(Movement movement){
        Point2D newPoint = point2D.translate(movement.getDx().get(orientation), movement.getDy().get(orientation));
        Orientation newOrientation = movement.newOrientation(orientation);
        return new Position(newPoint, newOrientation);
    }

    @Override
    public Position clone(){
        return new Position(point2D.clone(), orientation);
    }

    @Override
    public String toString() {
        return String.format("%s %s",point2D,orientation);
    }
}
