package ua.com.jee.systemListeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.com.jee.entity.UserEntity;
import ua.com.jee.repository.UserEntityRepository;

/**
 * Created by jsarafajr on 12/13/15.
 */
@Component
public class OnStartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserEntityRepository userEntityRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        userEntityRepository.save(new UserEntity("admin", passwordEncoder.encode("admin"), "evgeniy.baranuk@gmail.com"));
        userEntityRepository.save(new UserEntity("dmytro", passwordEncoder.encode("dmytro"), "dmytry.guly@gmail.com"));
    }
}
