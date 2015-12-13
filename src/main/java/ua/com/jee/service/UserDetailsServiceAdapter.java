package ua.com.jee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.jee.entity.UserEntity;
import ua.com.jee.repository.UserEntityRepository;

/**
 * Created by jsarafajr on 12/13/15.
 */
@Service
@Primary
public class UserDetailsServiceAdapter implements UserDetailsService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userEntityRepository.findByName(s);
        return new UserDetailsAdapter(user);
    }
}
