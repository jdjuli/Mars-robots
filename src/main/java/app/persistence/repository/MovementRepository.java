package app.persistence.repository;

import app.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    Movement findByLetter(char letter);
    boolean existsByLetter(char letter);
}
