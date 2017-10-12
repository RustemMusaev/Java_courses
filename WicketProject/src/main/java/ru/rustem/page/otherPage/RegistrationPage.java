package ru.rustem.page.otherPage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.PatternValidator;
import ru.rustem.dto.UserDto;
import ru.rustem.service.UserService;
import ru.rustem.validator.UserNameValidator;

import java.util.Date;

public class RegistrationPage extends WebPage {
    //1 digit, 1 lower, 1 upper, 1 symbol "@#$%", from 6 to 20
    private final String PASSWORD_PATTERN
            = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    @SpringBean
    private UserService userService;

    Form<?> form = null;
    UserDto model = new UserDto();

    public RegistrationPage(final PageParameters page) throws Exception {
        super();
        add(new FeedbackPanel("feedback"));
        Label regDate = new Label("regDateId", new Date());
        TextField login = new TextField("login", new PropertyModel(model, "login"));
        PasswordTextField password = new PasswordTextField("password", new PropertyModel(model, "password"));
        PasswordTextField cpassword = new PasswordTextField("cpassword", new PropertyModel(model, "cpassword"));
        login.setRequired(true);
        password.setRequired(true);
        cpassword.setRequired(true);

        form = new Form<Void>("regForm") {
            public void onSubmit() {
                if (userService.save(model)) {
                    setResponsePage(HomePage.class);
                } else {
                    error("This name not Unique");
                }
            }
        };
        form.setDefaultModel(new CompoundPropertyModel(this));
        form.add(regDate);
        form.add(login);
        login.add(new UserNameValidator());
        form.add(password);
        password.add(new PatternValidator(PASSWORD_PATTERN));
        form.add(cpassword);
        form.add(new EqualPasswordInputValidator(password, cpassword));
        add(form);
    }
}
