package ru.rustem.model;

public class Street {

    private Integer id;
    private String name;
    private Integer length;

    public Street(Integer id, String name, Integer length) {
        this.id = id;
        this.name = name;
        this.length = length;
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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Street)) return false;

        Street street = (Street) o;

        if (getId() != null ? !getId().equals(street.getId()) : street.getId() != null) return false;
        if (getName() != null ? !getName().equals(street.getName()) : street.getName() != null) return false;
        return getLength() != null ? getLength().equals(street.getLength()) : street.getLength() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getLength() != null ? getLength().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Street{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", length=" + length +
                '}';
    }
}

