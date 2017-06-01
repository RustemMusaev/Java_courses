package ru.rustem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rustem.model.Form;
import ru.rustem.repository.FormRepository;

import java.util.List;

@Service
public class FormServiceImpl implements FormService {

    @Autowired
    FormRepository formRepository;

    public Integer save(Form form) {
        if (form != null) {
            formRepository.save(form);
            return form.getId();
        }
        return null;
    }

    public List<Form> findAll() {
        return formRepository.findAll();
    }

    public Form findById(Integer id) {
        if (id != null) {
            return formRepository.findOne(id);
        }
        return null;
    }

    public List<Form> findByName(String name) {
        return null;
    }

    public Form findByPhone(String phone) {
        return null;
    }

    public Form findByEmail(String email) {
        return null;
    }

    public boolean delete(Integer id) {
        if (findById(id) != null) {
            formRepository.delete(id);
            return true;
        }
        return false;
    }

    public Form update(Form form, Integer id) {
        if (findById(id) != null) {
            form.setId(id);
            formRepository.save(form);
            return form;
        }
        return null;
    }
}
