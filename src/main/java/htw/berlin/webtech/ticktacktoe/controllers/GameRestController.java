package htw.berlin.webtech.ticktacktoe.controllers;

import htw.berlin.webtech.ticktacktoe.api.Game;
import htw.berlin.webtech.ticktacktoe.api.GameManipulationRequest;
import htw.berlin.webtech.ticktacktoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class GameRestController {

    @Autowired
    GameService service;

    @GetMapping(path = "/api/v1/games")
    public ResponseEntity<List<Game>> fetchGames(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(path = "/api/v1/games/{id}")
    public ResponseEntity<Game> fetchGameById(@PathVariable Long id){
        var game = service.findById(id);
        return game != null? ResponseEntity.ok(game): ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/games")
    public ResponseEntity<Void> createGame(@RequestBody GameManipulationRequest request) throws URISyntaxException {
        var game = service.create(request);
        URI uri = new URI("/api/v1/games/" + game.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/api/v1/games/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody GameManipulationRequest request){
        var game = service.update(id, request);
        return game != null? ResponseEntity.ok(game): ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/games/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id){
        boolean successful = service.deleteGame(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
