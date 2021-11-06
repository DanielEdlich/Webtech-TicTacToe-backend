package htw.berlin.webtech.ticktacktoe.service;

import htw.berlin.webtech.ticktacktoe.api.Game;
import htw.berlin.webtech.ticktacktoe.persistence.GameEntity;
import htw.berlin.webtech.ticktacktoe.persistence.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    GameRepository repo;

    public List<Game> findAll(){
        List<GameEntity> gameEntities = repo.findAll();
        return gameEntities.stream()
                .map(GameEntity ->
                    new Game(
                            GameEntity.getId(),
                            GameEntity.getPlayer1_id(),
                            GameEntity.getPlayer2_id(),
                            GameEntity.getIsFinished(),
                            GameEntity.getGrid()
                        )
                ).collect(Collectors.toList());

    }




}
