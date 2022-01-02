package htw.berlin.webtech.ticktacktoe.controllers;

import htw.berlin.webtech.ticktacktoe.api.User;
import htw.berlin.webtech.ticktacktoe.api.UserManipulationRequest;
import htw.berlin.webtech.ticktacktoe.response.MessageResponse;
import htw.berlin.webtech.ticktacktoe.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class AuthRestController {

    private final UserService userService;

    public AuthRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserManipulationRequest loginRequest) {

        if (userService.checkUsername(loginRequest)) {
            User user = userService.findByUsername(loginRequest.getName());

            if (userService.comparePassword(user.getPassword(), loginRequest.getPassword())) {
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Username or Password are incorrect!"));

    }

    @PostMapping("/api/v1/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserManipulationRequest signUpRequest) {
        if (userService.checkUsername(signUpRequest)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user's account
        var User = userService.create(signUpRequest);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}

