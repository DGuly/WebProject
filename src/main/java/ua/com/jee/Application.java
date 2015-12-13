package ua.com.jee;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.jee.entity.UserEntityRepository;

@SpringBootApplication
public class Application {

    @Autowired
    private UserEntityRepository userEntityRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }


}
