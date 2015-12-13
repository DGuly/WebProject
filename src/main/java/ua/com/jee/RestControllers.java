package ua.com.jee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private boolean isDbFilled = false;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestParam("userName") String name,
                            @RequestParam("password") String password) {

        if (!isDbFilled) {
            fillDb();
            isDbFilled = true;
        }

        UserEntity userEntity = repository.findByNameIgnoringCase(name);

        if (ObjectUtils.isEmpty(userEntity)) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        } else {
            System.out.println(userEntity.toString());
            return new ResponseEntity<String>(HttpStatus.OK);
        }
    }

    private void fillDb() {
        repository.save(new UserEntity("admin", "admin", "evgeniy.baranuk@gmail.com"));
    }
}
