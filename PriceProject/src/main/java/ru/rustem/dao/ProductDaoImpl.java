package ru.rustem.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.rustem.model.Product;
import ru.rustem.model.UserRequest;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

    private SessionFactory sessionFactory;

    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> findAnyParam(UserRequest userRequest) {
        Criteria criteria = getSession().createCriteria(Product.class);
        if (userRequest.getMin_price() != null) {
            criteria.add(Restrictions.ge("price", userRequest.getMin_price()));
        }
        if (userRequest.getMax_price() != null) {
            criteria.add(Restrictions.le("price", userRequest.getMax_price()));
        }
        if (!userRequest.getProduct().equals("")) {
            criteria.add(Restrictions.ilike("name", userRequest.getProduct(), MatchMode.START));
        }
        if (!userRequest.getCategory().equals("")) {
            criteria.createCriteria("category").add(Restrictions.ilike("name", userRequest.getCategory(), MatchMode.START));
        }
        List<Product> results = criteria.list();
        return results;
    }

    private Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
}
