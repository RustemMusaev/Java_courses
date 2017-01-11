package servlet;

public class Util {
    private String str="Сначала";
    public static String headWithTitle(String title) {
        return("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n");
    }

    public String getStringProperty(){
        return str;
    }
    public void setStringProperty(String str){
        this.str=str;
    }
}