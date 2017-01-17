package spring.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FrontController extends HttpServlet {

    protected void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url=request.getRequestURI();
        if (url!=null){
            List<String> parametrlist=parseUrl(url);
            if (parametrlist.size()>2 && parametrlist.get(0).equals("User") && parametrlist.get(2).equals("Car")){
                parametrlist.remove(0);
                request.setAttribute("parametrlist",parametrlist);
                request.getRequestDispatcher("/CarController").forward(request, response);
            } else if (parametrlist.get(0).equals("User")){
                parametrlist.remove(0);
                request.setAttribute("parametrlist",parametrlist);
                request.getRequestDispatcher("/UserController").forward(request, response);
            } else {
                System.out.println("URL is incorrect");
            }
        } else {
            System.out.println("URL is emply");
        }
    }
    private List<String> parseUrl(String url){
        List<String> urllist=new ArrayList<>();
        StringTokenizer stringTokenizer=new StringTokenizer(url,"/");
            while (stringTokenizer.hasMoreTokens()){
            urllist.add(stringTokenizer.nextToken());
        }
        return urllist;
    }
}
