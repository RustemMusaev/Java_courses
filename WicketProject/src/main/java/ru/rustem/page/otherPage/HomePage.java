package ru.rustem.page.otherPage;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import ru.rustem.page.adminPage.AdminPage;
import ru.rustem.page.basePage.BasePage;
import ru.rustem.page.userPage.LogoutPage;
import ru.rustem.page.userPage.UserPage;

public class HomePage extends BasePage {

    public HomePage(PageParameters pageParameters) {
        add(new BookmarkablePageLink<>("admin", AdminPage.class));
        add(new BookmarkablePageLink<>("user", UserPage.class));
        add(new BookmarkablePageLink<>("registr", RegistrationPage.class));

        BookmarkablePageLink singOutLink = new BookmarkablePageLink("signOut", LogoutPage.class);
        singOutLink.setVisible(AuthenticatedWebSession.get().getAttribute("userToSession") != null);
        add(singOutLink);

    }
}
