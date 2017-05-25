package net.category.service.user;


import net.category.dao.UserDao;
import net.category.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Implementation of {@link UserService} interface
 */
@Service
public class ServiceUserImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void update(User user) {
        if(user.getRole() == null)
            user.setRole("ROLE_USER");
        userDao.save(user);
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userDao.save(user);
    }



    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User getCurrentUser(){
        try {
            org.springframework.security.core.userdetails.User authUser =
                    (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                            .getAuthentication().getPrincipal();

            User user = findByEmail(authUser.getUsername());
            return user;
        }catch (Exception e){}
        return null;
    }

    @Override
    public User findById(long id) {
        return userDao.findOne(id);
    }

    @Override
    public boolean isLogin() {
        boolean isLogin = false;
        if(getCurrentUser() != null)
            isLogin = true;
        return isLogin;
    }

    @Override
    public boolean isAdmin() {
        boolean isAdmin = false;
        if(getCurrentUser() != null)
            isAdmin = getCurrentUser().getRole().equals("ROLE_ADMIN");
        return isAdmin;
    }
}
