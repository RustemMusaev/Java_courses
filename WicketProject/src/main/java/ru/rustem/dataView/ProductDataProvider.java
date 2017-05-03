package ru.rustem.dataView;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import ru.rustem.model.Product;
import ru.rustem.service.ProductService;

import java.util.Iterator;

public class ProductDataProvider implements IDataProvider<Product> {

    private ProductService productService;

    public ProductDataProvider(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Iterator<? extends Product> iterator(long first, long count) {
        return productService.findPagination((int) first, (int) count).iterator();
    }

    @Override
    public long size() {
        return productService.findAll().size();
    }

    @Override
    public IModel<Product> model(Product object) {
        return new DetachableProductModel(object, productService);
    }

    @Override
    public void detach() {

    }
}
