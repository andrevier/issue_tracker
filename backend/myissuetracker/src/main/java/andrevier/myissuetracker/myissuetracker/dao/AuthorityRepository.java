package andrevier.myissuetracker.myissuetracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import andrevier.myissuetracker.myissuetracker.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{

    @Query(value = "SELECT * FROM authority a"
    + " WHERE a.role = :role", nativeQuery = true)
    List<Authority> findAllAuthoritiesWithRole(String role);
    
}
