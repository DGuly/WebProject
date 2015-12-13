package ua.com.jee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.jee.entity.UserEntity;

@RestController
public class RestControllers {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public HttpStatus login(@RequestParam("userName") String name,
                            @RequestParam("password") String password) {
        UserEntity userEntity = new UserEntity(name, password);
        System.out.println(userEntity.toString());
        return HttpStatus.OK;
    }

}
