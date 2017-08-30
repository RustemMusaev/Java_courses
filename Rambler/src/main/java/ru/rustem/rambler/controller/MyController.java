package ru.rustem.rambler.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rustem.rambler.model.User;
import ru.rustem.rambler.service.UserService;

@Controller
public class MyController {

    @Autowired
    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(MyController.class);

    @PostMapping("/login")
    public String login(@RequestParam String login, String pwd, Model model) {
        try {
            User user = userService.findByLogin(login);
            if (userService.passwordIsCorrect(user.getId(), user.getPasswordId(), pwd)) {
                model.addAttribute("succesMessage", "Hello, " + user.getName());
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("user " + user.getLogin()+ " is succes ");
                }
                return "succesLogin";
            } else {
                model.addAttribute("errors", "Passwords do not match");
            }
            return "index";
        } catch (Exception ex) {
            model.addAttribute("errors", ex.getMessage());
            return "index";
        }
    }

    @RequestMapping("/")
    public String homePage() {
        return "index";
    }
}
