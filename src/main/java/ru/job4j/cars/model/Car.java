package ru.job4j.cars.model;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import java.util.Set;

/**
 * Модель данных таблицы с информацией об автомобилях
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @ManyToMany
    @JoinTable(
            name = "history_owner",
            joinColumns = { @JoinColumn(name = "car_id") },
            inverseJoinColumns = { @JoinColumn(name = "driver_id") }
    )
    private Set<Driver> owners;
}
