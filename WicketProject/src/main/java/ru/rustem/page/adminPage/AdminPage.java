package ru.rustem.page.adminPage;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy;
import org.apache.wicket.markup.repeater.util.ModelIteratorAdapter;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import ru.rustem.dataView.DetachableProductModel;
import ru.rustem.model.Product;
import ru.rustem.model.User;
import ru.rustem.page.basePage.BasePage;
import ru.rustem.page.popupPage.CreateProductPage;
import ru.rustem.page.popupPage.ShowHistory;
import ru.rustem.page.userPage.LogoutPage;
import ru.rustem.service.ProductService;

import java.util.Iterator;

@AuthorizeInstantiation("ADMIN")
public class AdminPage extends BasePage {
    final Form<?> form;
    @SpringBean
    private ProductService productService;

    private static final Logger log = Logger.getLogger(AdminPage.class);

    public AdminPage() {

        add(new BookmarkablePageLink("signOut", LogoutPage.class));
        PopupSettings popupSettingsToCreate = new PopupSettings("createProduct").setHeight(500)
                .setWidth(500);
        add(new BookmarkablePageLink<>("createProduct", CreateProductPage.class).setPopupSettings(popupSettingsToCreate));
        PopupSettings popupSettingsToHistory = new PopupSettings("createProduct").setHeight(500)
                .setWidth(800);
        add(new BookmarkablePageLink<>("viewTransactions", ShowHistory.class).setPopupSettings(popupSettingsToHistory));

        form = new Form("form");
        add(form);
        User user = (User) AuthenticatedWebSession.get().getAttribute("userToSession");
        RefreshingView<Product> refreshingView = new RefreshingView<Product>("simple") {
            @Override
            protected Iterator<IModel<Product>> getItemModels() {
                Iterator<Product> products = productService.findAll().iterator();
                return new ModelIteratorAdapter<Product>(products) {
                    @Override
                    protected IModel<Product> model(Product object) {
                        return new CompoundPropertyModel<Product>(
                                new DetachableProductModel(object, productService));
                    }
                };
            }

            @Override
            protected void populateItem(final Item<Product> item) {
                IModel<Product> productIModel = item.getModel();
                item.add(new ActionPanel("actions", productIModel));
                item.add(new TextField<>("id"));
                item.add(new TextField<>("name").add(
                        new AjaxFormComponentUpdatingBehavior("change") {
                            @Override
                            protected void onUpdate(AjaxRequestTarget target) {
                                Product product = (Product) item.getDefaultModelObject();
                                item.setDefaultModelObject(product);
                                productService.save(product);
                                if (log.isInfoEnabled()) {
                                    log.info("User = "+ user.getLogin() +"change product id = " + product.getId()+ " new name = " + product.getName());
                                }
                            }
                        }));
                item.add(new TextField<String>("description").add(
                        new AjaxFormComponentUpdatingBehavior("change") {
                            @Override
                            protected void onUpdate(AjaxRequestTarget target) {
                                Product product = (Product) item.getDefaultModelObject();
                                item.setDefaultModelObject(product);
                                productService.save(product);
                                if (log.isInfoEnabled()) {
                                    log.info("User = "+ user.getLogin() +"change product id = " + product.getId()+ " new description = " + product.getDescription());
                                }
                            }
                        }));
                item.add(new TextField<>("price").add(
                        new AjaxFormComponentUpdatingBehavior("change") {
                            @Override
                            protected void onUpdate(AjaxRequestTarget target) {
                                Product product = (Product) item.getDefaultModelObject();
                                item.setDefaultModelObject(product);
                                productService.save(product);
                                if (log.isInfoEnabled()) {
                                    log.info("User = "+ user.getLogin() +"change product id = " + product.getId()+ " new price = " + product.getPrice());
                                }
                            }
                        }));
                item.add(new TextField<>("count").add(
                        new AjaxFormComponentUpdatingBehavior("change") {
                            @Override
                            protected void onUpdate(AjaxRequestTarget target) {
                                Product product = (Product) item.getDefaultModelObject();
                                item.setDefaultModelObject(product);
                                productService.save(product);
                                if (log.isInfoEnabled()) {
                                    log.info("User = "+ user.getLogin() +"change product id = " + product.getId()+ " new count = " + product.getCount());
                                }
                            }
                        }));
            }

            @Override
            protected Item<Product> newItem(String id, int index, IModel<Product> model) {
                return new OddEvenItem<>(id, index, model);
            }
        };
        refreshingView.setItemReuseStrategy(ReuseIfModelsEqualStrategy.getInstance());
        form.add(refreshingView);
    }

    private class ActionPanel extends Panel {
        public ActionPanel(String id, IModel<Product> model) {
            super(id, model);

            SubmitLink editLink = new SubmitLink("edit", form) {
                @Override
                public void onSubmit() {
                    Product product = (Product) ActionPanel.this.getDefaultModelObject();
                    info("EDIT contact " + product);
                }
            };
            editLink.setDefaultFormProcessing(false);
            add(editLink);
            SubmitLink removeLink = new SubmitLink("remove", form) {
                @Override
                public void onSubmit() {
                    Product product = (Product) ActionPanel.this.getDefaultModelObject();
                    info("Removed  " + product);
                    productService.delete(product);
                }
            };
            removeLink.setDefaultFormProcessing(false);
            add(removeLink);
        }
    }
}
