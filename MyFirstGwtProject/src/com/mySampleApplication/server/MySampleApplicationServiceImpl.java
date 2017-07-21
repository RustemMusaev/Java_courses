package com.mySampleApplication.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mySampleApplication.client.MySampleApplicationService;
import com.mySampleApplication.client.model.PointDto;
import com.mySampleApplication.server.model.Point;

import java.util.ArrayList;
import java.util.List;

import static com.mySampleApplication.server.parse.ParseXMLWithXMLStreamReader.ParseXMLWithXMLStreamReader;

public class MySampleApplicationServiceImpl extends RemoteServiceServlet implements MySampleApplicationService {

    static List<Point> points = ParseXMLWithXMLStreamReader();

    @Override
    public List<String> getCitys(int service, String country) {
        List<String> citys = new ArrayList<>();
        for (Point p : points) {
            if ((p.getServices() == service) && p.getCountry().equals(country))
                citys.add(p.getCity());
        }
        return citys;
    }

    @Override
    public List<PointDto> getPointDto(int service, String country, String city) {
        List<PointDto> pointDtos = new ArrayList<>();
        for (Point p : points) {
            if ((p.getServices() == service) && p.getCountry().equals(country) && p.getCity().equals(city)) {
                PointDto pointDto = new PointDto();
                pointDto.setName(p.getName());
                pointDto.setAddress(p.getAddress());
                pointDto.setPhone(p.getPhone());
                pointDtos.add(pointDto);
            }
        }
        return pointDtos;
    }

    @Override
    public void reloadDataBase() {
        points = null;
        points = ParseXMLWithXMLStreamReader();
    }

    @Override
    public List<String> getCountrys(int service) {
        List<String> countries = new ArrayList<>();
        for (Point p : points) {
            if (p.getServices() == service) {
                countries.add(p.getCountry());
            }
        }
        return countries;
    }
}