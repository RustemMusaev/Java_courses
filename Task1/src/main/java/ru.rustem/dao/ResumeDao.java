package ru.rustem.dao;


import java.util.List;

public interface ResumeDao {

    List<Resume> findAll();

    Integer save(Resume resume);

    List<Resume> findByPosition(String position);
}
