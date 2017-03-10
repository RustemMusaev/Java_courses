package spring.dto;

public class CarDto {

    private Integer id;
    private String model;
    private String color;
    private UserDto userDto;

    public CarDto() {
    }
    private CarDto(Builder builder){
        this.id=builder.id;
        this.model=builder.model;
        this.color=builder.color;
        this.userDto=builder.userDto;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
    public Integer getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public static class Builder{
        private Integer id;
        private String model;
        private String color;
        private UserDto userDto;

        public Builder id(Integer value){
            this.id=value;
            return this;
        }
        public Builder model(String value){
            this.model=value;
            return this;
        }
        public Builder color(String value){
            this.color=value;
            return this;
        }
        public Builder id(UserDto value){
            this.userDto=value;
            return this;
        }
        public CarDto build(){
            return new CarDto(this);
        }
    }
}
