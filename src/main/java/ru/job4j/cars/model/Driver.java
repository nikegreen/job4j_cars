package ru.job4j.cars.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import java.io.Serializable;
import static org.hibernate.annotations.CascadeType.MERGE;

/**
 * модель данных БД - сущность водитель
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "driver")
@AllArgsConstructor
@NoArgsConstructor
public class Driver implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private int id;
    private String name;
    @ManyToOne
    @Cascade({MERGE})
    @JoinColumn(name = "user_id")
    private User user;
}