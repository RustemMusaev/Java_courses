package ru.rustem.service;


import ru.rustem.model.Form;

import java.util.List;

public interface FormService {
    Integer save(Form form);

    List<Form> findAll();

    Form findById(Integer id);

    List<Form> findByName(String name);

    Form findByPhone(String phone);

    Form findByEmail(String email);

    boolean delete(Integer id);

    Form update(Form form, Integer id);
}
