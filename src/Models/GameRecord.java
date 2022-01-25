package Models;

public class GameRecord {
    private long gameID;
    private long playerXID;
    private long playerOID;
    private long stepNumber;
    private int[] step;

    public long getGameID() {
        return gameID;
    }

    public void setGameID(long gameID) {
        this.gameID = gameID;
    }

    public long getPlayerXID() {
        return playerXID;
    }

    public void setPlayerXID(long playerXID) {
        this.playerXID = playerXID;
    }

    public long getPlayerOID() {
        return playerOID;
    }

    public void setPlayerOID(long playerOID) {
        this.playerOID = playerOID;
    }

    public long getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(long stepNumber) {
        this.stepNumber = stepNumber;
    }

    public int[] getStep() {
        return step;
    }

    public void setStep(int[] step) {
        this.step = step;
    }

//    public GameRecord findByGameID(long gameID) {
//
//    }
//
//    public GameRecord[] findByPlayerID(long playerID) {
//
//    }
//
//    public GameRecord[] findByPlayersID(long playerXID, long playerOID) {
//
//    }
}
