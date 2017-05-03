package ru.rustem.page.userPage;

import org.apache.wicket.markup.html.WebPage;
import ru.rustem.page.HomePage;

public class LogoutPage extends WebPage {
    public LogoutPage() {
        getSession().invalidate();
        setResponsePage(HomePage.class);
    }
}
