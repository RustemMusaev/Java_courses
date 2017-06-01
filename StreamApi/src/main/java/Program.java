import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        Shipment shipment1 = new Shipment();
        Shipment shipment2 = new Shipment();
        List<Shipment> shipments = new ArrayList<Shipment>();
        shipments.add(shipment1);
        shipments.add(shipment2);
        calculateTotalPrice(shipments);
    }

    public static Money calculateTotalPrice(Collection<Shipment> shipments){
        Money result = null;
        shipments.stream().map(Shipment::getProducts).collect(Collectors.toList()).forEach(products -> products
                .forEach(product -> result.add(product.getPrice().multiply(product.getAmount()))));
        return result;
    };


}
