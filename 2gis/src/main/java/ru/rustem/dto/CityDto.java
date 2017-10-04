package ru.rustem.dto;

public class CityDto {
    private String name;
    private Double area;
    private Integer countPeople;

    public CityDto(String name, Double area, Integer countPeople) {
        this.name = name;
        this.area = area;
        this.countPeople = countPeople;
    }

    public CityDto() {
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
