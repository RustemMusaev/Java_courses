package spring.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.converter.UserToUserDtoConverter;
import spring.dto.UserDto;
import spring.models.Car;
import spring.models.User;
import spring.service.UserService;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestUserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/users")
    public List<UserDto> showUsers(){
        List<User> userList=userService.findAll();
        List<UserDto> result=new ArrayList<UserDto>();
        for (User user:userList){
            result.add(UserToUserDtoConverter.convertWithCar(user));
        }
        return result;
    }

    @PostMapping(value = "/users")
    public ResponseEntity<List<UserDto>> addUser(@RequestBody UserDto userDto){
        User user=new User();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        userService.save(user);
        List<UserDto> result = new ArrayList<>();
        List<User> userList = userService.findAll();
        for (User currentUser : userList) {
            result.add(UserToUserDtoConverter.convertWithCar(currentUser));
        }
        ResponseEntity<List<UserDto>> response = new
                ResponseEntity<List<UserDto>>(result, HttpStatus.CREATED);
        return response;
    }
}
