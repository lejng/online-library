package net.category.dao;

import net.category.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
