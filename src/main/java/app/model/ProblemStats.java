package app.model;

import org.springframework.data.jpa.domain.JpaSort;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class ProblemStats implements Serializable {
    @Id
    private Long id;
    @OneToMany
    private List<RobotStats> robotStatsList;
    private int totalRobotsLost = 0;
    private int totalScentsLeaved = 0;
    private int totalAreaCovered;

    protected ProblemStats(){}

    public ProblemStats(List<RobotStats> robotStatsList) {
        this.robotStatsList = robotStatsList;
        HashSet<Point2D> positionsCovered = new HashSet<>();
        for(RobotStats robotStats : robotStatsList){
            if(robotStats.isWasLost()) totalRobotsLost++;
            if(robotStats.isLeavedScent()) totalScentsLeaved++;

            List<Point2D> path = robotStats.getPathFollowed()
                    .stream()
                    .map((position)->position.getPoint2D())
                    .collect(Collectors.toList());
            positionsCovered.addAll(path);
        }
        totalAreaCovered = positionsCovered.size();
    }

    public String generateOutput() {
        StringBuilder sb = new StringBuilder();
        for(RobotStats robotStats : robotStatsList){
            int distanceTraveled = robotStats.getPathFollowed().size();
            Position lastPosition = robotStats.getPathFollowed().get(distanceTraveled-1);
            sb.append(lastPosition);
            if(robotStats.isWasLost()){
                sb.append(" LOST");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public List<RobotStats> getRobotStatsList() {
        return robotStatsList;
    }

    public void setRobotStatsList(List<RobotStats> robotStatsList) {
        this.robotStatsList = robotStatsList;
    }

    public int getTotalRobotsLost() {
        return totalRobotsLost;
    }

    public void setTotalRobotsLost(int totalRobotsLost) {
        this.totalRobotsLost = totalRobotsLost;
    }

    public int getTotalScentsLeaved() {
        return totalScentsLeaved;
    }

    public void setTotalScentsLeaved(int totalScentsLeaved) {
        this.totalScentsLeaved = totalScentsLeaved;
    }

    public int getTotalAreaCovered() {
        return totalAreaCovered;
    }

    public void setTotalAreaCovered(int totalAreaCovered) {
        this.totalAreaCovered = totalAreaCovered;
    }
}
