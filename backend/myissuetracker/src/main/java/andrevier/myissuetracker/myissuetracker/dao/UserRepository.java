package andrevier.myissuetracker.myissuetracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import andrevier.myissuetracker.myissuetracker.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    public User findByEmail(String email);

    // @Query("SELECT u.user_id, u.email, u.userName, u.password FROM User u")
    // public List<User> findUsers();
    
}
