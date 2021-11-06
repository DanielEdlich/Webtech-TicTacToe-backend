package htw.berlin.webtech.ticktacktoe.service;

import htw.berlin.webtech.ticktacktoe.api.User;
import htw.berlin.webtech.ticktacktoe.api.UserManipulationRequest;
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

    public User findById(Long id){
        var userEntity = userRepository.findById(id);
        return userEntity.isPresent()? transformEntity(userEntity.get()) : null;
    }

    public User create(UserManipulationRequest request){
        var userEntity = new UserEntity(request.getName(), request.getHighscore());
        userEntity = userRepository.save(userEntity);
        return transformEntity(userEntity);
    }

    public User update(Long id, UserManipulationRequest request){
        var userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.isEmpty()){return null;}

        var userEntity = userEntityOptional.get();
        userEntity.setName(request.getName());
        userEntity.setHighscore(request.getHighscore());
        userEntity = userRepository.save(userEntity);
        return transformEntity(userEntity);
    }

    public boolean deleteById(long id){
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    private User transformEntity(UserEntity userEntity){
        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getHighscore()
        );
    }
}