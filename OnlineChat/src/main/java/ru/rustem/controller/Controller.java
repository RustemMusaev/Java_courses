package ru.rustem.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.rustem.converter.MessageToMessageDtoConverter;
import ru.rustem.dao.MessageDao;
import ru.rustem.dto.*;
import ru.rustem.model.Message;
import ru.rustem.model.User;
import ru.rustem.service.UserService;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static ru.rustem.converter.MessageToMessageDtoConverter.messageToMessageDtoConverter;
import static ru.rustem.converter.UserRegistrationToUserConverter.userRegistrationToUserConverter;
import static ru.rustem.converter.UserToUserInfoConverter.userToUserInfoConverter;

@RestController
public class Controller {
    @Autowired
    private UserService userService;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    SimpMessagingTemplate template;

    private static final Logger log = Logger.getLogger(Controller.class);

    private Set<UserInfo> usersOnline = new HashSet<>();

    @PostMapping("/chats/messages")
    public void messageToChat(@RequestBody Message message, HttpServletRequest request) {
        String token = getCookie(request);
        if (token != null) {
            User user = userService.findUserByToken(token);
            message.setUser(user);
            Date date = Calendar.getInstance(TimeZone.getTimeZone("GMT+7:00")).getTime();
            String dateMessage = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            message.setDate(dateMessage);
            messageDao.save(message);
            if (log.isInfoEnabled()) {
                log.info("user id = " + user.getId() + " send message id " + message.getId() + " text : " + message.getMessage());
            }
            MessageDto messageDto = messageToMessageDtoConverter(message);
            template.convertAndSend("/topic/messages", messageDto);
        }
    }

    @PostMapping("/chats/users")
    @ResponseBody
    public HttpStatus userStatus(HttpServletRequest request) {
        String token = getCookie(request);
        String status = request.getParameter("STATUS");
        List<UserInfo> userInfoList = new LinkedList<>();
        UserInfo userInfo;
        if ((UserStatus.valueOf("ONLINE").toString().equals(status)) && (token != null)) {
            User user = userService.findUserByToken(token);
            userInfo = userToUserInfoConverter(user);
            usersOnline.add(userInfo);
            userInfoList = new ArrayList<UserInfo>(usersOnline);
            System.out.println(userInfoList);
            if (log.isInfoEnabled()) {
                log.info("user id = " + user.getId() + " is online");
            }
            template.convertAndSend("/topic/users", userInfoList);
            return HttpStatus.OK;
        } else if (UserStatus.OFFLINE.toString().equals(status) && (token != null)) {
            User user = userService.findUserByToken(token);
            userInfo = userToUserInfoConverter(user);
            usersOnline.remove(userInfo);
            userInfoList = new ArrayList<UserInfo>(usersOnline);
            System.out.println(userInfoList);
            if (log.isInfoEnabled()) {
                log.info("user id = " + user.getId() + " is offline");
            }
            template.convertAndSend("/topic/users", userInfoList);
            return HttpStatus.OK;
        } else {
            System.out.println(userInfoList);
            template.convertAndSend("/topic/users", userInfoList);
            return HttpStatus.BAD_REQUEST;
        }
    }

    @GetMapping({"/","/OnlineChat"})
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("loginpage");
        messageDao.findAnyParam("1","1","1","1");
        return modelAndView;
    }

    @GetMapping(value = "/OnlineChat/messages")
    public ResponseEntity<List<MessageDto>> updateHosting() {
        List<Message> messageList = messageDao.findAll();
        if (!messageList.isEmpty()) {
            List<MessageDto> messageDtoList = messageDao.findAll().stream()
                    .map(MessageToMessageDtoConverter::messageToMessageDtoConverter).collect(Collectors.toList());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<List<MessageDto>>(messageDtoList, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<MessageDto>>((MultiValueMap<String, String>) null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/OnlineChat/login")
    public ResponseEntity<UserLogin> updateHosting(@RequestBody UserLogin userLogin, HttpServletRequest request) {
        String cookieValue = getCookie(request);
        System.out.println("cookie = " + cookieValue);
        Integer userId;
        if (cookieValue == null || cookieValue.equals("null")) {
            userId = userService.loginUser(userLogin);
            if (userId != null) {
                String token = userService.findTokenByUserId(userId);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Access-Control-Expose-Headers", "Auth-Token");
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add("Auth-Token", token);
                if (log.isInfoEnabled()) {
                    log.info("user id = " + userId + " is login, new cookie");
                }
                return new ResponseEntity<UserLogin>(userLogin, headers, HttpStatus.OK);
            } else
                return new ResponseEntity<UserLogin>((UserLogin) null, HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            userId = userService.findUserByToken(cookieValue).getId();
            if (log.isInfoEnabled()) {
                log.info("user id = " + userId + " is login, old cookie");
            }
            return new ResponseEntity<UserLogin>(userLogin, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/OnlineChat/registration")
    public ResponseEntity registerUser1(@ModelAttribute UserRegistration userRegistration, @RequestParam("myfile") MultipartFile multipartFile) {
        if (userService.loginIsCorrect(userRegistration.getLogin())) {
            if (log.isInfoEnabled()) {
                log.info("registration error, login is used : " + userRegistration.getLogin());
            }
            return new ResponseEntity("this login is used", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (userService.emailIsCorrect(userRegistration.getEmail())) {
            if (log.isInfoEnabled()) {
                log.info("registration error, email is used : " + userRegistration.getEmail());
            }
            return new ResponseEntity("this email is used", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User user = userRegistrationToUserConverter(userRegistration);
        if (!multipartFile.isEmpty()) {
            try {
                saveImage(user, multipartFile);
                userService.save(user);
            } catch (Exception e) {
                log.error("don't save image : ", e);
                return new ResponseEntity("don't save image", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            userService.save(user);
        }
        if (log.isInfoEnabled()) {
            log.info("crreate new user (id): " + user.getId());
        }
        return new ResponseEntity("use succes create", HttpStatus.OK);
    }

    private void saveImage(User user, MultipartFile image) throws IOException {
        Date date = Calendar.getInstance(TimeZone.getTimeZone("GMT+7:00")).getTime();
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        byte[] bytes = image.getBytes();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("../upload/"+fileName + ".jpg")));
        stream.write(bytes);
        stream.close();
        user.setPhoto(fileName);
    }

    public String getCookie(HttpServletRequest request) {
        String cookieName = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Auth-Token")) {
                    cookieName = cookie.getValue();
                }
            }
        }
        return cookieName;
    }
    @GetMapping(value = "/OnlineChat/getImage/{imageName}")
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
        return "redirect:/";
    }
}
