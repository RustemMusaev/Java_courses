package ru.rustem.model;

public class Weather {

    private String currentTemp;
    private String nextTemp;

    public Weather(String currentTemp, String nextTemp) {
        this.currentTemp = currentTemp;
        this.nextTemp = nextTemp;
    }

    public String getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(String currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String getNextTemp() {
        return nextTemp;
    }

    public void setNextTemp(String nextTemp) {
        this.nextTemp = nextTemp;
    }
}
