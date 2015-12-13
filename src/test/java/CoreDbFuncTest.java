import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.jee.Application;
import ua.com.jee.repository.UserEntityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class CoreDbFuncTest {
    @Autowired
    private UserEntityRepository repository;

    @Test
    public void checkDbConnectionSuccessfull() {
        //repository.save(new UserEntity("Jeka", "password123"));
        System.out.println(repository.findByNameIgnoringCase("jeka"));
    }
}
