package com.gradProj.HUrry.Repositories;
import com.gradProj.HUrry.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);

}
