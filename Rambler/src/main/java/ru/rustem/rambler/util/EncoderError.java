package ru.rustem.rambler.util;


public class EncoderError implements EncoreMethod {
    @Override
    public String encode(String pwd) {
        return String.valueOf(new RuntimeException());
    }
}
