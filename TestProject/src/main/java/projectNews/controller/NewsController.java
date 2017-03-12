package projectNews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import projectNews.converter.ArticleToArticleDtoConverter;
import projectNews.dto.ArticleDto;
import projectNews.models.Article;
import projectNews.service.ArticleServiceImpl;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class NewsController {
    @Autowired
    private ArticleServiceImpl articleService;

    private Integer currentPage = 1;
    private Integer countLine = 10;

    @GetMapping(value = {"/users","/"})
    @ResponseBody ModelAndView showNews() {
        ModelAndView modelAndView = new ModelAndView("users");
        Page<Article> page = articleService.getPageNews(currentPage, countLine);
        List<Integer> listPageCount = new ArrayList<>();
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

    @GetMapping(value = "/404")
    @ResponseBody ModelAndView errorPage() {
        ModelAndView modelAndView = new ModelAndView("404");
        return modelAndView;
    }

    @GetMapping(value = "/users/{currentPage}")
    public String selectPage(@PathVariable("currentPage") Integer setPage) {
        currentPage = setPage;
        return "redirect:/users";
    }

    @GetMapping(value = "/getImage/{imageName}")
    public String showImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws Exception {
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            BufferedImage image = ImageIO.read(new File("../upload/"+imageName+".jpg"));
                    ImageIO.write(image, "jpeg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        byte[] imgByte = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("img/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(imgByte);
        responseOutputStream.flush();
        responseOutputStream.close();
        return "redirect:/users";
    }

    @GetMapping(value = "/users/count/{countLine}")
    public String selectCountLine(@PathVariable("countLine") Integer setCountLine) {
        countLine = setCountLine;
        currentPage = 1;
        return "redirect:/users";
    }

    @PostMapping(value = "/users", produces = "text/plain;charset=UTF-8")
    public String addNews(@ModelAttribute("articleDto") ArticleDto articleDto, @RequestParam("file") MultipartFile file) {
        Article article;
        Date date = Calendar.getInstance(TimeZone.getTimeZone("GMT+7:00")).getTime();
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        if (!file.isEmpty()) {
             article = new Article.Builder()
                    .title(articleDto.getTitle())
                    .date(String.valueOf(date))
                    .message(articleDto.getMessage())
                    .picture(fileName)
                    .build();
            articleService.save(article);
            try {
                byte[] bytes = file.getBytes();
            //    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(""+fileName + ".jpg")));
           //     BufferedOutputStream stream1 = new BufferedOutputStream(new FileOutputStream(new File("./"+fileName + "1.jpg")));
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("../upload/"+fileName + ".jpg")));
            //    BufferedOutputStream stream3 = new BufferedOutputStream(new FileOutputStream(new File("/"+fileName + "3.jpg")));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                 return "Вам не удалось загрузить " + fileName + " => " + e.getMessage();
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
