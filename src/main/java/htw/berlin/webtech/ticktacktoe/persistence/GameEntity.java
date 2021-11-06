package htw.berlin.webtech.ticktacktoe.persistence;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "game")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "player1_id")
    private long player1_id;

    @Column(name = "player2_id")
    private long player2_id;

    @Column(name = "is_finished", nullable = false)
    private boolean finished;

    @Column(name = "grid", nullable = false)
    private String grid;

    protected GameEntity() {
    }

    public GameEntity(long player1_id, long player2_id, boolean finished, String grid) {
        this.player1_id = player1_id;
        this.player2_id = player2_id;
        this.finished = finished;
        this.grid = grid;
    }

    public long getId() {
        return id;
    }

    public long getPlayer1_id() {
        return player1_id;
    }

    public void setPlayer1_id(long player1_id) {
        this.player1_id = player1_id;
    }

    public long getPlayer2_id() {
        return player2_id;
    }

    public void setPlayer2_id(long player2_id) {
        this.player2_id = player2_id;
    }

    public boolean getIsFinished() {
        return finished;
    }

    public void setIsFinished(boolean finished) {
        this.finished = finished;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameEntity that = (GameEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
