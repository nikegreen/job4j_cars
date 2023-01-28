package ru.job4j.cars.model;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private int id;
    private String name;
    @Column(name = "file_name")
    private String fileName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String photosToString() {
        StringBuilder res = new StringBuilder();
        String separator = "";
        for (Photo photo: post.getPhotos()) {
            res.append(separator)
                    .append("{id=")
                    .append(photo.getId())
                    .append(", name=")
                    .append(photo.getName())
                    .append(", fileName=")
                    .append(photo.getFileName())
                    .append(", ")
                    .append(photo.getPost().getId())
                    .append("}");
            separator = ", ";
        }
        return res.toString();
    }

    @Override
    public String toString() {
        return "Photo{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", fileName='" + fileName + '\''
                + ", post={"
                    + "id=" + post.getId()
                    + ", text='" + post.getText() + '\''
                    + ", created=" + post.getCreated()
                    + ", user="
                    + "{id=" + post.getUser().getId()
                    + ", login=" + post.getUser().getLogin()
                    + ", password=" + post.getUser().getPassword() + "}"
                    + ", priceHistories=" + post.getPriceHistories().toString()
                    + ", participates=" + post.getParticipates()
                    + ", car=" + post.getCar().toString()
                    + ", photos=[" + photosToString() + "]}"
                + '}';
    }
}
