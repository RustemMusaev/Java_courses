package ru.rustem.dataView;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import ru.rustem.dao.ProductDao;
import ru.rustem.model.Product;

import java.util.Iterator;

public class ProductDataProvider implements IDataProvider<Product> {

    ProductDao productDao;

    public ProductDataProvider(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Iterator<? extends Product> iterator(long first, long count) {
        return productDao.findPagination((int) first, (int) count).iterator();
    }

    @Override
    public long size() {
        return productDao.findAll().size();
    }

    @Override
    public IModel<Product> model(Product object) {
        return new DetachableProductModel(object, productDao);
    }

    @Override
    public void detach() {

    }
}
