package app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.List;

@Entity
public class RobotStats implements Serializable {
    @Id
    private Long id;
    @OneToMany
    private List<Position> pathFollowed;
    private boolean wasLost;
    private boolean leavedScent;

    protected RobotStats(){}

    public RobotStats(List<Position> pathFollowed, boolean wasLost, boolean leavedScent) {
        this.pathFollowed = pathFollowed;
        this.wasLost = wasLost;
        this.leavedScent = leavedScent;
    }

    public List<Position> getPathFollowed() {
        return pathFollowed;
    }

    public void setPathFollowed(List<Position> pathFollowed) {
        this.pathFollowed = pathFollowed;
    }

    public boolean isWasLost() {
        return wasLost;
    }

    public void setWasLost(boolean wasLost) {
        this.wasLost = wasLost;
    }

    public boolean isLeavedScent() {
        return leavedScent;
    }

    public void setLeavedScent(boolean leavedScent) {
        this.leavedScent = leavedScent;
    }
}
