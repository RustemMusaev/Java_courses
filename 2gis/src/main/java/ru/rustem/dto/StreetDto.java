package ru.rustem.dto;


public class StreetDto {
    private String name;
    private Integer length;

    public StreetDto(String name, Integer length) {
        this.name = name;
        this.length = length;
    }

    public StreetDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
