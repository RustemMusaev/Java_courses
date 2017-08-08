package ru.rustem.dao;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.rustem.config.SpringConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class App {

    @Autowired
    private ResumeDao resumeDao;
    String token;

    private static String URL = "https://www.avito.ru/saratovskaya_oblast/rezume/turizm_restorany/polnyy_den?s=101&view=list";
    private static String STRING_TO_SEARCH = "ова";

    //Init Spring Context
    App() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    public static void main(String[] args) {
        App app = new App();
        app.sendGet(URL);
        app.sendPostRequest();
        app.findResumeByParam(STRING_TO_SEARCH);
    }

    // HTTP GET request
    private void sendGet(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(doc.title());
        parseDocument(doc);
    }

    private void parseDocument(Document doc) {
        Elements links = doc.select("a");
        for (Element link : links) {
            String string = link.absUrl("href");
            if (string.toLowerCase().contains("rezume/".toLowerCase()) & !string.toLowerCase().contains("s=101") & !string.endsWith("s=101")) {
                Resume resume = parseHref(string);
                if (resume != null && resumeDao.save(resume) != null) {
                    System.out.println("Succes add resume to database");
                } else {
                    System.out.println("error save resume");
                }
            }
        }
    }

    private Resume parseHref(String href) {
        Document document = null;
        try {
            document = Jsoup.connect(href).get();
        } catch (IOException e) {
            System.out.println("connect URL return IOException");
        }
        Resume resume = new Resume();
        resume.setName(document.select("div.title-info-metadata-item").text());
        if (!resume.getName().equals("")) {
            resume.setPosition(document.select("span.title-info-title-text").text());
            resume.setStudy(document.select("li.item-params-list-item").text());
            resume.setAbout(document.select("div.item-description-text").text());
            resume.setAddress(document.select("div.item-map-location").text());
            resume.setSkill(document.select("p.resume-params-text").text());
            resume.setPhone(document.select("span.item-phone-button-sub-text").text());
            resume.setOther(document.select("td.resume-params-left").text());
            return resume;
        } else return null;
    }

    private List<Resume> findResumeByParam(String string) {
        return resumeDao.findByPosition(string);
    }
    // HTTP POST request(get token from response)
    private void sendPostRequest() {
        String urlString = "https://passport.ngs.ru/ajax/login/";
        String login = "ipmusaevrr@gmail.com";
        String password = "9130001151Qq";

        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(urlString);

        List<NameValuePair> urlParam = new ArrayList<NameValuePair>();
        urlParam.add(new BasicNameValuePair("login", login));
        urlParam.add(new BasicNameValuePair("password", password));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(urlParam));
        } catch (UnsupportedEncodingException e) {
            System.out.println("httpPost.setEntity throws UnsupportedEncodingException ");
        }

        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException e) {
            System.out.println("httpClient.execute(httpPost) throws UnsupportedEncodingException ");
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
        } catch (IOException e) {
            System.out.println("Don't read response");
        }
        StringBuilder builder = new StringBuilder();
        try {
            for (String line = null; (line = reader.readLine()) != null; ) {
                builder.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Don't read response");
        }
        String string = "[" + builder + "]";

        JSONTokener tokener = new JSONTokener(string);
        JSONArray finalResult = new JSONArray(tokener);
        JSONObject jsonObject = (JSONObject) finalResult.get(0);
        token = jsonObject.optString("token");
        System.out.println(" token = " + token);
    }
}
