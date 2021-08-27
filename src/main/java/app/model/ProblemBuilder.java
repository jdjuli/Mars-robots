package app.model;

import app.persistence.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@Component
public class ProblemBuilder {

    @Autowired
    private MovementRepository movementRepository;

    public ProblemInstance fromString(String string){
        if( !string.matches("\\d+ \\d+\\r?\\n(\\d+ \\d+ [A-Z]\\r?\\n[A-Z]{1,50}\\r?\\n)+[ \r\n\t]*") ){
            throw new RuntimeException("Invalid input text format.");
        }
        MarsMap marsMap;
        List<Robot> robots = new LinkedList<>();
        Scanner sc = new Scanner(string.trim());
        marsMap = new MarsMap(new Point2D(sc.nextInt(), sc.nextInt()));
        sc.nextLine();
        while(sc.hasNext()) {
            Point2D point = new Point2D(sc.nextInt(), sc.nextInt());
            Position robotPos = new Position(point, Orientation.valueOf(sc.next()));
            sc.nextLine();
            List<Movement> movementList = new LinkedList<>();
            for (char c : sc.nextLine().toCharArray()) {
                movementList.add(movementRepository.findByLetter(c));
            }
            robots.add(new Robot(robotPos, movementList));
        }

        return new ProblemInstance(marsMap, robots);
    }
}
