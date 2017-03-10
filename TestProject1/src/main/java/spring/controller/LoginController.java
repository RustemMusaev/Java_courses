package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @GetMapping(value = "/")
    @ResponseBody
    ModelAndView loginGet() {
        ModelAndView modelAndView = new ModelAndView("loginpage");
        return modelAndView;
    }

    @GetMapping(value = "/login")
    @ResponseBody
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("loginpage");
        return modelAndView;
    }

    @PostMapping(value = {"/login"})
    ModelAndView loginPost() {
        ModelAndView modelAndView = new ModelAndView("loginpage");
        return modelAndView;
    }
    @GetMapping(value = {"/logout"})
    @ResponseBody
    ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("loginpage");
        //response.setContentType("text/html");
        Cookie loginCookie = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")){
                    loginCookie = cookie;
                    break;
                }
            }
        }

        if(loginCookie != null){
            loginCookie.setMaxAge(0);
            response.addCookie(loginCookie);
        }
        return  modelAndView;
    }

}
