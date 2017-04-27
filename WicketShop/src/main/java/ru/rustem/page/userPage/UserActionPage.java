package ru.rustem.page.userPage;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import ru.rustem.model.Product;
import ru.rustem.model.User;
import ru.rustem.page.HomePage;
import ru.rustem.page.basePage.BasePage;
import ru.rustem.service.TransactionService;

public class UserActionPage extends BasePage {
    private Product selected;
    @SpringBean
    private TransactionService transactionService;

    public UserActionPage() {
        final Link singOutLink = new Link("singOut") {
            @Override
            public void onClick() {
                getSession().invalidate();
                setResponsePage(HomePage.class);
            }
        };
        add(singOutLink);
        add(new Label("selectedLabel", new PropertyModel<>(this, "selectedContactLabel")));
    }

    public String getSelectedContactLabel() {
        if (selected == null) {
            return "Select product";
        } else {
            if (selected.getCount() > 0) {
                return "Buy the product : name = " + selected.getId() + " , price=" + selected.getPrice();
            } else return "This position is Empty ";
        }
    }

    class ProductPanel extends Panel {
        public ProductPanel(String id, IModel<Product> model) {
            super(id, model);
            add(new Link("selectP") {
                @Override
                public void onClick() {
                    selected = (Product) getParent().getDefaultModelObject();
                    if (selected.getCount() > 0) {
                        User user = (User) AuthenticatedWebSession.get().getAttribute("userToSession");
                        Integer count = transactionService.shop(selected, user);
                        if (count != null) {
                            selected.setCount(count);
                        }
                    }
                }
            });
        }
    }

    public Product getSelected() {
        return selected;
    }

    public void setSelected(Product selected) {
        addStateChange();
        this.selected = selected;
    }
}
