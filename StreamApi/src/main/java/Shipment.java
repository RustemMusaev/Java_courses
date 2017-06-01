import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Shipment {
    List<Product> getProducts(){
        Product product1 = new Product(new Money(),new BigDecimal(10));
        Product product2 = new Product(new Money(),new BigDecimal(10));
        Product product3 = new Product(new Money(),new BigDecimal(10));
        Product product4 = new Product(new Money(),new BigDecimal(10));
        List<Product> list = new ArrayList<Product>();
        list.add(product1);
        list.add(product2);
        list.add(product3);
        list.add(product4);
        return list;
    };
}
