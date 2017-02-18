package ru.itis.server.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itis.server.models.Auto;
import ru.itis.server.models.User;

import java.util.List;


public interface AutosRepository extends CrudRepository<Auto, Integer> {

}
