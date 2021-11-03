package htw.berlin.webtech.ticktacktoe.api;

public class User {

    private long id;
    private String name;
    private int highscore;

    public User(long id, String name, int highscore) {
        this.id = id;
        this.name = name;
        this.highscore = highscore;
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
}

