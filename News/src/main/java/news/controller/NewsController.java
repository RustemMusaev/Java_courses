package news.controller;

import news.converter.ArticleToArticleDtoConverter;
import news.dto.ArticleDto;
import news.model.Article;
import news.service.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static news.converter.ArticleToArticleDtoConverter.convertArticleDtoWithoutId;

@RestController
public class NewsController {

    @Autowired
    private ArticleServiceImpl articleService;
    @Autowired
    SimpMessagingTemplate template;

    @GetMapping(value = "/login")
    @ResponseBody
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("loginpage");
        return modelAndView;
    }

    @GetMapping("/news")
    public ResponseEntity<List<ArticleDto>> getAllMessages(@RequestParam("pageNumber") Integer pageNumber, @RequestParam("count") Integer count) {
            Page<Article> page = articleService.getPageNews(pageNumber,count);
            List<Article> list = new ArrayList<>(page.getContent());
            HttpHeaders headers = new HttpHeaders();
            headers.add("maxPage", String.valueOf(page.getTotalPages()));
            headers.add("currentPage", String.valueOf(page.getNumber()+1));
            List<ArticleDto> articleDtoList = list.stream()
                    .map(ArticleToArticleDtoConverter::convertArticleDtoWithoutId).collect(Collectors.toList());
            return new ResponseEntity<List<ArticleDto>>(articleDtoList, headers, HttpStatus.OK);
    }

    @PostMapping(value="/upload")
    public String addMessage(@RequestParam("message") String message, @RequestParam("title") String title,
                                                 @RequestParam("file") MultipartFile file, HttpServletRequest request){
        Article article;
        Date date = Calendar.getInstance(TimeZone.getTimeZone("GMT+7:00")).getTime();
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        if (!file.isEmpty()) {
            String url = "D:/upload/"+fileName + ".jpg";
            article = new Article.Builder()
                    .title(title)
                    .date(String.valueOf(date))
                    .message(message)
                    .picture(url)
                    .build();
            articleService.save(article);
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(url)));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
               // return "Вам не удалось загрузить " + fileName + " => " + e.getMessage();
            }
        } else {
            article = new Article.Builder()
                    .title(title)
                    .date(String.valueOf(date))
                    .message(message)
                    .build();
            articleService.save(article);
        }
        if (article != null){
            template.convertAndSend("/topic/newsletter",convertArticleDtoWithoutId(article));
        }
        return  "redirect: D:/JavaProject/Java_courses/NewsClient/client.html";
    }
}
