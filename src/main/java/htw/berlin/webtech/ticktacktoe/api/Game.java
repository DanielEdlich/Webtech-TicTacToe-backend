package htw.berlin.webtech.ticktacktoe.api;

public class Game {

    private long id;
    private User player1_id;
    private User player2_id;
    private boolean isFinished;
    private String grid;

    public Game(long id, User player1_id, User player2_id, boolean isFinished, String grid) {
        this.id = id;
        this.player1_id = player1_id;
        this.player2_id = player2_id;
        this.isFinished = isFinished;
        this.grid = grid;
    }

    public Game(long id, User player1_id, boolean isFinished, String grid) {
        this.id = id;
        this.player1_id = player1_id;
        this.isFinished = isFinished;
        this.grid = grid;
    }

    public long getId() {
        return id;
    }

    public void setId_id(long id) {
        this.id = id;
    }

    public User getPlayer1_id() {
        return player1_id;
    }

    public void setPlayer1_id(User player1) {
        this.player1_id = player1;
    }

    public User getPlayer2_id() {
        return player2_id;
    }

    public void setPlayer2_id(User player2) {
        this.player2_id = player2;
    }

    public boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }
}
