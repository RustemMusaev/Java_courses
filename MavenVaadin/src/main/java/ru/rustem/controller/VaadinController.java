package ru.rustem.controller;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import ru.rustem.CustomComponent.MyOther;
import ru.rustem.CustomComponent.MyWeather;
import ru.rustem.CustomComponent.MyKurs;

@Theme("mytheme")
public class VaadinController extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setStyleName("layout");
        horizontalLayout.setMargin(true);
        setContent(horizontalLayout);

        MyWeather myWeather = new MyWeather();
        MyKurs myKurs = new MyKurs();
        MyOther myOther = new MyOther(vaadinRequest);

        horizontalLayout.addComponents(myWeather,myKurs,myOther);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = VaadinController.class, productionMode = false)
    public static class MyUIServlet extends com.vaadin.server.VaadinServlet {
    }
}
