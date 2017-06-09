package ru.rustem.service;

import org.apache.log4j.Logger;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rustem.dao.ProductDao;
import ru.rustem.model.Product;
import ru.rustem.model.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    private static final Logger log = Logger.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl() {
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public List<Product> findPagination(Integer first, Integer count) {
        return productDao.findPagination(first, count);
    }

    @Override
    public Integer save(Product product) {
        Double price = new BigDecimal(product.getPrice()).setScale(2, RoundingMode.HALF_UP).doubleValue();
        product.setPrice(price);
        User user = (User) AuthenticatedWebSession.get().getAttribute("userToSession");
        if (log.isInfoEnabled()) {
            log.info("User = "+ user.getLogin() +"create Product id = " + product.getId());
        }
        return productDao.save(product);
    }

    @Override
    public Product find(Integer id) {
        return productDao.find(id);
    }

    @Override
    public boolean delete(Product product) {
        return productDao.delete(product);
    }
}
