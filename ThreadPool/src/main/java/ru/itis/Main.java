package ru.itis;

public class Main {
    static String str="Aaaa aaaaA Dddddd dddddD 9595 2526153  615621 6 126162161 6213   sdf sd dSdSd sDF SDF sDF Sdf sS";

    public static void main(String[] args) {

        Runnable CheckCharacterIsDigit = new CheckCharacterIsDigit();
        Runnable CheckLastCharacter = new CheckLastCharacter();
        Runnable CheckFirstCharacter = new CheckFirstCharacter();

        WorkQueue workQueue = new WorkQueue(2);

        workQueue.execute(CheckFirstCharacter);
        workQueue.execute(CheckCharacterIsDigit);
        workQueue.execute(CheckLastCharacter);
    }
}
