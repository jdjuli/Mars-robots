package app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.LinkedList;
import java.util.List;

@Entity
public class ProblemInstance {
    @Id
    private Long id;
    @OneToOne
    private MarsMap marsMap;
    @OneToMany
    private List<Robot> robots;

    protected ProblemInstance(){}

    public ProblemInstance(MarsMap marsMap, List<Robot> robots) {
        this.marsMap = marsMap;
        this.robots = robots;
    }

    public ProblemStats solve(){
        List<RobotStats> robotStatsList = new LinkedList<>();
        RobotStats currentRobotStats;
        for(Robot robot : robots){
            currentRobotStats = robot.moveOver(marsMap);
            robotStatsList.add(currentRobotStats);
        }
        return new ProblemStats(robotStatsList);
    }
}
