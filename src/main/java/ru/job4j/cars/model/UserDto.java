package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * DTO User пользователя для передачи данных на страницу (нет пароля)
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class UserDto {
    private int id;

    private String login;

    public UserDto(User user) {
        id = user.getId();
        login = user.getLogin();
    }
}
