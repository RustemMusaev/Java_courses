package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody ModelAndView showUsers() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> userList = userService.findAll();
        modelAndView.addObject("users", userList);
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("users");
        return modelAndView;
    }
    //addUser
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody ModelAndView addUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        userService.save(user);
        List<User> userList = userService.findAll();
        modelAndView.addObject("users", userList);
        modelAndView.setViewName("users");
        return modelAndView;
    }
    //deleteUser
    @RequestMapping(value = "/users/{userid}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("userid") int userid) {
        //userService.delete(userid);
       return "redirect:/users";
    }
    //updateUser(POST)
    @RequestMapping(value = "/users/{userid}", method = RequestMethod.POST)
    @ResponseBody ModelAndView updateUser(@PathVariable("userid") String userid) {
        ModelAndView modelAndView = new ModelAndView();
        User user=userService.find(Integer.parseInt(userid));
        modelAndView.addObject("user", user);
        modelAndView.setViewName("userUpdate");
        return modelAndView;
    }
    //updateUser(PUT)
    @RequestMapping(value = "/users/{userid}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable("userid") int userid, @RequestParam("name") String name,
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
