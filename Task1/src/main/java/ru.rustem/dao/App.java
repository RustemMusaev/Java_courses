package ru.rustem.dao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.rustem.config.SpringConfig;

import java.io.IOException;
import java.util.List;

public class App {

    @Autowired
    private ResumeDao resumeDao;

    private final static String URL = "https://www.avito.ru/saratovskaya_oblast/rezume/turizm_restorany/polnyy_den?s=101&view=list";
    private final static String STRING_TO_SEARCH = "ова";

    App() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    public static void main(String[] args) {
        App app = new App();
        app.sendGet(URL);
        app.findResumeByParam(STRING_TO_SEARCH);
    }

    private void sendGet(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
