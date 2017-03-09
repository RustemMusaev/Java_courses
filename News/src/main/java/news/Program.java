package news;

import news.config.SpringConfig;
import news.model.Article;
import news.service.ArticleService;
import news.service.ArticleServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.security.Security;
import java.util.List;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan("news.config")
public class Program {
    public static void main(String[] args) {

        SpringApplication.run(SpringConfig.class, args);

 /*       ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        ArticleService articleService = context.getBean(ArticleServiceImpl.class);
        Article test = articleService.save(new Article.Builder().title("test").date(String.valueOf(java.util.Calendar.getInstance().getTime())).message("Hello").build());
        List<Article> articleList = articleService.findAll();
        System.out.println(articleList.get(1).getId());
   */ }
}
