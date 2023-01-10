package ru.job4j.cars.model.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.User;

public class UserUsage {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            var userRepository = new UserRepository(sf);
            System.out.println("------------ create user -------------");
            var user = new User();
            user.setLogin("admin");
            user.setPassword("admin");
            userRepository.create(user);
            System.out.println("------------ findAllOrderById() -------------");
            userRepository.findAllOrderById()
                    .forEach(System.out::println);
            System.out.println("------------ findByLikeLogin() -------------");
            userRepository.findByLikeLogin("e")
                    .forEach(System.out::println);
            System.out.println("------------ findById() -------------");
            userRepository.findById(user.getId())
                    .ifPresent(System.out::println);
            System.out.println("------------ findByLogin() -------------");
            userRepository.findByLogin("admin")
                    .ifPresent(System.out::println);
            System.out.println("------------ setPassword() -------------");
            user.setPassword("password");
            System.out.println("------------ update() -------------");
            userRepository.update(user);
            System.out.println("------------ findById() -------------");
            userRepository.findById(user.getId())
                    .ifPresent(System.out::println);
            System.out.println("------------ delete() -------------");
            userRepository.delete(user.getId());
            System.out.println("------------ findAllOrderById() -------------");
            userRepository.findAllOrderById()
                    .forEach(System.out::println);
            System.out.println("------------ final -------------");
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}