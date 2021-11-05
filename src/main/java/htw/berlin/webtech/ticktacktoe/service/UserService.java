package htw.berlin.webtech.ticktacktoe.service;

import htw.berlin.webtech.ticktacktoe.api.User;
import htw.berlin.webtech.ticktacktoe.api.UserCreateRequest;
import htw.berlin.webtech.ticktacktoe.persistence.UserEntity;
import htw.berlin.webtech.ticktacktoe.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(userEntity -> new User(
                        userEntity.getId(),
                        userEntity.getName(),
                        userEntity.getHighscore()
                ))
                .collect(Collectors.toList());
    }
    public User create(UserCreateRequest request){
        var userEntity = new UserEntity(request.getName(), request.getHighscore());
        userEntity = userRepository.save(userEntity);
        return transformEntity(userEntity);
    }

    private User transformEntity(UserEntity userEntity){
        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getHighscore()
        );
    }
}
