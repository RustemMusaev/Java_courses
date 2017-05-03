package ru.rustem.dao;

import org.apache.log4j.Logger;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rustem.model.Product;

import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {
    @SpringBean
    private SessionFactory sessionFactory;
    private static final Logger log = Logger.getLogger(ProductDaoImpl.class);

    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = getSession().createQuery("FROM Product").list();
        return products;
    }

    @Override
    public List<Product> findPagination(Integer first, Integer count) {
        Query query = getSession().createQuery("From Product");
        query.setFirstResult(first);
        query.setMaxResults(count);
        List<Product> products = query.list();
        return products;
    }

    @Override
    public Integer save(Product product) {
        getSession().saveOrUpdate(product);
        Integer id = product.getId();
        if (id != null && log.isInfoEnabled()) {
            log.info("Save Product id = " + id);
        }
        return id;
    }

    @Override
    public Product find(Integer id) {
        return (Product) getSession().createQuery("FROM Product p where id = :id").setParameter("id", id).uniqueResult();
    }

    @Override
    public boolean delete(Product product) {
        Integer id = product.getId();
        getSession().delete(product);
        if (find(id) == null) {
            if (log.isInfoEnabled()) {
                log.info("Delete Product id = " + id);
            }
            return true;
        }
        return false;
    }

    Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
