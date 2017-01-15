package servlet;

import com.sun.rowset.CachedRowSetImpl;
import dao.CarsDao;
import dao.UsersDao;
import factory.CarServiceFactory;
import factory.UserDaoFactory;
import factory.UserServiceFactory;
import models.Car;
import service.CarService;
import service.UserService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CarController extends HttpServlet {
    private UserService userService;
    private CarService carService;
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = UserServiceFactory.getInstanse().getUserService();
        carService = CarServiceFactory.getInstanse().getCarService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> parametrlist= (List<String>) request.getAttribute("parametrlist");
        int id=Integer.parseInt(parametrlist.get(0));
        request.setAttribute("carList",userService.find(id).getMycars());
        request.getRequestDispatcher("/ShowAllCarForUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int carid=Integer.parseInt(request.getParameter("carid"));
        List<String> parametrlist= (List<String>) request.getAttribute("parametrlist");
        int userid=Integer.parseInt(parametrlist.get(0));
        Car car=new Car(carid,request.getParameter("model"),request.getParameter("color"),userService.find(userid));
        carService.save(car);
        response.sendRedirect("/User/"+userid+"/Car");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> parametrlist= (List<String>) request.getAttribute("parametrlist");
        String model,color;
        if (request.getParameter("model")=="") {
            model=carService.find(Integer.parseInt(parametrlist.get(2))).getModel();
        } else {
            model=request.getParameter("model");
        }
        if (request.getParameter("color")=="") {
            color=carService.find(Integer.parseInt(parametrlist.get(2))).getColor();
        } else {
            color = request.getParameter("color");
        }
        carService.update(new Car(Integer.parseInt(parametrlist.get(2)),model,color,userService.find(Integer.parseInt(parametrlist.get(0)))));
        response.sendRedirect("/User/"+parametrlist.get(0)+"/Car");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         List<String> parametrlist= (List<String>) request.getAttribute("parametrlist");
        System.out.println("pre delete");
         carService.delete(Integer.parseInt(parametrlist.get(2)));
        System.out.println("posle");
         response.sendRedirect("/User/"+parametrlist.get(0)+"/Car");
    }
}

