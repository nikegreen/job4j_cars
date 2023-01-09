# job4j_cars
## О проекте
"АвтоМаг"  сайт по продаже машин. 
### Возможности
На сайте должны быть обьявления о продаже машин. 
Объявления состоят из: <br>
- описания
- марка машины
- тип кузова
- фото
- статус (продаётся/продано)

## Стек технологий
- java 17
- git 
- maven
- Spring boot
- thymeleaf
- liquidbase
- h2 (h2database)
- junit 5

## Необходимое ПО
### - git
Установить с сайта: https://git-scm.com/downloads

### - java 17
Установить с сайта: https://www.oracle.com/java/technologies/downloads/

### - IntelliJ IDEA 2022.3.1 (Community Edition)
Установить с сайта: https://www.jetbrains.com/ru-ru/idea/

### - Maven 3.8
Установить с сайта: https://maven.apache.org/install.html

### - PostgreSQL 14
Установить с сайта: https://www.postgresql.org/download/

## Запуск проекта

### 1. Клонирование проекта с github.
Создайте каталог "job4j_car" в который будете клонировать проект.<br>
```c:\>mkdir  job4j_car``` <br>

Ссылка на проект: https://github.com/nikegreen/job4j_car.git <br>
Войдите внутрь своего каталога:<br>
```c:\>cd  job4j_car``` <br>
Склонируйте проект командой.
```c:\job4j_car\>git clone https://github.com/nikegreen/job4j_car.git``` <br>

### 2. Создание базы данных:
Запустите окно команд БД (ПУСК -> Все программы -> PostgreSQL 15 - SQL Shell(psql)).
Введите пользователя и пароль б.д.
Создайте б.д. командой: "create database car" <br>
```
Server [localhost]:
Database [postgres]:
Port [5432]:
Username [postgres]:
Пароль пользователя postgres:
psql (15.1)
ПРЕДУПРЕЖДЕНИЕ: Кодовая страница консоли (866) отличается от основной
                страницы Windows (1251).
                8-битовые (русские) символы могут отображаться некорректно.
                Подробнее об этом смотрите документацию psql, раздел
                "Notes for Windows users".
Введите "help", чтобы получить справку.

postgres=# create database car;
 ``` 
### 3. Создание таблиц и инициализация таблиц
В папке "job4j_car\db\script" нужно выполнить последовательно 2 скрипта:<br>

#### 3.1 Создание таблиц
001_ddl_create_users_sessions_tickets_rooms_seats_movies_table.sql <br>

#### 3.2 Инициализация таблиц
002_dml_insert_users_rooms_seats_movies_sessions.sql

### 4. Настройка конфигурации БД в Job4j_car
В папке "job4j_car\srс\main\resources" в текстовом редакторе измените настройки в файле "db.properties".
```
jdbc.url=jdbc:postgresql://127.0.0.1:5432/car
jdbc.driver=org.postgresql.Driver
jdbc.username=postgres
jdbc.password=password
```
если у вас они отличаются.

### 5. Компиляция.
```
mvn clean
mvn compile
mvn test
```
### 6. Запуск сервиса.
```
cd c:\job4j_car\target
java -jar job4j_car-1.0-SNAPSHOT.jar 
```
### 7. Запуск клиента.
Открываем любым web - браузером адрес:
```
http://localhost:8080/index
```

## FEEDBACK
Все предложения и замечания направлять job4j_car.nikegreen@yandex.ru <br>
Проект учебный. Используйте на свой страх и риск.
Я не несу ответственности за любой прямой и косвенный ущерб.
