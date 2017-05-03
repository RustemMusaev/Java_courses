package ru.rustem.dataView;

import org.apache.wicket.model.LoadableDetachableModel;
import ru.rustem.model.Product;
import ru.rustem.service.ProductService;

public class DetachableProductModel extends LoadableDetachableModel<Product> {
    private final int id;

    private ProductService productService;

    public DetachableProductModel(Product product, ProductService productService) {
        this(product.getId());
        this.productService = productService;
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
        return productService.find(id);
    }

}
