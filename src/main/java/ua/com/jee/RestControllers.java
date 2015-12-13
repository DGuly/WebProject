package ua.com.jee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.jee.entity.UserEntity;
import ua.com.jee.repository.UserEntityRepository;

@RestController
public class RestControllers {

    @Autowired
    private UserEntityRepository repository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public HttpStatus login(@RequestParam("userName") String name,
                            @RequestParam("password") String password) {

        UserEntity userEntity = repository.findByNameIgnoringCase(name);

        if (ObjectUtils.isEmpty(userEntity)) {
            return HttpStatus.BAD_REQUEST;
        } else {
            System.out.println(userEntity.toString());
            return HttpStatus.OK;
        }
    }
}
