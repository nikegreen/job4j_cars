package ru.job4j.cars.model;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import org.hibernate.annotations.Cascade;
import java.io.Serializable;
import java.util.Set;

import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;
import static org.hibernate.annotations.CascadeType.MERGE;

/**
 * Модель данных - сущность автомобиль
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "car")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private int id;
    private String name;
    @ManyToOne
    @Cascade({SAVE_UPDATE, MERGE})
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @ManyToOne
    @Cascade({SAVE_UPDATE, MERGE})
    @JoinColumn(name = "marc_id")
    private CarMarc marc;

    @ManyToOne
    @Cascade({SAVE_UPDATE, MERGE})
    @JoinColumn(name = "model_id")
    private CarModel model;

    @ManyToMany
    @Cascade({ SAVE_UPDATE, MERGE})
    @JoinTable(
            name = "history_owner",
            joinColumns = { @JoinColumn(name = "car_id") },
            inverseJoinColumns = { @JoinColumn(name = "driver_id") }
    )
    private Set<Driver> owners;
}
