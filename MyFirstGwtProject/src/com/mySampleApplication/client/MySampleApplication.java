package com.mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.mySampleApplication.client.model.PointDto;

import java.util.ArrayList;
import java.util.List;

public class MySampleApplication implements EntryPoint {

    private int t;
    private String county;
    private String city;
    private SuggestBox suggestBox;
    private SuggestBox suggestCity;
    private ListBox listBox;
    private Button searchCity = new Button("searchCity");
    private Button searchPoint = new Button("searchPoint");
    private Button searchCountry = new Button("searchCountry");
    private Button reloadData = new Button("reload Data");
    private List<PointDto> pointDtos = new ArrayList<>();

    public void onModuleLoad() {
        listBox = new ListBox();
        listBox.addItem("input");
        listBox.addItem("output");
        listBox.addItem("input and output");
        listBox.setItemSelected(0, true);
        listBox.setStyleName("suggestBox");

        reloadData.setStyleName("button");
        RootPanel.get("buttoms").add(reloadData);
        RootPanel.get("listBox").add(listBox);
        searchCountry.setStyleName("button");
        RootPanel.get("listBox").add(searchCountry);

        reloadData.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                MySampleApplicationService.App.getInstance().reloadDataBase(new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert("error reload Data");
                    }
                    @Override
                    public void onSuccess(Void result) {
                        Window.alert("reload OK");
                        RootPanel.get("country").clear();
                        RootPanel.get("city").clear();
                        RootPanel.get("tablePoint").clear();
                    }
                });

            }
        });
        searchCountry.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("country").clear();
                RootPanel.get("city").clear();
                RootPanel.get("tablePoint").clear();
                t = listBox.getSelectedIndex();
                suggestBox = new SuggestBox(getCountries(t));
                suggestBox.setStyleName("suggestBox");
                Label label = new Label("Select Country");
                label.setStyleName("label");
                RootPanel.get("country").add(label);
                RootPanel.get("country").add(suggestBox);
                searchCity.setStyleName("button");
                RootPanel.get("country").add(searchCity);
            }
        });
        searchCity.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("city").clear();
                RootPanel.get("tablePoint").clear();
                county = suggestBox.getValue();
                MultiWordSuggestOracle oracle = (getCity(t, county));
                suggestCity = new SuggestBox(oracle);
                suggestCity.setStyleName("suggestBox");
                Label label = new Label("Select City");
                label.setStyleName("label");
                RootPanel.get("city").add(label);
                RootPanel.get("city").add(suggestCity);
                searchPoint.setStyleName("button");
                RootPanel.get("city").add(searchPoint);
            }
        });
        searchPoint.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("tablePoint").clear();
                city = suggestCity.getValue();
                MySampleApplicationService.App.getInstance().getPointDto(t, county, city, new AsyncCallback<List<PointDto>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert("err Point downloads");
                    }

                    @Override
                    public void onSuccess(List<PointDto> result) {
                        for (PointDto p : result) {
                            pointDtos.add(p);
                        }
                        CellTable<PointDto> cellTable = new CellTable<>();
                        TextColumn<PointDto> columnName = new TextColumn<PointDto>() {
                            @Override
                            public String getValue(PointDto object) {
                                return object.getName();
                            }
                        };
                        cellTable.addColumn(columnName, "Name");

                        TextColumn<PointDto> columnPhone = new TextColumn<PointDto>() {
                            @Override
                            public String getValue(PointDto object) {
                                return object.getPhone();
                            }
                        };
                        cellTable.addColumn(columnPhone, "Phone");

                        TextColumn<PointDto> columnAdress = new TextColumn<PointDto>() {
                            @Override
                            public String getValue(PointDto object) {
                                return object.getAddress();
                            }
                        };
                        cellTable.addColumn(columnAdress, "Adress");
                        cellTable.setRowCount(pointDtos.size(), true);
                        cellTable.setRowData(0, pointDtos);
                        pointDtos = new ArrayList<>();
                        RootPanel.get("tablePoint").add(cellTable);
                    }
                });
            }
        });
    }
    private MultiWordSuggestOracle getCity(int t, String value) {
        MultiWordSuggestOracle citys = new MultiWordSuggestOracle();
        MySampleApplicationService.App.getInstance().getCitys(t, value, new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("error citys");
            }

            @Override
            public void onSuccess(List<String> result) {
                for (String s : result) {
                    citys.add(s);
                }
            }
        });
        return citys;
    }
    private MultiWordSuggestOracle getCountries(int count) {
        MultiWordSuggestOracle countries = new MultiWordSuggestOracle();
        MySampleApplicationService.App.getInstance().getCountrys(count, new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("ERROR");
            }
            @Override
            public void onSuccess(List<String> result) {
                countries.addAll(result);
            }
        });
        return countries;
    }
}
