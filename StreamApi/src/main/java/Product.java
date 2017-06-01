import java.math.BigDecimal;

public class Product {
    private Money price;
    private BigDecimal count;

    public Product(Money money, BigDecimal i) {
        this.price = money;
        this.count = i;
    }

    public Money getPrice() {
        return price;
    }

    public BigDecimal getAmount() {
        return count;
    }

}
