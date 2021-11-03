package htw.berlin.webtech.ticktacktoe.persistence;

import javax.persistence.*;

@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name= "Highscore")
    private int highscore;

    public UserEntity(String name, int highscore) {
        this.name = name;
        this.highscore = highscore;
    }

    protected UserEntity(){}

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
}
