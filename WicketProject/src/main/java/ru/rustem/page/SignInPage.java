package ru.rustem.page;

import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public final class SignInPage extends WebPage {

    public SignInPage() {
        this(null);
    }

    public SignInPage(final PageParameters parameters) {
        add(new SignInPanel("signInPanel"));
    }
}