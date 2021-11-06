package htw.berlin.webtech.ticktacktoe.service;

import htw.berlin.webtech.ticktacktoe.api.Game;
import htw.berlin.webtech.ticktacktoe.api.GameManipulationRequest;
import htw.berlin.webtech.ticktacktoe.persistence.GameEntity;
import htw.berlin.webtech.ticktacktoe.persistence.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    GameRepository repo;

    private Game transformEntity(GameEntity entity){
        return new Game(
                entity.getId(),
                entity.getPlayer1_id(),
                entity.getPlayer2_id(),
                entity.getIsFinished(),
                entity.getGrid()
        );
    }

    public List<Game> findAll(){
        List<GameEntity> gameEntities = repo.findAll();
        return gameEntities.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());

    }

    public Game create(GameManipulationRequest request) {
        var gameEntity = new GameEntity(request.getPlayer1_id(), request.getPlayer2_id(), request.isFinished(), request.getGrid());
        gameEntity = repo.save(gameEntity);
        return transformEntity(gameEntity);
    }

    public Game findById(Long id){
//        var gameEntity = repo.findById(id);
//        return gameEntity.isPresent()? transformEntity(gameEntity.get()): null;

        return repo.findById(id).map(this::transformEntity).orElse(null);
    }

    public Game update(Long id, GameManipulationRequest request) {
        var optionaleEntity = repo.findById(id);
        if(optionaleEntity.isEmpty()) {
            return null;
        }
        else {
            var gameEntity = optionaleEntity.get();
            gameEntity.setPlayer1_id(request.getPlayer1_id());
            gameEntity.setPlayer2_id(request.getPlayer2_id());
            gameEntity.setIsFinished(request.isFinished());
            gameEntity.setGrid(request.getGrid());

            return transformEntity(repo.save(gameEntity));
        }
    }

    public boolean deleteGame(Long id) {
        if(!repo.existsById(id)){
            return false;
        }
        else {
            repo.deleteById(id);
            return true;
        }

    }
}
