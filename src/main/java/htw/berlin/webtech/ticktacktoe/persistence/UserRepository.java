package htw.berlin.webtech.ticktacktoe.persistence;

import htw.berlin.webtech.ticktacktoe.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<User> findAllByName(String Name);

}
