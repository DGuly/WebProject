package ua.com.jee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllers {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {
        return "Login page!";
    }

}
