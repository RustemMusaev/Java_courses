package ru.rustem.service;

import org.apache.log4j.Logger;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rustem.dao.ProductDao;
import ru.rustem.dao.TransactionDao;
import ru.rustem.model.Product;
import ru.rustem.model.Transaction;
import ru.rustem.model.User;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionDao transactionDao;
    @Autowired
    private ProductDao productDao;

    private static final Logger log = Logger.getLogger(TransactionServiceImpl.class);

    public TransactionServiceImpl() {
    }

    @Override
    public List<Transaction> findAll() {
        return transactionDao.findAll();
    }

    @Override
    public Integer save(Transaction transaction) {
        return transactionDao.save(transaction);
    }

    @Override
    public Integer shop(Product product, User user) {
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setProduct(product);
        transaction.setDate(Calendar.getInstance(TimeZone.getTimeZone("GMT+7:00")).getTime());
        transaction.setPrice(product.getPrice());
        transaction.setCount(product.getCount());
        transactionDao.save(transaction);
        if (transaction.getId() != null) {
            product.setCount(product.getCount() - 1);
            productDao.save(product);
            return product.getCount();
        }
        return null;
    }

    @Override
    public void buyProduct(Product product) {
        if (product.getCount() > 0) synchronized (product) {
            User user = (User) AuthenticatedWebSession.get().getAttribute("userToSession");
            Integer count = shop(product, user);
            if (count != null && log.isInfoEnabled()) {
                    log.info("User = "+ user.getLogin() +"buy product id = " + product.getId());
            }
        }
    }
}
