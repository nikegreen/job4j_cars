package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

/**
 * DTO объявление
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PostDto implements Serializable {
    final static DateTimeFormatter CUSTOM_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @EqualsAndHashCode.Include
    private int id;
    private String text;
    private String created;
    private UserDto user;
    private int price;
    private Car car;
    private List<Photo> photos;
    private int statusId;

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

    /**
     * Функция помошник для конвертации String в LocalDateTime
     * @param postDto DTO объявления
     * @return тип {@link java.time.LocalDateTime} дата и время из DTO объявления
     */
    public static LocalDateTime getCreate(PostDto postDto) {
        return LocalDateTime.parse(postDto.getCreated(), CUSTOM_FORMATTER);
    }

    /**
     * Конвертация типа Post в PostDto
     * @param post тип Post
     * @return объект типа PostDto с данными из {@param post}
     */
    public static PostDto fromPost(Post post) {
        if (post == null) {
            return null;
        }
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setText(post.getText());
        postDto.setCreated(post.getCreated().format(CUSTOM_FORMATTER));
        postDto.setCar(post.getCar());
        PriceHistory priceHistory = post.getPriceHistories()
                .stream()
                .max(Comparator.comparing(PriceHistory::getCreated))
                .orElse(new PriceHistory());
        postDto.setPrice((int) priceHistory.getAfter());
        postDto.setStatusId(
                priceHistory.getAfter() > 0 &&  priceHistory.getAfter() == priceHistory.getBefore()
                        ? 2 : 1);
        postDto.setUser(new UserDto(post.getUser()));
        postDto.setPhotos(post.getPhotos());
        return postDto;
    }
}
