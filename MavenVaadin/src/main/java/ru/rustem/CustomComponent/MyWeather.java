package ru.rustem.CustomComponent;

import com.vaadin.ui.*;
import ru.rustem.service.WeatherService;
import ru.rustem.service.WeatherServiceImpl;

public class MyWeather extends CustomComponent {
    WeatherService weatherService = new WeatherServiceImpl();
    public MyWeather() {
        // A layout structure used for composition
        Panel panel = new Panel("Weather");
        VerticalLayout panelContent = new VerticalLayout();
        panel.setContent(panelContent);
        // Compose from multiple components
        Label labelCurrentWeather = new Label();
        Label labelNextWeather = new Label();
        panelContent.addComponents(labelCurrentWeather,labelNextWeather);
        //NativeSelect select = new NativeSelect("Select city ");
        RadioButtonGroup<String> select =
                new RadioButtonGroup<>("Select city");
        select.setItems("Novosibirsk", "Moscow", "StPeterburg");
        select.addValueChangeListener(event -> {
            Integer idCity = 0;
            if (event.getValue().equals("Novosibirsk")) {
                idCity = 1496747;
            } else if (event.getValue().equals("Moscow")){
                idCity = 524901;
            } else if (event.getValue().equals("StPeterburg")) {
                idCity = 498817;
            } else idCity = 0;
            if (idCity != 0) {
                labelCurrentWeather.setValue(" current  " + weatherService.getWeather(idCity).getCurrentTemp());
                labelNextWeather.setValue(" tomorrow  "+ weatherService.getWeather(idCity).getNextTemp());
            }
        } );
        panelContent.addComponent(select);
        // Set the size as undefined at all levels
        panelContent.setSizeUndefined();
        panel.setStyleName("divClass");
        // The composition root MUST be set
        setCompositionRoot(panel);
    }
}