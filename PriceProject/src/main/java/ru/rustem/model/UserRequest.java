package ru.rustem.model;

public class UserRequest {
    private String category;
    private String product;
    private Double min_price;
    private Double max_price;

    public UserRequest(String category, String product, Double min_price, Double max_price) {
        this.category = category;
        this.product = product;
        this.min_price = min_price;
        this.max_price = max_price;
    }

    public UserRequest() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getMin_price() {
        return min_price;
    }

    public void setMin_price(Double min_price) {
        this.min_price = min_price;
    }

    public Double getMax_price() {
        return max_price;
    }

    public void setMax_price(Double max_price) {
        this.max_price = max_price;
    }
}
