package app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Point2D implements Cloneable, Serializable {
    @Id
    private Long id;
    private int x;
    private int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected Point2D() {}

    public Point2D translate(int dx, int dy){
        return new Point2D(x + dx, y + dy);
    }

    @Override
    public Point2D clone(){
        return new Point2D(x,y);
    }

    @Override
    public String toString() {
        return String.format("%d %d",x,y);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2D point2D = (Point2D) o;
        return x == point2D.x && y == point2D.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
