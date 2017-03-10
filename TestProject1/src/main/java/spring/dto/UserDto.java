package spring.dto;

import java.util.List;

public class UserDto {

    private Integer id;
    private String name;
    private Integer age;
    private List<CarDto> carDtoList;

    public UserDto() {
    }
    private UserDto(Builder builder){
        this.id=builder.id;
        this.name=builder.name;
        this.age=builder.age;
        this.carDtoList=builder.carDtoList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCarDtoList(List<CarDto> carDtoList) {
        this.carDtoList = carDtoList;
    }

    public static class Builder{
        private Integer id;
        private String name;
        private Integer age;
        private List<CarDto> carDtoList;

        public Builder id(Integer value){
            this.id=value;
            return this;
        }
        public Builder name(String value){
            this.name=value;
            return this;
        }
        public Builder age(Integer value){
            this.age=value;
            return this;
        }
        public Builder mycars(List<CarDto> value){
            this.carDtoList=value;
            return this;
        }
        public UserDto build(){
            return new UserDto(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public List<CarDto> getCarDtoList() {
        return carDtoList;
    }
}
