package htw.berlin.webtech.ticktacktoe.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {

    private long id;
    private String name;
    private int highscore;
    @JsonIgnore
    private String password;

    public User(long id, String name, int highscore, String password) {
        this.id = id;
        this.name = name;
        this.highscore = highscore;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

