package htw.berlin.webtech.ticktacktoe.controllers;

import htw.berlin.webtech.ticktacktoe.api.Game;
import htw.berlin.webtech.ticktacktoe.api.GameManipulationRequest;
import htw.berlin.webtech.ticktacktoe.api.User;
import htw.berlin.webtech.ticktacktoe.service.GameService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameRestController.class)
class GameRestControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private GameService service;

    @Test
    @DisplayName("fetchGames test.")
    public void fetchGames() throws Exception {
        List<Game> gameList = List.of(
            new Game(
                1L,
                new User(1,"Max", 100),
                new User(2,"David", 0),
                false,
                "---------"
                ),
            new Game(
                    2L,
                    new User(1,"Max", 100),
                    new User(3,"Moritz", 1000),
                    true,
                    "xxx------"
                )
            );

        when(service.findAll()).thenReturn(gameList);

        String expected = "[" +
                "{\"id\":1,\"player_1_id\":1,\"player_2_id\":2,\"isFinished\":false,\"grid\":\"---------\"}," +
                "{\"id\":2,\"player_1_id\":1,\"player_2_id\":3,\"isFinished\":true,\"grid\":\"xxx------\"}" +
                "]";

        this.mock.perform(get("/api/v1/games/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString(expected)));
    }

    @Test
    @DisplayName("fetchGameBxId test.")
    public void fetchGameById() throws Exception {
        
        Long id = 1L;

        Game game1 = new Game(
                id,
                new User(1,"Max", 100),
                new User(2,"David", 0),
                false,
                "---------"
        );

        when(service.findById(id)).thenReturn(game1);

        String expected = "{\"id\":1,\"player_1_id\":1,\"player_2_id\":2,\"isFinished\":false,\"grid\":\"---------\"}";

        this.mock.perform(get("/api/v1/games/"+id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString(expected)));

    }

    @Test
    @DisplayName("createGame test.")
    @Disabled
    void createGame() throws Exception {
        User max = new User(1,"Max", 100);
        User david = new User(2,"David", 0);

        GameManipulationRequest request = new GameManipulationRequest(
            max.getId(),
            david.getId(),
            false,
            "---------"
        );

        Game game = new Game(1, max, david, false, "---------");

        when(service.create(request)).thenReturn(game);

        String expected = "{\"id\":1,\"player_1_id\":1,\"player_2_id\":2,\"isFinished\":false,\"grid\":\"---------\"}";

        this.mock.perform(put("/api/v1/games/"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString(expected)));
    }

}