package temp;

import dao.UsersDao;
import factory.UserDaoFactory;
import models.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteUser")
public class DeleteUser extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersDao usersDao= UserDaoFactory.getInstance().getUsersDao();
        HttpSession httpSession=request.getSession(true);
        httpSession.setAttribute("name",usersDao.find(Integer.parseInt(request.getParameter("id"))).getName());
        //usersDao.delete(Integer.parseInt(request.getParameter("id")));
        List<User> userList=usersDao.findAll();
        httpSession.setAttribute("userList",userList);
        httpSession.setAttribute("carList",null);
        response.sendRedirect(request.getContextPath() + "/User");
        httpSession=request.getSession(false);
        //response.sendRedirect("/User");
        //request.getRequestDispatcher("/User").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
















}
