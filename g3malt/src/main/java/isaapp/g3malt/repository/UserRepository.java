package isaapp.g3malt.repository;

import isaapp.g3malt.model.User;
import net.bytebuddy.asm.Advice.This;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByJmbg(String jmbg);
}
