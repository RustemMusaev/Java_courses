package ru.rustem.converter;


import ru.rustem.dto.ProductDto;
import ru.rustem.model.Product;

public class ProductToProductDtoConverter {
    public static ProductDto productToProductDtoConverter(Product product){
        return new ProductDto(product.getName(),String.format("%1$,.2f", product.getPrice()),product.getCategory().getName());
    }
}
