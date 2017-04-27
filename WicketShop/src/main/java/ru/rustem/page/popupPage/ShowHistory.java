package ru.rustem.page.popupPage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.PopupCloseLink;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;
import ru.rustem.dao.TransactionDao;
import ru.rustem.model.Transaction;

import java.text.SimpleDateFormat;
import java.util.List;

public class ShowHistory extends WebPage {
    @SpringBean
    private TransactionDao transactionDao;

    public ShowHistory() {
        add(new PopupCloseLink("close"));
        List<Transaction> transactions = transactionDao.findAll();

        final DataView dataView = new DataView("simple", new ListDataProvider(transactions)) {
            @Override
            protected void populateItem(Item item) {
                final Transaction transaction = (Transaction) item.getModelObject();
                item.add(new Label("id", transaction.getId()));
                item.add(new Label("user", transaction.getUser().getLogin()));
                item.add(new Label("product", transaction.getProduct().getName()));
                String date = new SimpleDateFormat("yyyy / MM / dd").format(transaction.getDate());
                item.add(new Label("date", date));
                item.add(new Label("price", transaction.getPrice().toString()));
                item.add(new Label("count", transaction.getCount().toString()));
            }
        };
        dataView.setItemsPerPage(10);
        add(dataView);
        add(new PagingNavigator("navigator", dataView));
    }
}
