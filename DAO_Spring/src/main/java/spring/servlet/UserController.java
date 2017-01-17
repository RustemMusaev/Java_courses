package spring.servlet;

import org.springframework.context.ApplicationContext;
import spring.models.User;
import spring.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserController extends HttpServlet {
    private UserService userService;

    public void init(ServletConfig config) throws ServletException {
        ApplicationContext context = (ApplicationContext)config.getServletContext().getAttribute("jdbcSpringContext");
        userService = (UserService) context.getBean("userService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("userList",userService.findAll());
        request.getRequestDispatcher("/ShowAllUser.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user=new User(Integer.parseInt(request.getParameter("id")),request.getParameter("name"),Integer.parseInt(request.getParameter("age")));
        userService.save(user);
        request.setAttribute("userList",userService.findAll());
        request.getRequestDispatcher("/ShowAllUser.jsp").forward(request, response);
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> parametrlist= (List<String>) request.getAttribute("parametrlist");
        String name;
        int age;
        if (request.getParameter("name")=="") {
            name=userService.find(Integer.parseInt(parametrlist.get(0))).getName();
        } else {
            name=request.getParameter("name");
        }
        if (request.getParameter("age")=="") {
            age=userService.find(Integer.parseInt(parametrlist.get(0))).getAge();
        } else {
            age = Integer.parseInt(request.getParameter("age"));
        }
        userService.update(new User(Integer.parseInt(parametrlist.get(0)),name,age));
        HttpSession httpSession=request.getSession(false);
        httpSession.setAttribute("idupdate",parametrlist.get(0));
        httpSession.setAttribute("userList",userService.findAll());
        httpSession.setAttribute("namedelete",null);
        response.sendRedirect("/ShowAllUser.jsp");
     }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> parametrlist= (List<String>) request.getAttribute("parametrlist");
        HttpSession httpSession=request.getSession(false);
        httpSession.setAttribute("namedelete",userService.find(Integer.parseInt(parametrlist.get(0))).getName());
        userService.delete(Integer.parseInt(parametrlist.get(0)));
        httpSession.setAttribute("userList",userService.findAll());
        httpSession.setAttribute("idupdate",null);
        response.sendRedirect("/ShowAllUser.jsp");
    }
}
