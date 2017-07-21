package com.mySampleApplication.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mySampleApplication.client.model.PointDto;

import java.util.List;

@RemoteServiceRelativePath("MySampleApplicationService")
public interface MySampleApplicationService extends RemoteService {
    List<String> getCountrys(int services);

    List<String> getCitys(int services, String country);

    List<PointDto> getPointDto(int services, String country, String city);

    void reloadDataBase();

    public static class App {
        private static MySampleApplicationServiceAsync ourInstance = GWT.create(MySampleApplicationService.class);

        public static synchronized MySampleApplicationServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
