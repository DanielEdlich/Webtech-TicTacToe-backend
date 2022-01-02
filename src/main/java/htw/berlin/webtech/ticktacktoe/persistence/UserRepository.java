package htw.berlin.webtech.ticktacktoe.persistence;

import htw.berlin.webtech.ticktacktoe.api.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<User> findAllByName(String Name);

    Optional<UserEntity> findByName(String username);

    boolean existsByName(String username);



}
