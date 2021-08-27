package app.model;

import javax.persistence.*;
import java.util.Map;

@Entity
public class Movement {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    @Column(unique=true)
    private char letter;
    @ElementCollection
    private Map<Orientation,Integer> dy;
    @ElementCollection
    private Map<Orientation,Integer> dx;
    @ElementCollection
    private Map<Orientation,Orientation> newOrientation;

    protected Movement(){}

    public Movement(char letter, Map<Orientation,Integer> dx, Map<Orientation,Integer> dy, Map<Orientation,Orientation> newOrientation) {
        this.letter = letter;
        this.dx = dx;
        this.dy = dy;
        this.newOrientation = newOrientation;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public Map<Orientation,Integer> getDy() {
        return dy;
    }

    public void setDy(Map<Orientation,Integer> dy) {
        this.dy = dy;
    }

    public Map<Orientation,Integer> getDx() {
        return dx;
    }

    public void setDx(Map<Orientation,Integer> dx) {
        this.dx = dx;
    }

    public void setNewOrientation(Map<Orientation, Orientation> newOrientation) {
        this.newOrientation = newOrientation;
    }

    public Orientation newOrientation(Orientation oldOrientation){
        return newOrientation.get(oldOrientation);
    }
}
