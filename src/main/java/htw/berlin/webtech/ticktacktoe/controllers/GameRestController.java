package htw.berlin.webtech.ticktacktoe.controllers;

import htw.berlin.webtech.ticktacktoe.api.Game;
import htw.berlin.webtech.ticktacktoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameRestController {

    @Autowired
    GameService service;

    @GetMapping(path = "/api/v1/games")
    public ResponseEntity<List<Game>> fetchGames(){
        return ResponseEntity.ok(service.findAll());
    }



}
