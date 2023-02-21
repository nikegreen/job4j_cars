package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDto {
    private int id;

    private String login;

    public UserDto(User user) {
        id = user.getId();
        login = user.getLogin();
    }
}
