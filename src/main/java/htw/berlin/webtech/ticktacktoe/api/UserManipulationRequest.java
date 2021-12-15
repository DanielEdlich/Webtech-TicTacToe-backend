package htw.berlin.webtech.ticktacktoe.api;

public class UserManipulationRequest {

    private String name;
    private int highscore;

    public UserManipulationRequest(String name, int highscore) {
        this.name = name;
        this.highscore = highscore;
    }

    public UserManipulationRequest() {}

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
