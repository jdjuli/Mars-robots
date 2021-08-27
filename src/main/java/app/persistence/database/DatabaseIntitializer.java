package app.persistence.database;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import app.model.Movement;
import app.persistence.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseIntitializer {

	@Autowired
	private MovementRepository movementRepository;

	
	@PostConstruct
	public void createData() {
		createBasicMovements();
	}

	private void createBasicMovements(){
		for(Movement movement : DefaultMovements.getAll()){
			if(!movementRepository.existsByLetter(movement.getLetter())){
				movementRepository.save(movement);
			}
		}
	}
}

