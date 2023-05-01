package andrevier.myissuetracker.myissuetracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import andrevier.myissuetracker.myissuetracker.dto.UserRequestDto;
import andrevier.myissuetracker.myissuetracker.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    public User findByEmail(String email);

    public User findByUserId(Long userId);

    @Query(value = "SELECT u.user_id as userId,"
    + " u.user_name as userName,"
    + " u.email as email"
    + " u.password as password"
    + " FROM user_data as u", nativeQuery = true)
    public List<UserRequestDto> findAllUsers();
    
}
