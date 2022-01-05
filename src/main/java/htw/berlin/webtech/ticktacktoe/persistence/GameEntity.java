package htw.berlin.webtech.ticktacktoe.persistence;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import htw.berlin.webtech.ticktacktoe.api.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "game")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    // inspired by https://stackoverflow.com/a/50378345
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_1_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity player1;

    @Column(name = "users_1_id")
    private Long player1_id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_2_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity player2;

    @Column(name = "users_2_id")
    private Long player2_id;


    @Column(name = "is_finished", nullable = false)
    private boolean finished;

    @Column(name = "grid", nullable = false)
    private String grid;

    protected GameEntity() {
    }

    public GameEntity(UserEntity player1, UserEntity player2, boolean finished, String grid) {
        this.player1 = player1;
        this.player2 = player2;
        this.finished = finished;
        this.grid = grid;

        fetchPlayer1_id(this.player1);
        fetchPlayer2_id(this.player2);
    }

    public GameEntity(UserEntity player1, boolean finished, String grid) {
        this.player1 = player1;
        this.finished = finished;
        this.grid = grid;

        fetchPlayer1_id(this.player1);
    }

    public long getId() {
        return id;
    }

    public UserEntity getPlayer1() {
        return player1;
    }

    public void setPlayer1(UserEntity player1) {
        this.player1 = player1;
        this.player1_id = player1.getId();
    }

    public UserEntity getPlayer2() {
        return player2;
    }

    public void setPlayer2(UserEntity player2) {
        this.player2 = player2;
        this.player2_id = player2.getId();
    }

    public boolean IsFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
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

    public Long getPlayer1_id() {
        return player1_id;
    }

    public void setPlayer1_id(Long player1_id) {
        this.player1_id = player1_id;
    }

    private void fetchPlayer1_id(UserEntity player1) {
        this.player1_id = player1.getId();
    }

    public Long getPlayer2_id() {
        return player2_id;
    }

    public void setPlayer2_id(Long player2_id) {
        this.player2_id = player2_id;
    }

    public void fetchPlayer2_id(UserEntity player2) {
        this.player2_id = player2.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public void setId(long id) {
        this.id = id;
    }
}
