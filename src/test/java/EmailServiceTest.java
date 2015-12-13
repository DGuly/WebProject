import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.jee.Application;
import ua.com.jee.service.EmailService;

import static org.junit.Assert.*;

/**
 * Created by jsarafajr on 12/13/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @Test
    public void TestEmailService() {
        String email =
            "evgeniy.baranuk@gmail.com";
        emailService.sendText(email, "yo", "123");
    }
}