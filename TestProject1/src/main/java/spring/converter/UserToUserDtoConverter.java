package spring.converter;


import spring.dto.CarDto;
import spring.dto.UserDto;
import spring.models.Car;
import spring.models.User;

import java.util.ArrayList;

public class UserToUserDtoConverter {
    public static UserDto convertWithoutCar(User user){
        UserDto userDto=new UserDto.Builder().id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .build();
    return userDto;
    }

    public static UserDto convertWithCar(User user){
        UserDto userDto=convertWithoutCar(user);
        userDto.setCarDtoList(new ArrayList<CarDto>());
        for (Car car:user.getMycars()){
            CarDto carDto= CarToCarDtoConverter.convertWithoutUser(car);
            userDto.getCarDtoList().add(carDto);
        }
        return userDto;
    }

}
