package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PostDto {
    @EqualsAndHashCode.Include
    private int id;

    private String text;
    private LocalDateTime created;

    private UserDto user;

    private int price;

    private Car car;

    private List<Photo> photos;

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", text='" + text + '\''
                + ", created=" + created
                + ", user="
                + "{id=" + user.getId() + ", login=" + user.getLogin() + "}"
                + ", price=" + price
                + ", car=" + car.toString()
                + ", photos=" + photos
                + '}';
    }
    public static PostDto fromPost(Post post) {
        if (post == null) {
            return null;
        }
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setText(post.getText());
        postDto.setCreated(post.getCreated());
        postDto.setCar(post.getCar());
        postDto.setPrice(
                (int)post.getPriceHistories()
                        .stream()
                        .sorted((a,b)->a.getCreated().compareTo(b.getCreated()))
                        .findFirst().get().getAfter()
        );
        postDto.setUser(new UserDto(post.getUser()));
        postDto.setPhotos(post.getPhotos());
        return postDto;
    }
}
