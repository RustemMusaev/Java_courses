package com;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

public class MyUI extends UI {
    @Override
    protected void init(VaadinRequest request) {
        Button button = new Button("button");
        TextField textField = new TextField("what you name");

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponents(textField, button);
        layout.addComponent(new Label("Hello, world!"));
        layout.addComponent(new Label("Thanks "));

        setContent(layout);

        button.addClickListener(e -> Notification.show("Hello" +textField.getValue()));
    }

}
