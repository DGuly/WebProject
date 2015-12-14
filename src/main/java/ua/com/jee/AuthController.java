package ua.com.jee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.jee.entity.UserEntity;
import ua.com.jee.repository.UserEntityRepository;
import ua.com.jee.service.UserDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jsarafajr on 12/13/15.
 */
@Controller
public class AuthController {

    @Autowired
    UserEntityRepository userEntityRepository;

    @RequestMapping(value = "/code",method = RequestMethod.POST)
    public String verifyCode(@RequestParam String code) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = ((UserDetailsAdapter) auth.getPrincipal()).getUserEntity();

        if (user.getCode().equals(code)) {
            grantAuthority();
            return "redirect:/data";
        }
        return "redirect:/code?error";
    }

    @RequestMapping("/code")
    public String codePage() {
        return "code";
    }

    private void grantAuthority() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(auth.getAuthorities());
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), authorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
