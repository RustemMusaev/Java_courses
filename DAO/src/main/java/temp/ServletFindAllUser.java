package temp;

import dao.UsersDao;
import factory.UserDaoFactory;
import models.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "User/")
public class ServletFindAllUser extends HttpServlet {

    UsersDao usersDao;
   // FrontController frontController=new FrontController();

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        usersDao= UserDaoFactory.getInstance().getUsersDao();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getPathInfo()==null){
            printhomepage(request, response);
        } else {
           // frontController.dispatchRequestPost(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getPathInfo()==null){
            printhomepage(request, response);
        } else {
           // frontController.dispatchRequestget(request,response);
        }
    }

    public void printhomepage(HttpServletRequest request, HttpServletResponse response) {
        List<User> userList=usersDao.findAll();
        request.setAttribute("userList",userList);
        try {
            request.getRequestDispatcher("/ShowAllUser.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
