package com.onefactor.oauth.auth;

import com.onefactor.domain.User;
import com.onefactor.oauth.user.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created on 07.03.18.
 */
@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        return new MyUserDetails(user);
    }
}
