package ru.rustem.page.otherPage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.PopupCloseLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class SuccessPage extends WebPage {
    public SuccessPage(final PageParameters parameters) {
        String id = "";
        if (parameters.get("id") != null) {
            id = String.valueOf(parameters.get("id"));
        }
        final Label result = new Label("result", "Product Id = : " + id);
        add(result);
        add(new PopupCloseLink("close"));

    }
}
