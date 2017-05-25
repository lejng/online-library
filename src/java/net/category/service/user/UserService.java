package net.category.service.user;

import net.category.model.User;

/**
 * Service class for {@link net.category.model.User}
 */
public interface UserService {

    void save(User user);

    void update(User user);

    User findByEmail(String email);

    User getCurrentUser();

    User findById(long id);

    boolean isAdmin();

    boolean isLogin();
}
