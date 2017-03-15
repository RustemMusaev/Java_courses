package ru.rustem.CustomComponent;

import com.vaadin.ui.*;
import ru.rustem.service.WeatherService;
import ru.rustem.service.WeatherServiceImpl;

public class MyKurs extends CustomComponent {
    WeatherService weatherService = new WeatherServiceImpl();
    public MyKurs() {
        // A layout structure used for composition
        Panel panel = new Panel("EUR, USD");
        VerticalLayout panelContent = new VerticalLayout();
        panel.setContent(panelContent);
        // Compose from multiple components
        Label labelEUR = new Label(" EUR : " + weatherService.getEur()+" руб. ");
        Label labelUSD = new Label("USD "+ weatherService.getUsd()+" руб.");
        Button button = new Button("update");
        panelContent.addComponents(labelEUR,labelUSD,button);
        button.addClickListener( e -> {
            labelEUR.setValue(" EUR : " + weatherService.getEur()+" руб. ");
            labelUSD.setValue("USD "+ weatherService.getUsd()+" руб.");
            });
        // Set the size as undefined at all levels
        panelContent.setSizeUndefined();
        panel.setStyleName("divClass");
        setSizeUndefined();
        // The composition root MUST be set
        setCompositionRoot(panel);
    }
}