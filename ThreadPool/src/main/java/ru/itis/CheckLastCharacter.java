package ru.itis;

import java.util.StringTokenizer;

import static ru.itis.Main.str;

public class CheckLastCharacter implements Runnable {
    public void run() {
        StringTokenizer stringTokenizer=new StringTokenizer(str);
        while (stringTokenizer.hasMoreTokens()) {
            String s,s1;
            s=stringTokenizer.nextToken();
            int size=s.length();
            s1=""+s.charAt(size-1);
            if (s1.matches("[A-Z]")){
                System.out.println(Thread.currentThread().getName()+" " + s);
            }
        }
    }
}
