package ru.rustem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.rustem.model.Form;
import ru.rustem.model.User;
import ru.rustem.service.FormService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    FormService formService;

    @GetMapping(value = "/register")
    public String viewRegister(Map<String, Object> model) {
        model.put("form", new Form());
        return "register";
    }

    @PostMapping(value = "/register")
    public String doRegister(@Valid Form form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        if (formService.save(form) != null) {
            return "registerSuccess";
        } else {
            return "registerError";
        }
    }

    @RequestMapping(value = "/admin")
    @ResponseBody
    ModelAndView showForms() {
        ModelAndView modelAndView = new ModelAndView("admin");
        List<Form> forms = formService.findAll();
        modelAndView.addObject("forms", forms);
        return modelAndView;
    }

    @DeleteMapping(value = "/forms/{userid}")
    ModelAndView deleteUser(@PathVariable("userid") Integer userid, Model model, HttpServletResponse response) {
        if (formService.delete(userid)) {
            RedirectView view = new RedirectView("/admin", true);
            view.setExposeModelAttributes(false);
            return new ModelAndView(view);
        } else {
            RedirectView view = new RedirectView("/404", true);
            view.setExposeModelAttributes(false);
            return new ModelAndView(view);
        }
    }

    @GetMapping(value = "/forms/{userid}")
    public String updateUser(@PathVariable("userid") String id, Model model) {
        Form form = formService.findById(Integer.parseInt(id));
        if (form != null) {
            model.addAttribute("form", form);
            return "regUpd";
        } else {
            return "/404";
        }
    }

    @PutMapping(value = "/registerUpdate/{id}")
    ModelAndView updateForm(@PathVariable("id") String id, @Valid Form form, BindingResult result, Model model, HttpServletResponse response) {
        if (result.hasErrors()) {
            RedirectView view = new RedirectView("/forms/"+id+"", true);
            view.setExposeModelAttributes(false);
            return new ModelAndView(view);
        }
        if (formService.update(form, Integer.parseInt(id)) != null) {
            RedirectView view = new RedirectView("/admin", true);
            view.setExposeModelAttributes(false);
            return new ModelAndView(view);
        } else {
            RedirectView view = new RedirectView("/404", true);
            view.setExposeModelAttributes(false);
            return new ModelAndView(view);
        }
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "loginpage";
    }

    @GetMapping(value = "/")
    public String homePage() {
        return "index";
    }

    @RequestMapping(value = "/404")
    public String errorPage() {
        return "404";
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error",required = false) String error,
                                  @RequestParam(value = "logout",	required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid Credentials provided.");
        }

        if (logout != null) {
            model.addObject("message", "Logged out from JournalDEV successfully.");
        }

        model.setViewName("loginPage");
        return model;
    }

}
