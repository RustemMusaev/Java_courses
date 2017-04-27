package ru.rustem.page;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.Link;
import ru.rustem.page.adminPage.AdminPage;
import ru.rustem.page.basePage.BasePage;
import ru.rustem.page.userPage.UserPage;

public class HomePage extends BasePage {


    public HomePage() {
        final Link adminLink = new Link("admin") {
            @Override
            public void onClick() {
                setResponsePage(AdminPage.class);
            }
        };
        add(adminLink);
        final Link userLink = new Link("user") {
            @Override
            public void onClick() {
                setResponsePage(UserPage.class);
            }
        };
        add(userLink);
        final Link registrLink = new Link("registr") {
            @Override
            public void onClick() {
                setResponsePage(RegistrationPage.class);
            }
        };
        add(registrLink);

        final Link singOutLink = new Link("singOut") {
            @Override
            public void onClick() {
                getSession().invalidate();
                setResponsePage(HomePage.class);
            }

            @Override
            protected void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("style", "display:none");
                if (AuthenticatedWebSession.get().getAttribute("userToSession") != null) {
                    tag.put("style", "display:blok");
                }
            }
        };
        add(singOutLink);
    }
}
