package ru.rustem.CustomComponent;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import ru.rustem.service.WeatherService;
import ru.rustem.service.WeatherServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MyOther extends CustomComponent{
    WeatherService weatherService = new WeatherServiceImpl();
    public MyOther(VaadinRequest vaadinRequest) {
        // A layout structure used for composition
        Panel panel = new Panel("Other");
        VerticalLayout panelContent = new VerticalLayout();
        panel.setContent(panelContent);
        // Compose from multiple components
        Date date = Calendar.getInstance(TimeZone.getTimeZone("GMT+7:00")).getTime();
        String dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);

        Label labelDate = new Label(" date : " + dateFormat);
        Label labelIP = new Label("IP " + vaadinRequest.getRemoteAddr());
       // weatherService.saveCount(3);
       // Integer id = weatherService.findCount();
        Label LabelCount = new Label("count" );
        panelContent.addComponents(labelDate,labelIP,LabelCount);
        // Set the size as undefined at all levels
        panelContent.setSizeUndefined();
        panel.setStyleName("divClass");
        labelIP.setSizeUndefined();
        // The composition root MUST be set
        setSizeUndefined();
        setCompositionRoot(panel);
    }
}
