package ru.rustem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.rustem.converter.DtoToStreetConverter;
import ru.rustem.converter.StreetToDtoConverter;
import ru.rustem.dao.StreetDao;
import ru.rustem.dto.StreetDto;
import ru.rustem.exception.IdNotUniqueException;
import ru.rustem.exception.NotFoundException;
import ru.rustem.model.Street;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StreetServiceImpl implements StreetService {

    @Autowired
    private StreetDao dao;

    @Override
    public Integer save(StreetDto model, int id) {
        try {
            Street street = new Street(id,model.getName(), model.getLength());
            return dao.save(street);
        } catch (DuplicateKeyException e) {
            throw  new IdNotUniqueException(model.getName(),id);
        }
    }

    @Override
    public StreetDto find(Integer id) {
        try {
            return new StreetToDtoConverter().convert(dao.find(id));
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Street",id);
        }
    }

    @Override
    public void update(StreetDto model, int id) {
        Street street = new Street(id, model.getName(), model.getLength());
        dao.update(street);
    }

    @Override
    public List<StreetDto> findAll() {
        List<StreetDto> streetDtos = dao.findAll().stream()
                .map(x->new StreetToDtoConverter().convert(x)).collect(Collectors.toList());
        return streetDtos;
    }

    @Override
    public int[] saveBatch(List<StreetDto> list) {
        List<Street> streets = list.stream().map(x->new DtoToStreetConverter().convert(x)).collect(Collectors.toList());
        return dao.saveBatch(streets);
    }
}
