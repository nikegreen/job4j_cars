package ru.job4j.cars.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDto {

//    public UserDto() {}

    public UserDto(User user) {
        id = user.getId();
        login = user.getLogin();
    }

    @Getter
    private int id;

    @Getter
    private String login;
}
