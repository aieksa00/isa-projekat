package isaapp.g3malt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isaapp.g3malt.model.UserCredentials;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Integer>{
   
}
