package ru.rustem.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ru.rustem.models.Weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class WeatherServiceImpl implements WeatherService{

    private static final String URL_API = "http://samples.openweathermap.org/data/2.5/weather?id=2172797&appid=b1b15e88fa797225412429c1c50c122a1";
    @Override
    public Weather getWeather(Integer idCity) {
        return new Weather(convertURLtoString(URL_API,idCity),convertURLtoString(URL_API,idCity));
    }

    public String convertURLtoString(String url, Integer idCity) {
        String result = "";
        String weatherResult = null;
        try {
            URL url_weather = new URL(URL_API);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url_weather.openConnection();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 8192);
                //String line = null;
                while((bufferedReader.readLine()) != null){
                    result += bufferedReader.readLine();
                }
                bufferedReader.close();
                weatherResult = parseResult(result);
                System.out.println(weatherResult);
            } else {
                System.out.println("Error in httpURLConnection.getResponseCode()!!!");
            }

        } catch (MalformedURLException ex) {
        } catch (IOException ex) {
        }
        return weatherResult;
    }

    private String parseResult(String jsonWeatherResult) {
        Double fixTemp = 273.15;
        JSONObject jsonObject = new JSONObject(jsonWeatherResult);
        JSONObject JSONObject_main = jsonObject.getJSONObject("main");
        return String.valueOf(JSONObject_main.getDouble("temp") - fixTemp);
     }
}
