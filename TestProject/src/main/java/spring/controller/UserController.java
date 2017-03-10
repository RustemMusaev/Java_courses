package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.converter.ArticleToArticleDtoConverter;
import spring.dto.ArticleDto;
import spring.models.Article;
import spring.service.ArticleServiceImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class UserController {
    @Autowired
    private ArticleServiceImpl articleService;

    private Integer currentPage = 1;
    private Integer countLine = 10;

    @GetMapping(value = "/users")
    @ResponseBody ModelAndView showNews() {
        ModelAndView modelAndView = new ModelAndView("users");
        Page<Article> page = articleService.getPageNews(currentPage, countLine);
        List<Integer> listPageCount = new ArrayList<>(1);
        listPageCount.add(0,currentPage);
        listPageCount.add(1,page.getTotalPages());
        List<Article> list = new ArrayList<>(page.getContent());
        List<ArticleDto> articleDtoList = list.stream()
                .map(ArticleToArticleDtoConverter::convertArticleDtoWithoutId).collect(Collectors.toList());
        modelAndView.addObject("articleDtoList", articleDtoList);
        modelAndView.addObject("articleDto", new ArticleDto());
        modelAndView.addObject("listPageCount", listPageCount);
        return modelAndView;
    }

    @GetMapping(value = "/users/{currentPage}")
    public String selectPage(@PathVariable("currentPage") Integer setPage) {
        currentPage = setPage;
        return "redirect:/users";
    }

    @GetMapping(value = "/users/count/{count}")
    public String selectCountLine(@PathVariable("count") Integer setCountLine) {
        countLine = setCountLine;
        currentPage = 1;
        return "redirect:/users";
    }

    @PostMapping(value = "/users")
    public String addUser(@ModelAttribute("articleDto") ArticleDto articleDto) {
        Article article;
        Date date = Calendar.getInstance(TimeZone.getTimeZone("GMT+7:00")).getTime();
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        if (1==2) {
            String url = "D:/upload/"+fileName + ".jpg";
            article = new Article.Builder()
                    .title(articleDto.getTitle())
                    .date(String.valueOf(date))
                    .message(articleDto.getMessage())
                    .picture(url)
                    .build();
            articleService.save(article);
            try {
              //  byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(url)));
               // stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                // return "Вам не удалось загрузить " + fileName + " => " + e.getMessage();
            }
        } else {
            article = new Article.Builder()
                    .title(articleDto.getTitle())
                    .date(String.valueOf(date))
                    .message(articleDto.getMessage())
                    .build();
            articleService.save(article);
        }
        return "redirect:/users";
    }
}
