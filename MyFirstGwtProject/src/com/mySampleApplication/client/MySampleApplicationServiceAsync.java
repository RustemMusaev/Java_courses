package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mySampleApplication.client.model.PointDto;

import java.util.List;

public interface MySampleApplicationServiceAsync {
    void getCitys(int count, String country, AsyncCallback<List<String>> async);

    void getCountrys(int count, AsyncCallback<List<String>> async);

    void getPointDto(int services, String country, String city, AsyncCallback<List<PointDto>> async);

    void reloadDataBase(AsyncCallback<Void> async);
}
