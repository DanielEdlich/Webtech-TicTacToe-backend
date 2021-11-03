package htw.berlin.webtech.ticktacktoe.controllers;

import htw.berlin.webtech.ticktacktoe.api.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UserRestController {
        private List<User> users;

        public UserRestController(){
            users = new ArrayList<>();
            users.add(new User(1, "Max", 100));
            users.add(new User(2, "Sabrina", 200));
        }

        @GetMapping (path = "/api/v1/users")
        public ResponseEntity<List<User>> fetchUsers(){
            return ResponseEntity.ok(users);
        }

}
