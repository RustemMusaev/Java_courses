package ru.rustem.dao;

import org.apache.log4j.Logger;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rustem.model.Transaction;

import java.util.List;

@Repository
@Transactional
public class TransactionDaoImpl implements TransactionDao {
    @SpringBean
    private SessionFactory sessionFactory;
    private static final Logger log = Logger.getLogger(TransactionDaoImpl.class);

    public TransactionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Transaction> findAll() {
        return getSession().createQuery("FROM Transaction").list();
    }

    @Override
    public Integer save(Transaction transaction) {
        getSession().saveOrUpdate(transaction);
        Integer id = transaction.getId();
        if (id != null && log.isInfoEnabled()) {
            log.info("Save Transaction id = " + id);
        }
        return id;
    }

    Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
