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

import java.util.List;
import java.util.Random;

@RestController
public class RestControllers {

    @Autowired
    private UserEntityRepository repository;

    @Autowired
    private EmailService emailService;

    private boolean isDbFilled = false;


    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String getData() {
        return repository.findAll().toString();
    }
}
