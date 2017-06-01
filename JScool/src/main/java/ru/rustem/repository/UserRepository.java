package ru.rustem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.rustem.model.User;

/**
 * This interface extend JpaRepository, which the have nested CRUD methods(Spring JPA)
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByToken(String token);

    User findUserByLogin(String login);
}
