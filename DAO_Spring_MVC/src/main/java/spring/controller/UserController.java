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

    @RequestMapping(value = "/User", method = RequestMethod.GET)
    @ResponseBody
    ModelAndView ShowAllUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ShowAllUser");
        List<User> user=userService.findAll();
        modelAndView.addObject("userList",user);
        return modelAndView;
    }
  /*  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("userList", userService.findAll());
        request.getRequestDispatcher("/ShowAllUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User(Integer.parseInt(request.getParameter("id")), request.getParameter("name"), Integer.parseInt(request.getParameter("age")));
        userService.save(user);
        request.setAttribute("userList", userService.findAll());
        request.getRequestDispatcher("/ShowAllUser.jsp").forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> parametrlist = (List<String>) request.getAttribute("parametrlist");
        String name;
        int age;
        if (request.getParameter("name") == "") {
            name = userService.find(Integer.parseInt(parametrlist.get(0))).getName();
        } else {
            name = request.getParameter("name");
        }
        if (request.getParameter("age") == "") {
            age = userService.find(Integer.parseInt(parametrlist.get(0))).getAge();
        } else {
            age = Integer.parseInt(request.getParameter("age"));
        }
        userService.update(new User(Integer.parseInt(parametrlist.get(0)), name, age));
        HttpSession httpSession = request.getSession(false);
        httpSession.setAttribute("idupdate", parametrlist.get(0));
        httpSession.setAttribute("userList", userService.findAll());
        httpSession.setAttribute("namedelete", null);
        response.sendRedirect("/ShowAllUser.jsp");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> parametrlist = (List<String>) request.getAttribute("parametrlist");
        HttpSession httpSession = request.getSession(false);
        httpSession.setAttribute("namedelete", userService.find(Integer.parseInt(parametrlist.get(0))).getName());
        userService.delete(Integer.parseInt(parametrlist.get(0)));
        httpSession.setAttribute("userList", userService.findAll());
        httpSession.setAttribute("idupdate", null);
        response.sendRedirect("/ShowAllUser.jsp");
    }*/
}
