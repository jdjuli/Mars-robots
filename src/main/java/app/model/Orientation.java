package app.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

public enum Orientation implements Serializable {
    N, E, S, W
}
