package ru.rustem.service;

import ru.rustem.model.Product;
import ru.rustem.model.Transaction;
import ru.rustem.model.User;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAll();

    Integer save(Transaction transaction);

    Integer shop(Product product, User user);
}
