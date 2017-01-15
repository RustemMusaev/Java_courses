package temp;

import dao.UsersDao;
import factory.UserDaoFactory;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dispatcher {
    UsersDao usersDao;

    public Dispatcher(){
          usersDao=UserDaoFactory.getInstance().getUsersDao();
    }

    public void dispatch(List<String> requeststring,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       if (requeststring==null){
           request.getRequestDispatcher("/AddUser").forward(request, response);
       }
        if (requeststring.get(1).equals("delete")){
       try {
            request.getRequestDispatcher("/DeleteUser?id="+Integer.parseInt(requeststring.get(2))).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
     }
        if (requeststring.get(0).equals("GET")){
            try {
                request.getRequestDispatcher("/FindAllCarsForUser?id="+Integer.parseInt(requeststring.get(1))).forward(request, response);////
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public  void deleteuser (int id,HttpServletRequest request, HttpServletResponse response) throws ServletException {

            request.setAttribute("name",usersDao.find(id).getName());
            //usersDao.delete(Integer.parseInt(request.getParameter("id")));
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
