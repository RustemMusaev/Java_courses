package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.models.User;
import spring.service.UserService;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    //showUsers
    @GetMapping(value = "/users")
    @ResponseBody ModelAndView showUsers() {
        ModelAndView modelAndView = new ModelAndView("users");
        List<User> userList = userService.findAll();
        modelAndView.addObject("users", userList);
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
    //addUser
    @PostMapping(value = "/users")
    @ResponseBody ModelAndView addUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView("users");
        userService.save(user);
        List<User> userList = userService.findAll();
        modelAndView.addObject("users", userList);
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
    //deleteUser
    @DeleteMapping(value = "/users/{userid}")
    public String deleteUser(@PathVariable("userid") Integer userid) {
        userService.delete(userid);
       return "redirect:/users";
    }
    //updateUser(POST)
    @PostMapping(value = "/users/{userid}")
    @ResponseBody ModelAndView updateUser(@PathVariable("userid") String userid) {
        ModelAndView modelAndView = new ModelAndView("userUpdate");
        User user=userService.find(Integer.parseInt(userid));
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    //updateUser(PUT)
    @PutMapping(value = "/users/{userid}")
    public String updateUser(@PathVariable("userid") Integer userid, @RequestParam("name") String name,
                             @RequestParam("age") String age) {
        User user=userService.find(userid);
        if (name.equals("")) {
            } else {
            user.setName(name);
        }
        if (age.equals("")){
        } else {
            user.setAge(Integer.parseInt(age));
        }
        userService.update(user);
        return "redirect:/users";
    }
}
