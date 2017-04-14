package ru.rustem.dto;

public class ProductDto {

    private String name;
    private String price;
    private String categoryName;

    public ProductDto(String name, String price, String categoryName) {
        this.name = name;
        this.price = price;
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
