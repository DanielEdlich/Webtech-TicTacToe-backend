package htw.berlin.webtech.ticktacktoe.service;

import htw.berlin.webtech.ticktacktoe.api.Game;
import htw.berlin.webtech.ticktacktoe.api.GameManipulationRequest;
import htw.berlin.webtech.ticktacktoe.persistence.GameEntity;
import htw.berlin.webtech.ticktacktoe.persistence.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static htw.berlin.webtech.ticktacktoe.service.UserService.transformUserEntity;

@Service
public class GameService {

    @Autowired
    GameRepository repo;
    @Autowired
    UserService userService;

    private Game transformGameEntity(GameEntity entity){
        if (entity.getPlayer2() == null){
            return new Game(
                    entity.getId(),
                    transformUserEntity(entity.getPlayer1()),
                    entity.IsFinished(),
                    entity.getGrid()
            );
        }
        else {
            return new Game(
                    entity.getId(),
                    // Return full User object
                    transformUserEntity(entity.getPlayer1()),
                    transformUserEntity(entity.getPlayer2()),
//                alternative
//                new User(entity.getPlayer1_id().getId(),entity.getPlayer1_id().getName(),entity.getPlayer1_id().getHighscore()),
//                new User(entity.getPlayer2_id().getId(),entity.getPlayer2_id().getName(),entity.getPlayer2_id().getHighscore()),
                    entity.IsFinished(),
                    entity.getGrid()
            );
        }
    }

    public List<Game> findAll(){
        List<GameEntity> gameEntities = repo.findAll();
        return gameEntities.stream()
                .map(this::transformGameEntity)
                .collect(Collectors.toList());

    }

    public Game create(GameManipulationRequest request) {
        GameEntity gameEntity;
        if (request.getPlayer2_id() == 0) {
            gameEntity = new GameEntity(
                    userService.findEntityById(request.getPlayer1_id()),
                    request.isFinished(),
                    request.getGrid());
        }
        else {
            gameEntity = new GameEntity(
                    userService.findEntityById(request.getPlayer1_id()),
                    userService.findEntityById(request.getPlayer2_id()),
                    request.isFinished(),
                    request.getGrid());
        }
        gameEntity = repo.save(gameEntity);
        return transformGameEntity(gameEntity);
    }

    public Game findById(Long id){
//        alternative
//        var gameEntity = repo.findById(id);
//        return gameEntity.isPresent()? transformEntity(gameEntity.get()): null;

        return repo.findById(id).map(this::transformGameEntity).orElse(null);
    }

    public Game update(Long id, GameManipulationRequest request) {
        var optionaleEntity = repo.findById(id);
        if(optionaleEntity.isEmpty()) {
            return null;
        }
        else {
            var gameEntity = optionaleEntity.get();
            gameEntity.setPlayer1(userService.findEntityById(request.getPlayer1_id()));
            if (request.getPlayer2_id() != 0) {
                gameEntity.setPlayer2(userService.findEntityById(request.getPlayer2_id()));
            }
            gameEntity.setFinished(request.isFinished());
            gameEntity.setGrid(request.getGrid());

            var changedGameEntity =repo.save(gameEntity);
            return transformGameEntity(changedGameEntity);
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
