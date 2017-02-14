package ru.itis.theory.beans;


import javax.annotation.PostConstruct;

public class SimpleBean {
    private final String DEFAULT_STRING_VALUE = "DEFAULT";
    private final int DEFAULT_INT_VALUE = 0;

    private int intValue;
    private String stringValue;

    public SimpleBean(int intValue, String stringValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
    }

    public SimpleBean() {
        System.out.println("1. SimpleBean in constructor");
    }

    @PostConstruct
    public void initMyBean() {
        System.out.println("3. SimpleBean in init");
        this.intValue = DEFAULT_INT_VALUE;
        this.stringValue = DEFAULT_STRING_VALUE;
    }

    public void destroyMyBean() {
        System.out.println("4. SimpleBean in destroy");
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        System.out.println("2. SimpleBean in setter of IntValue");
        this.intValue = intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        System.out.println("2. SimpleBean in setter of StringValue");
        this.stringValue = stringValue;
    }
}
