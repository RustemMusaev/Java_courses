package ru.rustem.page.userPage;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import ru.rustem.model.Product;
import ru.rustem.page.basePage.BasePage;
import ru.rustem.service.TransactionService;

@AuthorizeInstantiation("USER")
public class UserActionPage extends BasePage {

    private Product selectedProduct;

    @SpringBean
    private TransactionService transactionService;

    public UserActionPage() {
        add(new BookmarkablePageLink("signOut", LogoutPage.class));
        add(new Label("selectedLabel", new PropertyModel<>(this, "selectedContactLabel")));
    }

    public String getSelectedContactLabel() {
        return productIsSelect(selectedProduct);
    }

    class ProductPanel extends Panel {
        public ProductPanel(String id, IModel<Product> model) {
            super(id, model);
            add(new Link("selectP") {
                @Override
                public void onClick() {
                    selectedProduct = (Product) getParent().getDefaultModelObject();
                    transactionService.buyProduct(selectedProduct);
                }
            });
        }
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product product) {
        addStateChange();
        this.selectedProduct = product;
    }

    public String productIsSelect(Product product) {
        if (product == null) {
            return "Select product";
        }
        if (0 <= product.getCount()) {
            return "Buy the product : name = " + product.getId() + " , price=" + product.getPrice();
        } else
            return "This position is Empty ";
    }
}
