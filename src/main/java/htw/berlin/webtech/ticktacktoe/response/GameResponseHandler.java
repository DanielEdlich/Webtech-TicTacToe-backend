package htw.berlin.webtech.ticktacktoe.response;

import htw.berlin.webtech.ticktacktoe.api.Game;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.stream.Collectors;

// inspired by https://medium.com/codestorm/custom-json-response-with-responseentity-in-spring-boot-b09e87ab1f0a
public class GameResponseHandler {
    public static ResponseEntity<Object> generateGameResponse(HttpStatus status, Game game){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", game.getId());
        map.put("player_1_id", game.getPlayer1_id().getId());
        if (game.getPlayer2_id() != null) {
            map.put("player_2_id", game.getPlayer2_id().getId());
        }
        map.put("isFinished", game.getIsFinished());
        map.put("grid", game.getGrid());
        return new ResponseEntity<>(map, status);
    }
    public static ResponseEntity<List<Object>> generateGameListResponse(HttpStatus status, List<Game> game){
        List<Object> list;

        list = game.stream()
                .map(g -> {
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("id", g.getId());
                    map.put("player_1_id", g.getPlayer1_id().getId());
                    if (g.getPlayer2_id() != null) {map.put("player_2_id", g.getPlayer2_id().getId());}
                    map.put("isFinished", g.getIsFinished());
                    map.put("grid", g.getGrid());
                            return map;
                        }
                )
                .collect(Collectors.toList());

        return new ResponseEntity<>(list, status);
    }

    public static ResponseEntity<Object> id(HttpStatus status, long id){
//        Map<String, Object> map = new LinkedHashMap<>();
//        map.put("id", id);
//
        return new ResponseEntity<>(Map.of("id", id), status);
    }
}
