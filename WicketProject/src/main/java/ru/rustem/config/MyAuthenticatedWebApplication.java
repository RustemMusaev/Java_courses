package ru.rustem.config;

import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.rustem.page.otherPage.HomePage;
import ru.rustem.page.otherPage.SignInPage;

public class MyAuthenticatedWebApplication extends AuthenticatedWebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return MyAuthenticatedWebSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return SignInPage.class;
    }

    @Override
    protected void init() {
        super.init();
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("ru.rustem.config");
        ctx.register(SpringConfig.class);
        ctx.refresh();
        getDebugSettings().setDevelopmentUtilitiesEnabled(false);
        getDebugSettings().setAjaxDebugModeEnabled(false);
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, ctx));
    }
}
