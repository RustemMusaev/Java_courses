package ru.rustem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.rustem.converter.ArticleToArticleDtoConverter;
import ru.rustem.dto.ArticleDto;
import ru.rustem.models.Article;
import ru.rustem.service.ArticleServiceImpl;


import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This controller processed all client request, who redirect LoginFilter
 */
@Controller
public class NewsController {
    @Autowired
    private ArticleServiceImpl articleService;
    private Integer currentPage = 1;
    private Integer countLine = 10;
    /**
     * This method processed request "/news","/", method=GET.
     * He create ModelandView for transfer of data on client page. He install to view name, call getPageNews method for pagination
     * data of database,set value current Page for client page, set value count rows for client page, convert result in
     * database request in ArticleDto.
     * @return ModelandView - collection output data and name view jsp to download fo client
     */
    @GetMapping(value = {"/TestProject/news","/TestProject"})
    @ResponseBody ModelAndView showNews() {
        ModelAndView modelAndView = new ModelAndView("news");
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
    /**
     * This method processed request "/404", method=GET. This method processed fail request, who do not filter control
     * @return ModelandView - collection output data and name view jsp to download fo client
     */
    @GetMapping(value = "/TestProject/404")
    @ResponseBody ModelAndView errorPage() {
        ModelAndView modelAndView = new ModelAndView("404");
        return modelAndView;
    }
    /**
     *This method is used for select number page of result. He processed Get reuest "/news/{currentPage}" where
     * {currentPage} is the page, which select cleint. His new pagination of data in database and output to client page
     * @param setPage - number of page, who select client for view
     * @return redirect on url "/news", call method showNews, for display result on select page
     */
    @GetMapping(value = "/TestProject/news/{currentPage}")
    public String selectPage(@PathVariable("currentPage") Integer setPage) {
        currentPage = setPage;
        return "redirect:/TestProject/news";
    }

    /**
     * This method using for download image for article for display client page. Each image save as relative path to picture fail.
     * For download image, method takes her relative path and makes files to path and send to client page.
     * @param imageName - relative path to saves image
     * @param response - answer containing image file.
     * @return updated data for client page
     * @throws Exception
     */
    @GetMapping(value = "/TestProject/getImage/{imageName}")
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
        return "redirect:/TestProject/news";
    }

    /**
     * This method is used for select count rows for pagination. He processed Get reuest "/news/count/{countLine}" where
     * {countLine} is the count rows, which select cleint. His new pagination of data in database and output to client page
      * @param setCountLine
     * @return
     */
    @GetMapping(value = "/TestProject/news/count/{countLine}")
    public String selectCountLine(@PathVariable("countLine") Integer setCountLine) {
        countLine = setCountLine;
        currentPage = 1;
        return "redirect:/TestProject/news";
    }
    /**
     *This method is used for add new Article for database.His get input param of client page(use ModelAttribute) and optional get file.
     * Default model ArticleDto contains not empty field "title" and "message". Field "date" create before save object to database
     * using abstract class Calendar and method SimpleDateFormat.class. If input file is not emply, create param "fileName" using current
     * server date and create new Article for save to database, else crete object without file. Then on client page upload updated data.
     * @param articleDto - model for get to client page FORM
     * @param file - optional attribute for Article object
     * @return updated data for client page
     */
    @PostMapping(value = "/TestProject/news", produces = "text/plain;charset=UTF-8")
    public String addNews(@ModelAttribute("articleDto") ArticleDto articleDto, @RequestParam("file") MultipartFile file) {
        Article article;
        Date date = Calendar.getInstance(TimeZone.getTimeZone("GMT+7:00")).getTime();
        if (!file.isEmpty()) {
            String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
            article = new Article.Builder()
                    .title(correctTitle(articleDto.getTitle()))
                    .date(String.valueOf(date))
                    .message(correctMessage(articleDto.getMessage()))
                    .picture(fileName)
                    .build();
            articleService.save(article);
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("../upload/"+fileName + ".jpg")));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                 return "Download error " + fileName + " => " + e.getMessage();
            }
        } else {
            article = new Article.Builder()
                    .title(correctTitle(articleDto.getTitle()))
                    .date(String.valueOf(date))
                    .message(correctMessage(articleDto.getMessage()))
                    .build();
            articleService.save(article);
        }
        return "redirect:/TestProject/news";
    }
    /**
     * This assistant method, who test field "title" on correct before writing to database.
     * @param title - String param for writting object Article to database
     * @return "title" if his length < 60 characters, or return first 60 characters of "title"
     */
    private String correctTitle(String title){
        if (title.length() > 60) {
            return (String) title.subSequence(0,59);
        } else return title;
    }
    /**
     * This assistant method, who test field "message" on correct before writing to database.
     * @param message - String param for writting object Article to database
     * @return "message" if his length < 500 characters, or return first 500 characters of "message"
     */
    private String correctMessage(String message){
        if (message.length() > 500) {
            return (String) message.subSequence(0,499);
        } else return message;
    }
}
