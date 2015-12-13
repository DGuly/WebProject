package ua.com.jee.systemListeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ua.com.jee.entity.UserEntity;
import ua.com.jee.repository.UserEntityRepository;
import ua.com.jee.service.EmailService;
import ua.com.jee.service.UserDetailsAdapter;
import ua.com.jee.util.AccessCode;

/**
 * Created by jsarafajr on 12/13/15.
 */
@Component
public class AuthenticationListener implements ApplicationListener<AuthenticationSuccessEvent>{

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        UserEntity user = ((UserDetailsAdapter) event.getAuthentication().getPrincipal()).getUserEntity();
        user.setCode(AccessCode.generateCode());

        emailService.sendAccessCode(user);

        userEntityRepository.save(user);
    }
}
