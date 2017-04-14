package ru.rustem.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "cat")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.FIELD)
    @Column(name = "cat_id")
    private Integer id;
    @Access(AccessType.FIELD)
    @Column(name = "cat_name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products = new LinkedList<>();

    public Category(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public Category() {
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
