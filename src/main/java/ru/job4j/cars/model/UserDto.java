package ru.job4j.cars.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDto {
    @Getter
    private final int id;

    @Getter
    private final String login;

    public UserDto(User user) {
        id = user.getId();
        login = user.getLogin();
    }
}
