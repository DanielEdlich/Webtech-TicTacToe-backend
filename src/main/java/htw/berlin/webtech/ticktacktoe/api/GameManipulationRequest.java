package htw.berlin.webtech.ticktacktoe.api;

import javax.validation.constraints.Size;

public class GameManipulationRequest {

    private long player1_id;
    private long player2_id;
    private boolean isFinished;
    @Size(min = 9, max = 9, message= "Please provide a Grid with 9 characters.")
    private String grid;

    public GameManipulationRequest(long player1_id, long player2_id, boolean isFinished, String grid) {
        this.player1_id = player1_id;
        this.player2_id = player2_id;
        this.isFinished = isFinished;
        this.grid = grid;
    }

    public GameManipulationRequest() {}

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

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }
}
