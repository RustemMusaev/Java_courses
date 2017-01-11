package ru.itis;

import java.util.StringTokenizer;

import static ru.itis.Main.str;

public class CheckFirstCharacter implements Runnable {
    public void run() {
        StringTokenizer stringTokenizer=new StringTokenizer(str);
        while (stringTokenizer.hasMoreTokens()) {
            String s,s1;
            s=stringTokenizer.nextToken();
            s1=""+s.charAt(0);
            if (s1.matches("[A-Z]")){
                System.out.println(Thread.currentThread().getName()+" " + s);
            }
        }
    }
}
