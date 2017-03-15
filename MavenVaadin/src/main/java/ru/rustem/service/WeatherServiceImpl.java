package ru.rustem.service;


import com.mongodb.*;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.rustem.model.Weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherServiceImpl implements WeatherService{
    private static final String WEATHER_API_URL[] = {"http://api.openweathermap.org/data/2.5/forecast?id=","&APPID=41ca4f0c8437a012ee1e09cbf705ee94"};
    private static final String USD_API_URL = "http://api.fixer.io/latest?base=USD";
    private static final String EUR_API_URL = "http://api.fixer.io/latest?base=EUR";


    @Override
    public Weather getWeather(Integer idCity) {
        String url = WEATHER_API_URL[0]+idCity+WEATHER_API_URL[1];
        return new Weather(parseWeatherToday(convertURLtoString(url)),parseWeatherTomorrow(convertURLtoString(url)));
    }

    @Override
    public String getUsd() {
        return parseUSD(convertURLtoString(USD_API_URL));
    }

    @Override
    public String getEur() {
        return parseEUR(convertURLtoString(EUR_API_URL));
    }

    public String convertURLtoString(String url) {
        String result = "";
        try {
            URL url_weather = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url_weather.openConnection();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 2048);
                String line = null;
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
            } else {
                System.out.println("Error in httpURLConnection.getResponseCode()!!!");
            }

        } catch (MalformedURLException ex) {
        } catch (IOException ex) {
        }
        return result;
    }
    private String parseWeatherToday(String json) {
        Double fixTemp = 273.15;
        JSONObject jsonObject = new JSONObject(json);
        JSONArray JSONArray_weather = jsonObject.getJSONArray("list");
        JSONObject JSONObject_weather = JSONArray_weather.getJSONObject(0);
        JSONObject JSONObject_main = JSONObject_weather.getJSONObject("main");
        return String.valueOf(new BigDecimal(JSONObject_main.getDouble("temp") - fixTemp).setScale(2, RoundingMode.HALF_UP).floatValue());
     }
    private String parseWeatherTomorrow(String json) {
        Double fixTemp = 273.15;
        JSONObject jsonObject = new JSONObject(json);
        JSONArray JSONArray_weather = jsonObject.getJSONArray("list");
        JSONObject JSONObject_weather = JSONArray_weather.getJSONObject(1);
        JSONObject JSONObject_main = JSONObject_weather.getJSONObject("main");
        return String.valueOf(new BigDecimal(JSONObject_main.getDouble("temp") - fixTemp).setScale(2, RoundingMode.HALF_UP).floatValue());
    }
    private String parseUSD(String json) {
        JSONObject jsonObject = new JSONObject(json);
        JSONObject JSONObject_rates = jsonObject.getJSONObject("rates");
        return String.valueOf(JSONObject_rates.get("RUB"));
    }
    private String parseEUR(String json) {
        JSONObject jsonObject = new JSONObject(json);
        JSONObject JSONObject_rates = jsonObject.getJSONObject("rates");
        return String.valueOf(JSONObject_rates.get("RUB"));
    }
    @Override
    public void saveCount(Integer count){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = (DB) mongoClient.getDatabase("local");
        DBCollection table = db.getCollection("count");
         BasicDBObject document = new BasicDBObject();
        document.put("count", count);
        table.insert(document);
    }
    @Override
    public Integer findCount(){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = (DB) mongoClient.getDatabase("testdb");
        DBCollection table = db.getCollection("count");
        BasicDBObject newData = new BasicDBObject();
        newData.put("count", 3);
        BasicDBObject searchQuery = new BasicDBObject().append("login", 1);
        table.update(searchQuery, newData);
        return  1;
    }

}
