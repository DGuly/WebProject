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
import ua.com.jee.service.EmailService;

import java.util.Random;

@RestController
public class RestControllers {

    @Autowired
    private UserEntityRepository repository;

    @Autowired
    private EmailService emailService;

    private boolean isDbFilled = false;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestParam("userName") String name,
                            @RequestParam("password") String password) {

        if (!isDbFilled) {
            fillDb();
            isDbFilled = true;
        }

        UserEntity userEntity = repository.findByName(name);

        if (ObjectUtils.isEmpty(userEntity)) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        } else {
            userEntity.setCode(generateCode());
            System.out.println(userEntity.toString());
            emailService.sendAccessCode(userEntity);

            return new ResponseEntity<String>(HttpStatus.OK);

        }
    }

    private void fillDb() {
        repository.save(new UserEntity("admin", "admin", "evgeniy.baranuk@gmail.com"));
    }

    private String generateCode() {
        Random rnd = new Random();
        Integer n = 100000 + rnd.nextInt(900000);
        return n.toString();
    }
}
