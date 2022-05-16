package utility;

public class PlayerInfo {
    private static PlayerInfo playerInfo;
     public String username;
     public String score;
     public String wins;
     public String losses;
     public String id;
     public String login;
     public String opponentId;
     public boolean playerTurn;
     public boolean allowFire;
     public String mySign;
     public Integer gameId;
     public int opponentScore;
     public String opponentUsername;

    private PlayerInfo()
    {

    }
    public static PlayerInfo getPlayerInfo()
    {
        if(PlayerInfo.playerInfo== null) {
            PlayerInfo.playerInfo=new PlayerInfo();
            return PlayerInfo.playerInfo;
        }
        else return PlayerInfo.playerInfo;
    }
     public String getOpponentUsername()
    {
        return opponentUsername;
    }
        public int getOpponentScore()
    {
        return opponentScore;
    }
    public String getLogin() {
        return login;
    }

    public String getId() {
        return id;
    }

    public  String getLosses() {
        return losses;
    }

    public  String getScore() {
        return score;
    }

    public  String getUsername() {
        return username;
    }

    public  String getWins() {
        return wins;
    }
    public void updateScore()
    {
        score+=50;
    }
}
