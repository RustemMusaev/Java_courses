package temp;

import dao.UsersDao;
import factory.UserDaoFactory;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddUser")
public class AddUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersDao usersDao= UserDaoFactory.getInstance().getUsersDao();
        int id= Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        int age= Integer.parseInt(request.getParameter("age"));
        usersDao.save(new User(id,name,age));

        List<User> userList=usersDao.findAll();
        HttpSession httpSession=request.getSession(true);
        httpSession.setAttribute("name",null);
        httpSession.setAttribute("carList",null);
        request.setAttribute("userList",userList);
        response.sendRedirect(request.getContextPath() + "/User");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hello");
    }
}
