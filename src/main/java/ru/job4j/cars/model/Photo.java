package ru.job4j.cars.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * модель данных БД - сущность фотография
 * фото хранится на диске, в сущности имя файла.
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "photo")
@NoArgsConstructor
@AllArgsConstructor
public class Photo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private int id;
    private String name;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "post_id")
    private int postId;

    @Override
    public String toString() {
        return "Photo{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", fileName='" + fileName + '\''
                + ", post={"
                    + "id=" + getPostId()
                + '}';
    }
}
