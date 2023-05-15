package andrevier.myissuetracker.myissuetracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import andrevier.myissuetracker.myissuetracker.dto.UserRequestDto;
import andrevier.myissuetracker.myissuetracker.model.UserData;

public interface UserRepository extends JpaRepository<UserData, Long>{
    public UserData findByEmail(String email);

    public UserData findByUserId(Long userId);

    @Query(value = "SELECT u.user_id as userId,"
    + " u.user_name as userName,"
    + " u.user_email as email,"
    + " u.password as password"
    + " FROM user_data u", nativeQuery = true)
    public List<UserRequestDto> findAllUsers();
    
}
