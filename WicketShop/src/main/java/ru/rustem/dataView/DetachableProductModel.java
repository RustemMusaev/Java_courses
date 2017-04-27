package ru.rustem.dataView;

import org.apache.wicket.model.LoadableDetachableModel;
import ru.rustem.dao.ProductDao;
import ru.rustem.model.Product;

public class DetachableProductModel extends LoadableDetachableModel<Product> {
    private final int id;

    private ProductDao productDao;

    public DetachableProductModel(Product product, ProductDao productDao) {
        this(product.getId());
        this.productDao = productDao;
    }

    public DetachableProductModel(int id) {
        if (id == 0) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(id).hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (obj instanceof DetachableProductModel) {
            DetachableProductModel other = (DetachableProductModel) obj;
            return other.id == id;
        }
        return false;
    }

    @Override
    protected Product load() {
        return productDao.find(id);
    }

}
