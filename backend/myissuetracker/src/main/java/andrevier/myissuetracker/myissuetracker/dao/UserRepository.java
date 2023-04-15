package andrevier.myissuetracker.myissuetracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import andrevier.myissuetracker.myissuetracker.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
    
}
