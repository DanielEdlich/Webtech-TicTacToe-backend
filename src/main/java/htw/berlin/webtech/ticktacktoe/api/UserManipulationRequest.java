package htw.berlin.webtech.ticktacktoe.api;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;

public class UserManipulationRequest {
    @Size(min = 3, message = "Please provide a Username with 3 characters or more.")
    private String name;
    @PositiveOrZero(message = "Please be sure that Highscore is 0 or higher.")
    private int highscore;
    @Size(min = 4, max = 20, message = "Please provide a Password between 4 and 20 characters.")
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
