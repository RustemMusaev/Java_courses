package ru.rustem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rustem.model.Form;

import java.util.List;

/**
 * This interface extend JpaRepository, which the have nested CRUD methods(Spring JPA)
 */
public interface FormRepository extends JpaRepository<Form, Integer> {

   // List<Form> findFormByNameStartWith(String name);

}
