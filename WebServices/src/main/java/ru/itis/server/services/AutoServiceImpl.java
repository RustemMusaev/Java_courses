package ru.itis.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.server.models.Auto;
import ru.itis.server.repository.AutosRepository;

import javax.jws.WebService;
import java.util.List;

@Service
@WebService(endpointInterface = "ru.itis.server.services.AutoService")
public class AutoServiceImpl implements AutoService {

    @Autowired
    private AutosRepository autosRepository;

    @Override
    public Auto findOne(Integer id) {
        return autosRepository.findOne(id);
    }

    @Override
    public List<Auto> findAll() {
        return (List<Auto>) autosRepository.findAll();
    }

    @Override
    public Auto save(Auto auto) {
        return autosRepository.save(auto);
    }

    @Override
    public void delete(Integer id) {
        autosRepository.delete(id);
    }
}
