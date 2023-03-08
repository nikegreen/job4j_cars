package ru.job4j.cars.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
 * модель данных БД - сущность пользователя User
 * для хранилища и окна входа и регистрации (с паролем)
 */
@Entity
@Table(name = "auto_user")
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
}