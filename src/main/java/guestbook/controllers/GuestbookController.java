package guestbook.controllers;

import java.util.Date;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import guestbook.domain.Greeting;
import guestbook.service.GuestbookService;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;

@Controller
public class GuestbookController {
    private final Logger log = Logger.getLogger(getClass().getName());

    @Autowired
    private GuestbookService guestbookService;

    @Autowired
    private UserService userService;

    @RequestMapping(Url.INDEX)
    public String index(Model model) {
        log.info("Looking at the guestbook index");
        User user = userService.getCurrentUser();

        model.addAttribute("user", user);
        model.addAttribute("loginHref", userService.createLoginURL(Url.INDEX));
        model.addAttribute("logoutHref", userService.createLogoutURL(Url.INDEX));
        model.addAttribute("greetings", guestbookService.recentGreetings(5));

        return Template.INDEX;
    }

    @RequestMapping(value=Url.CREATE, method=RequestMethod.POST)
    public String create(@RequestParam("content") String content, Model model) {
        log.info(String.format("Adding guestbook content: %s", content));

        User user = userService.getCurrentUser();
        Date date = new Date();
        Greeting greeting = new Greeting(user, content, date);
        guestbookService.addGreeting(greeting);

        return String.format("redirect:%s", Url.INDEX);
    }

    private class Url {
        public static final String INDEX = "/gb/";
        public static final String CREATE = "/gb/new/";
    }

    private class Template {
        public static final String INDEX = "gb";
    }
}
