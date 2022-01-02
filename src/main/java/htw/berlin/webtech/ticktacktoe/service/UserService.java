package htw.berlin.webtech.ticktacktoe.service;

import htw.berlin.webtech.ticktacktoe.api.User;
import htw.berlin.webtech.ticktacktoe.api.UserManipulationRequest;
import htw.berlin.webtech.ticktacktoe.persistence.UserEntity;
import htw.berlin.webtech.ticktacktoe.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.MessageDigestSpi;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

/*    @Autowired
    PasswordEncoder encoder;*/

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(UserService::transformUserEntity)
                .collect(Collectors.toList());
    }

    public User findById(Long id){
        var userEntity = userRepository.findById(id);
        return userEntity.isPresent()? transformUserEntity(userEntity.get()) : null;
    }

    public UserEntity findEntityById(Long id){
        var userEntity = userRepository.findById(id);
        return userEntity.orElse(null);
    }

    public boolean checkUsername(UserManipulationRequest request){
        return userRepository.existsByName(request.getName());
    }

    public User findByUsername(String username){
        var userEntity = userRepository.findByName(username);

        return userEntity.isEmpty() ? null : transformUserEntity(userEntity.get());
    }

    public User create(UserManipulationRequest request){
        var userEntity = new UserEntity(request.getName(), request.getHighscore(), encryptPassword(request.getPassword()));
        userEntity = userRepository.save(userEntity);
        return transformUserEntity(userEntity);
    }

    public User update(Long id, UserManipulationRequest request){
        var userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.isEmpty()){return null;}

        var userEntity = userEntityOptional.get();
        userEntity.setName(request.getName());
        userEntity.setHighscore(request.getHighscore());
        userEntity = userRepository.save(userEntity);
        return transformUserEntity(userEntity);
    }

    public boolean deleteById(long id){
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    static User transformUserEntity(UserEntity userEntity){
        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getHighscore(),
                userEntity.getPassword()
        );
    }

    public boolean comparePassword(String encodedPassword, String password) {

        return encodedPassword.equals(encryptPassword(password));
//       return encoder.matches(password, encodedPassword);
    }

    private String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return Arrays.toString(digest.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }


}
