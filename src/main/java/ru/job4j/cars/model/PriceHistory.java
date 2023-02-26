package ru.job4j.cars.model;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * модель данных БД - сущность записи истории цены
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "price_history")
public class PriceHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private int id;
    private long before;
    private long after;
    private LocalDateTime created;

    @Column(name = "auto_post_id")
    private int autoPostId;
}
