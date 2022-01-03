package htw.berlin.webtech.ticktacktoe.controllers;
import htw.berlin.webtech.ticktacktoe.api.User;
import htw.berlin.webtech.ticktacktoe.persistence.UserRepository;
import org.hamcrest.Matchers;
import htw.berlin.webtech.ticktacktoe.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
    @DisplayName("should return 404 if person is not found")
    void personNotFoundTest() throws Exception {
        // given
        doReturn(null).when(userService).findById(anyLong());

        // when
        mockMvc.perform(get("/api/v1/users/200"))
                // then
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should return 201 http status when creating a person")
    void CreatePersonTest() throws Exception {
        // given
        String personToCreateAsJson = "{\"name\": \"Django\", \"highscore\":\"10\"}";
        var user = new User(123, "Django", 10, "abcde");
        doReturn(user).when(userService).create(any());

        // when
        mockMvc.perform(
                        post("/api/v1/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(personToCreateAsJson)
                )
                // then
                .andExpect(status().isCreated());
    }
    @Test
    @DisplayName("should validate create user on Name attribute request")
    void validationTestName() throws Exception {
        // given
        String userToCreateAsJson = "{\"name\": \"TJ\", \"highscore\":\"0\"}";

        // when
        mockMvc.perform(
                        post("/api/v1/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userToCreateAsJson)
                )
                // then
                .andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("should validate create person on Highscore attribute request")
    void validationTestHighscore() throws Exception {
        // given
        String userToCreateAsJson = "{\"name\": \"Django\", \"highscore\":\"-1\"}";

        // when
        mockMvc.perform(
                        post("/api/v1/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userToCreateAsJson)
                )
                // then
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("should return all found users Example made for 2")
    void returnFoundUsers() throws Exception {
        // given
        var users = List.of(
                new User(1, "Django", 5, "abcde"),
                new User(2, "Playerxxx", 11, "abcde")
        );
        doReturn(users).when(userService).findAll();

        // when
        mockMvc.perform(get("/api/v1/users"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Django"))
                .andExpect(jsonPath("$[0].highscore").value(5))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Playerxxx"))
                .andExpect(jsonPath("$[1].highscore").value(11));
    }

}
