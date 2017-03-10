package spring.converter;

import spring.dto.CarDto;
import spring.models.Car;

public class CarToCarDtoConverter {
    public static CarDto convertWithoutUser(Car car){
        return new CarDto.Builder().
                id(car.getId())
                .model(car.getModel())
                .color(car.getColor())
                .build();
    }
    public static CarDto convertWithUser(Car car){
        CarDto carDto=convertWithoutUser(car);
        carDto.setUserDto(UserToUserDtoConverter.convertWithoutCar(car.getUser()));
        return carDto;
    }
}
