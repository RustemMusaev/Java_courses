package ru.itis.server.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itis.server.models.User;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Integer> {

}
