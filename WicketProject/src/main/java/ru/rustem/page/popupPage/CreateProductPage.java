package ru.rustem.page.popupPage;

import org.apache.log4j.Logger;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;
import ru.rustem.model.Product;
import ru.rustem.model.User;
import ru.rustem.page.SuccessPage;
import ru.rustem.service.ProductService;

public class CreateProductPage extends WebPage {
    @SpringBean
    private ProductService productService;

    private static final Logger log = Logger.getLogger(CreateProductPage.class);

    public CreateProductPage() {
        Product product = new Product();
        final TextField nameField = new TextField("addName", new PropertyModel(product, "name"));
        nameField.setRequired(true);
        final TextField descField = new TextField("addDescription", new PropertyModel(product, "description"));
        descField.setRequired(true);
        final TextField priceField = new TextField("addPrice", new PropertyModel(product, "price"));
        priceField.setRequired(true);
        priceField.add(RangeValidator.<Double>range(0.0, 10000.0));
        final TextField countField = new TextField("addCount", new PropertyModel(product, "count"));
        countField.setRequired(true);
        countField.add(RangeValidator.<Integer>range(0, 10000));

        Form<?> userForm = new Form<Void>("userForm") {
            @Override
            protected void onSubmit() {
                int id = productService.save(product);
                User user = (User) AuthenticatedWebSession.get().getAttribute("userToSession");
                if (log.isInfoEnabled()) {
                    log.info("User = "+ user.getLogin() +"create Product id = " + id);
                }
                PageParameters parameters = new PageParameters();
                parameters.add("id", id);
                setResponsePage(SuccessPage.class, parameters);
            }
        };
        userForm.add(nameField);
        userForm.add(descField);
        userForm.add(priceField);
        userForm.add(countField);
        add(userForm);
    }
}
