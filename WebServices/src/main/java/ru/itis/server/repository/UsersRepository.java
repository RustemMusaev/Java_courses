package ru.itis.server.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itis.server.models.User;

public interface UsersRepository extends CrudRepository<User, Integer> {

}
