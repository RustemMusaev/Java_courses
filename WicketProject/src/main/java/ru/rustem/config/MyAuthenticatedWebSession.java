package ru.rustem.config;

import org.apache.log4j.Logger;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.rustem.model.User;
import ru.rustem.service.UserService;

public class MyAuthenticatedWebSession extends AuthenticatedWebSession {

    public static PasswordEncoder ENCODER = new BCryptPasswordEncoder();
    private static final Logger log = Logger.getLogger(MyAuthenticatedWebSession.class);

    @SpringBean
    private UserService userService;

    User user = null;

    public MyAuthenticatedWebSession(Request request) {
        super(request);
        Injector.get().inject(this);
    }

    @Override
    public boolean authenticate(final String login, final String password) {
        user = userService.findByLogin(login);
        if (user != null && ENCODER.matches(password, user.getPassword())) {
            this.setAttribute("userToSession", user);
            if (log.isInfoEnabled()) {
                log.info("User = "+ user.getLogin() +" open session");
            }
            return true;
        }
        if (log.isInfoEnabled()) {
            log.info("User = "+ user.getLogin() +"error authenticate ");
        }
        return false;
    }

    @Override
    public Roles getRoles() {
        if (isSignedIn() && user.getRoles().equals("USER")) {
            return new Roles("USER");
        }
        if (isSignedIn() && user.getRoles().equals("ADMIN")) {
            return new Roles("ADMIN");
        }
        return null;
    }
}
