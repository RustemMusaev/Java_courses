package ru.rustem.page.userPage;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import ru.rustem.dataView.ProductDataProvider;
import ru.rustem.model.Product;
import ru.rustem.service.ProductService;

import java.util.List;

@AuthorizeInstantiation("USER")
public class UserPage extends UserActionPage {
    private static final long serialVersionUID = 1L;

    @SpringBean
    private ProductService productService;

    public UserPage() {
        List<Product> products = productService.findAll();
        DataView<Product> dataView = new DataView<Product>("pageable", new ProductDataProvider(productService)) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final Item<Product> item) {
                Product product = item.getModelObject();
                item.add(new ProductPanel("actionsP", item.getModel()));
                item.add(new Label("productId", product.getId()));
                item.add(new Label("productName", product.getName()));
                item.add(new Label("productDesc", product.getDescription()));
                item.add(new Label("productPrice", String.valueOf(product.getPrice())));
                item.add(new Label("productCount", String.valueOf(product.getCount())));

                item.add(AttributeModifier.replace("class", new AbstractReadOnlyModel<String>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public String getObject() {
                        return (item.getIndex() % 2 == 1) ? "even" : "odd";
                    }
                }));
            }
        };

        dataView.setItemsPerPage(8L);
        add(dataView);

        add(new PagingNavigator("navigator", dataView));
    }
}
