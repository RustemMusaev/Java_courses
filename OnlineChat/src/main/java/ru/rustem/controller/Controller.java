package ru.rustem.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.rustem.converter.MessageToMessageDtoConverter;
import ru.rustem.dao.MessageDao;
import ru.rustem.dto.*;
import ru.rustem.model.Message;
import ru.rustem.model.User;
import ru.rustem.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    private Set<UserInfo> usersOnline = new HashSet<>();//sinhronaize

    @PostMapping("/chats/messages")
    public void messageToChat(@RequestBody Message message, HttpServletRequest request) {
        String token = getCookie(request);
        if (token!=null) {
            User user = userService.findUserByToken(token);
            message.setUser(user);
            messageDao.save(message);
            MessageDto messageDto = new MessageDto();
            messageDto.setText(message.getMessage());
            messageDto.setLogin(message.getUser().getLogin());
            template.convertAndSend("/topic/messages",messageDto);
        }
    }
    @PostMapping("/chats/users")
    public void userStatus(@RequestBody UserStatus userStatus, HttpServletRequest request) {
        String token = getCookie(request);
        List<UserInfo> userInfoList = new LinkedList<>();
        UserInfo userInfo;
        if (userStatus.getStatus().equals("online") && (token != null)) {
            User user = userService.findUserByToken(token);
            userInfo = userToUserInfoConverter(user);
            usersOnline.add(userInfo);
            userInfoList = new ArrayList<UserInfo>(usersOnline);
            System.out.println(userInfoList);
            template.convertAndSend("/topic/users",userInfoList);
        } else if (userStatus.getStatus().equals("offline")) {
            User user = userService.findUserByToken(token);
            userInfo = userToUserInfoConverter(user);
            usersOnline.remove(userInfo);
            userInfoList = new ArrayList<UserInfo>(usersOnline);
            System.out.println(userInfoList);
            template.convertAndSend("/topic/users",userInfoList);
        } else {
            System.out.println(userInfoList);
            template.convertAndSend("/topic/users",userInfoList);
        }
    }

    @GetMapping(value = "/")
    @ResponseBody ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("loginpage");
        modelAndView.addObject("userRegistration",new UserRegistration());
        return modelAndView;
    }
    @GetMapping(value = "/messages")
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
    @PostMapping(value = "/login1")
    public ResponseEntity<UserLogin> updateHosting(@RequestBody UserLogin userLogin, HttpServletRequest request) {
        String cookieValue = getCookie(request) ;
        System.out.println("cookie = "+cookieValue);
        Integer userId = null;
        if (cookieValue == null || cookieValue.equals("null")) {
            userId = userService.loginUser(userLogin);
            if (userId != null) {
                String token = userService.findTokenByUserId(userId);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Access-Control-Expose-Headers", "Auth-Token");
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add("Auth-Token", token);
                return new ResponseEntity<UserLogin>(userLogin, headers, HttpStatus.OK);
            } else
                return new ResponseEntity<UserLogin>((UserLogin) null, HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            userId = userService.findUserByToken(cookieValue).getId();
            return new ResponseEntity<UserLogin>(userLogin, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLogin userLogin) {
        Set<User> users = userService.findAll();
        Integer userId = userService.loginUser(userLogin);
        if(userId!=null){
            User user = userService.find(userId);
            String token = userService.findTokenByUserId(userId);
            HttpHeaders headers = new HttpHeaders();
         //   headers.add("Access-Control-Expose-Headers", "Auth-Token");
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Auth-Token", token);
            return new ResponseEntity<String>(String.valueOf(users),headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(String.valueOf(users), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
    @PostMapping(value = "/registration")
    public ModelAndView registerUser(@ModelAttribute("userRegistration") UserRegistration userRegistration) {
        ModelAndView modelAndView = new ModelAndView("loginpage");
        modelAndView.addObject("userRegistration",new UserRegistration());
        if (!userService.loginIsCorrect(userRegistration.getLogin())) {
            return modelAndView;
        }
        User user = userRegistrationToUserConverter(userRegistration);
        if (!userRegistration.getFile().isEmpty()) {
            Date date = Calendar.getInstance(TimeZone.getTimeZone("GMT+7:00")).getTime();
            String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
            try {
                byte[] bytes = userRegistration.getFile().getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(servletContext.getRealPath("/") +
                        "/img/" + fileName + ".jpg")));
                stream.write(bytes);
                stream.close();
                user.setPhoto(fileName);
                userService.save(user);
            } catch (Exception e) {
                return modelAndView;//
            }
        } else {
            userService.save(user);
        }
        return modelAndView;
    }
    private void validateImage(MultipartFile image) {
        if (!image.getContentType().equals("image/jpeg")) {
            throw new RuntimeException("Only JPG images are accepted");
        }
    }
    private void saveImage(String filename, MultipartFile image) throws RuntimeException, IOException {
        try {
            File file = new File(servletContext.getRealPath("/") + "/" + filename);

            FileUtils.writeByteArrayToFile(file, image.getBytes());
            System.out.println("Go to the location:  " + file.toString()
                    + " on your computer and verify that the image has been stored.");
        } catch (IOException e) {
            throw e;
        }
    }
    public String getCookie(HttpServletRequest request){
        String cookieName=null;
        // получаем все куки
        Cookie[] cookies = request.getCookies();
        // есди куки непустые
        if(cookies !=null){
            // идем по всем кукам
            for(Cookie cookie : cookies){
                // если у нас печенька с атрибутом user совпала
                if(cookie.getName().equals("Auth-Token")) {
                    // сохраняем значение в переменную userName
                    cookieName = cookie.getValue();
                }
            }
        } return cookieName;
    }

}
