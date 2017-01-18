package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.models.User;
import spring.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/User", method = RequestMethod.GET)
    @ResponseBody
    ModelAndView showAllUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ShowAllUser");
        modelAndView.addObject("userList", userService.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/User/{user-id}", method = RequestMethod.POST)
    @ResponseBody
    ModelAndView addUser(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("age") int age) {
        User user = new User(id, "name", age);
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ShowAllUser");
        modelAndView.addObject("userList", userService.findAll());
        return modelAndView;
    }
/*
    @RequestMapping(value = "/User/{user-id}", method = RequestMethod.PUT)
    @ResponseBody
    ModelAndView putUser(
            @PathVariable("user-id") int id,
            @RequestParam("name") String name,
            @RequestParam("age") int age) {
        if ((name.equals(""))) {
            name = userService.find(id).getName();
        }
        if (age == 0) {
            age = userService.find(id).getAge();
        }
        userService.update(new User(id, name, age));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ShowAllUser");
        modelAndView.addObject("userList", userService.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/User/{user-id}", method = RequestMethod.POST)
    @ResponseBody
    ModelAndView deleteUser(
            @RequestParam("user-id") int id) {
        userService.delete(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ShowAllUser");
        modelAndView.addObject("userList", userService.findAll());
        return modelAndView;
    }*/
}
