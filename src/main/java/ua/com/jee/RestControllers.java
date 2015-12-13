package ua.com.jee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllers {

    @RequestMapping("/login")
    public String login() {
        return "Login page!";
    }

}
