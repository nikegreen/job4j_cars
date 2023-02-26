package ru.job4j.cars.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 * модель данных БД - сущность модель авто
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "car_model")
@NoArgsConstructor
@AllArgsConstructor
public class CarModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    @Column(name = "marc_id")
    private int marcId;
    @Column(name = "body_id")
    private int bodyId;
}
