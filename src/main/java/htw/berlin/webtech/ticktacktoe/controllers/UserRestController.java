package htw.berlin.webtech.ticktacktoe.controllers;

import htw.berlin.webtech.ticktacktoe.api.User;
import htw.berlin.webtech.ticktacktoe.api.UserCreateRequest;
import htw.berlin.webtech.ticktacktoe.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
public class UserRestController {



    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping (path = "/api/v1/users")
    public ResponseEntity<List<User>> fetchUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping (path = "/api/v1/users")
    public ResponseEntity<Void> createUser(@RequestBody UserCreateRequest request) throws URISyntaxException {
        var user = userService.create(request);
        URI uri = new URI("/api/v1/users/" + user.getId());
                return ResponseEntity.created(uri).build();
    }

}
