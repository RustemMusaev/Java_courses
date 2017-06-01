package ru.rustem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.rustem.model.User;
import ru.rustem.model.UserLogin;
import ru.rustem.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    private Integer currentPage = 1;

    @GetMapping(value = "/logout")
    @ResponseBody
    public HttpStatus signOut(HttpServletRequest request) {
        String token = getCookie(request);
        if (token != null && userService.closeSession(token)) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @GetMapping(value = "/register")
    public String viewRegister(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        return "register";
    }

    @PostMapping(value = "/register")
    public String doRegister(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        model.addAttribute("user", user);
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/loginpage")
    public String loginPage() {
        return "redirect:/users";
    }

    @PostMapping(value = "/users/{userid}")
    public String updateUser(@PathVariable("userid") String userid, Model model) {
        User user = userService.findById(Integer.parseInt(userid));
        model.addAttribute("user", user);
        return "regUpd";
    }

    @PostMapping(value = "/users/update/{userid}")
    public String update(@PathVariable("userid") String userid,
                         @Valid User user, BindingResult result, Model model,HttpServletResponse response) {
        if (result.hasErrors()) {
            return "regUpd";
        }
        user.setId(Integer.parseInt(userid));
        userService.save(user);
        return "registerSuccess";
    }

    @GetMapping(value = {"/","/login"})
    public String homePage() {
        //ModelAndView modelAndView = new ModelAndView("loginpage");
        return "loginpage";
    }

    @GetMapping(value = "/users")
    @ResponseBody
    ModelAndView showNews() {
        ModelAndView modelAndView = new ModelAndView("users");
        Page<User> page = userService.getPageNews(currentPage);
        modelAndView.addObject("pageCount", page.getTotalPages());
        List<User> users = new ArrayList<>(page.getContent());
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping(value = "/users/page/{currentPage}")
    @ResponseBody
    ModelAndView selectPage(@PathVariable("currentPage") Integer setPage) {
        ModelAndView modelAndView = new ModelAndView("users");
        Page<User> page = userService.getPageNews(setPage);
        modelAndView.addObject("pageCount", page.getTotalPages());
        List<User> users = new ArrayList<>(page.getContent());
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping(value = "/404")
    public String errorPage() {
        ModelAndView modelAndView = new ModelAndView("404");
        return "404";
    }

    @DeleteMapping(value = "/users/{userid}")
    public String deleteUser(@PathVariable("userid") Integer userid, Model model, HttpServletResponse response) {
        if (userService.findById(userid) != null) {
            userService.delete(userid);
            return "redirect: /users";
        } else return "404";
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserLogin> login(@RequestBody UserLogin userLogin, HttpServletRequest request) {
        String cookieValue = getCookie(request);
        System.out.println("cookie = " + cookieValue);
        if (cookieValue == null || cookieValue.equals("null")) {
            User user = userService.userIsCorrect(userLogin);
            if (user != null) {
                String token = user.getToken();
                HttpHeaders headers = new HttpHeaders();
                headers.add("Access-Control-Expose-Headers", "Auth-Token");
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add("Auth-Token", token);
                return new ResponseEntity<>(userLogin, headers, HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            if (userService.findUserByToken(cookieValue) != null) {
                return new ResponseEntity<>(userLogin, HttpStatus.OK);
            } else {
                new RuntimeException("TODO");
                return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
            }
        }
    }

    public String getCookie(HttpServletRequest request) {
        String cookieName = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Auth-Token")) {
                    cookieName = cookie.getValue();
                }
            }
        }
        return cookieName;
    }

    public Date stringToDateFormat(String string) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = simpleDateFormat.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
