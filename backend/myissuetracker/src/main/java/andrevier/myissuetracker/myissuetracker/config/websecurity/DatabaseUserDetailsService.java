package andrevier.myissuetracker.myissuetracker.config.websecurity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import andrevier.myissuetracker.myissuetracker.dao.UserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import andrevier.myissuetracker.myissuetracker.model.UserData;


public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Username is the email.
        UserData userData = this.userRepository.findByEmail(username);
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        grantedAuthorities.add(grantedAuthority);
        User user = new User(
            userData.getEmail(), userData.getPassword(), grantedAuthorities);
        System.out.println(username + " / " + userData.getUserName());
        return user;
    }
    
}
