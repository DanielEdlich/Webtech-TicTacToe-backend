package htw.berlin.webtech.ticktacktoe.api;

public class UserCreateRequest {

    private String name;
    private int highscore;

    public UserCreateRequest(String name, int highscore) {
        this.name = name;
        this.highscore = highscore;
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
