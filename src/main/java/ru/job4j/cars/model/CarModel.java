package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "car_model")
@AllArgsConstructor
public class CarModel {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    @Column(name = "marc_id")
    private int marcId;

    @Getter
    @Setter
    @Column(name = "body_id")
    private int bodyId;

    public CarModel() {

    }
}
