package ru.itis;

import java.util.StringTokenizer;

import static ru.itis.Main.str;

public class CheckCharacterIsDigit implements Runnable {
    public void run() {
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        while (stringTokenizer.hasMoreTokens()) {
            String[] strings = stringTokenizer.nextToken().split("");
            for (String a : strings) {
                if (a.matches("[0-9]")) {
                    System.out.println(Thread.currentThread().getName() + " " + a);
                }
            }
        }
    }
}
