package htw.berlin.webtech.ticktacktoe.api;

public class UserManipulationRequest {

    private String name;
    private int highscore;
    private String password;

    public UserManipulationRequest(String name, int highscore, String password) {
        this.name = name;
        this.highscore = highscore;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
