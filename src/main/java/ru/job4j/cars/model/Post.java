package ru.job4j.cars.model;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import org.hibernate.annotations.BatchSize;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * модель данных БД - сущность объявление для хранилища
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "Post")
@Table(name = "auto_post")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private int id;
    @Column(name = "_text")
    private String text;
    private LocalDateTime created;
    @ManyToOne
    @JoinColumn(name = "auto_user_id")
    private User user;

    @BatchSize(size = 150)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "auto_post_id")
    private List<PriceHistory> priceHistories;

    @ManyToMany
    @JoinTable(
            name = "participates",
            joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<User> participates;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private List<Photo> photos;

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", text='" + text + '\''
                + ", created=" + created
                + ", user="
                    + "{id=" + user.getId() + ", login=" + user.getLogin()
                    + ", password=" + user.getPassword() + "}"
                + ", priceHistories=" + priceHistories.toString()
                + ", participates=" + participates
                + ", car=" + car.toString()
                + ", photos=" + photos
        + '}';
    }
}
