package ru.rustem.dao;


import ru.rustem.model.Transaction;

import java.util.List;

public interface TransactionDao {
    List<Transaction> findAll();

    Integer save(Transaction transaction);
}
