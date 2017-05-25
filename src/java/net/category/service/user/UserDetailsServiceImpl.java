package net.category.service.user;


import net.category.dao.UserDao;
import net.category.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link org.springframework.security.core.userdetails.UserDetailsService}
 */
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);
        Set<GrantedAuthority> grantedAuthority = new HashSet<>();
        grantedAuthority.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),grantedAuthority);
    }
}
