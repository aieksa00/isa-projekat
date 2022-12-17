package isaapp.g3malt.repository;

import isaapp.g3malt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import isaapp.g3malt.model.UserCredentials;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Integer>{

    UserCredentials findByEmail(String email);
    UserCredentials findByUserId(Integer userId);
    User findByid(Integer id);
}
