package ru.rustem.model;


public class City {

    private Integer id;
    private String name;
    private Double area;
    private Integer countPeople;

    public City(String name, Double area, Integer countPeople) {
        this.name = name;
        this.area = area;
        this.countPeople = countPeople;
    }

    public City(Integer id, String name, Double area, Integer countPeople) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.countPeople = countPeople;
    }

    public City() {
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

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getCountPeople() {
        return countPeople;
    }

    public void setCountPeople(Integer countPeople) {
        this.countPeople = countPeople;
    }

}
