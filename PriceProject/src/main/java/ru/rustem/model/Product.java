package ru.rustem.model;

import javax.persistence.*;


@Entity
@Table(name = "prod")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.FIELD)
    @Column(name = "prod_id")
    private Integer id;
    @Access(AccessType.FIELD)
    @Column(name = "prod_name")
    private String name;
    @Access(AccessType.FIELD)
    @Column(name = "prod_price")
    private Double price;
    @ManyToOne
    @JoinColumn(name = "prod_cat_id")
    private Category category;

    public Product(String name, Double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
