package com.example.ex4.Controllers;

import com.example.ex4.beans.UserSession;
import com.example.ex4.repo.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * This is the main controller, handles all the notations
 */
@Controller
public class SpringController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    /**
     * export the users list from database
     * @return users list
     */
    private UserRepository getRepo() {
        return userRepository;
    }

    /**
     * export the messages list from database
     * @return messages list
     */
    private MessageRepository getMsgRepo() {
        return messageRepository;
    }

    @Resource(name = "userSessionBean")
    private UserSession userSession;


    /**
     * Landing page of our site
     * @param user User
     * @param model Model
     * @return login.html page
     */
    @GetMapping("/login")
    public String login(@Valid User user, Model model) {
        model.addAttribute("user", user);
        if (userSession.getUserName() != null) {
            return "redirect:/";
        }
        return "/login";
    }

    /**
     * Implements the logout from out site, delete the user information
     * @param model Model
     * @param session HttpSession
     * @return redirect to login page
     */
    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        getRepo().delete(getRepo().findByUserName(userSession.getUserName()));
        session.invalidate();
        return "redirect:/login";
    }

    /**
     * Checks if the user is still connected, by checking if the user send a fetch request
     * in the last 10 seconds. the function is also checks if there is new messages.
     * @param id the id of the last currently message of the user screen.
     * @param model Model
     * @return true if is the last message in database, false, else.
     * @throws JSONException Exception
     */
    @GetMapping("/isOnline")
    @ResponseBody
    public String isOnline(@RequestParam("Id") long id, Model model) throws JSONException {
        List<User> user = getRepo().findAllByUserName(userSession.getUserName());
        user.get(0).setDate(new Date());
        getRepo().save(user.get(0));

        List<Message> messages = getMsgRepo().findFirst1ByOrderByIdDesc();

        if (messages.size() > 0 && id != messages.get(0).getId()) {
            return new JSONObject().put("result", false).toString();
        }
        return new JSONObject().put("result", true).toString();
    }


    /**
     * Insert new user to database
     * @param user User
     * @param message Message
     * @param result to check errors
     * @param model Model
     * @return the chatroom page
     */
    @PostMapping("/insertUser")
    public String insertUser(@Valid User user, @Valid Message message, BindingResult result, Model model) {
        if (user.userName.trim().isEmpty()) {
            model.addAttribute("error", "the name was empty");
            return "login";
        }
        int length = getRepo().findAllByUserName(user.userName).size();
        if (result.hasErrors() || length > 0) {
            model.addAttribute("error", "The name is already exists in the system");
            return "login";
        }

        userSession.setUserName(user.getUserName());
        getRepo().save(user);
        return "redirect:/";
    }

    /**
     * @param message message
     * @param result to check errors
     * @param model for thymeleaf in the html
     * @return "ok",if the succeed
     */
    @PostMapping("/addMessage")
    @ResponseBody
    public String addMessage(@Valid Message message, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:login";
        }
        message.setUserName(userSession.getUserName());
        getMsgRepo().save(message);
        return "ok";
    }

    /**
     * return the last 5 messages
     * @param model for thymeleaf in the html
     * @return messages list as a json
     */
    @GetMapping(value = "/getjson")
    public @ResponseBody
    List<Message> getAll(Model model) {
        return getMsgRepo().findFirst5ByOrderByIdDesc();
    }

    /**
     * return the users list
     * @param model for thymeleaf in the html
     * @return users list as a json
     */
    @GetMapping(value = "/getUserjson")
    public @ResponseBody
    List<User> getAllUsers(Model model) {
        return getRepo().findAll();
    }


    /**
     * mapping to search html page
     * @return search html
     */
    @GetMapping("/search")
    public String search() {
        return "search";
    }

    /**
     * this function returns all messages of a user
     * @param searchByUserName user name
     * @return list of users messages
     */
    @PostMapping("/searchByUserName")
    public @ResponseBody
    List<Message> searchByUserName(String searchByUserName) {
        return getMsgRepo().findAllByUserName(searchByUserName);
    }

    /**
     * searches for and returns the message writer
     * @param searchByMsg a message
     * @return list of messages
     */
    @PostMapping("/searchByMsg")
    public @ResponseBody
    List<Message> searchByMsg(@Valid String searchByMsg) {
        return getMsgRepo().findAllByMsgContains(searchByMsg);
    }

    /**
     * Introduces the chatroom page
     * @param message Message
     * @param model Model
     * @return the chatRoom html page
     */
    @GetMapping("/")
    public String mainWindow(Message message, Model model) {
        model.addAttribute("message", message);
        model.addAttribute("nameUser", userSession.getUserName());
        return "chatRoom";
    }
}
