package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.service.ChatUserService;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestChatUserController {
/*
    @Autowired
    ChatUserService chatUserService;

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
    @PostMapping("/users")
    public ResponseEntity<UserDto> signUp(@RequestBody UserDataForRegistrationDto user) {
        return new ResponseEntity<>(usersService.registerUser(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestHeader("password") String password,
                                        @RequestHeader("login") String login) {
        String token = usersService.login(password, login);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-Token", token);
        return new ResponseEntity<>(null, headers, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(usersService.getUsers(), HttpStatus.ACCEPTED);
    }*/
}
