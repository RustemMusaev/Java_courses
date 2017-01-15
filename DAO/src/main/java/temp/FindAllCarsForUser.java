package temp;

import dao.UsersDao;
import factory.UserDaoFactory;
import models.Car;
import models.User;

import javax.servlet.RequestDispatcher;
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

@WebServlet(name = "FindAllCarsForUser")
public class FindAllCarsForUser extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersDao usersDao = UserDaoFactory.getInstance().getUsersDao();
        HttpSession httpSession=request.getSession(true);
        List<Car> carList = usersDao.find(Integer.parseInt(request.getParameter("id"))).getMycars();
        httpSession.setAttribute("carList", carList);
        httpSession.setAttribute("name", null);
        List<User> userList=usersDao.findAll();
        httpSession.setAttribute("userList",userList);
        response.sendRedirect("/User");
    }
}