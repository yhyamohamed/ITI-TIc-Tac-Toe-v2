package Models;

import java.sql.*;
import java.util.ArrayList;

public class Player {
    private int id;
    private String username;
    private String hashedPassword;
    private int wins;
    private int losses;
    private int score;
    private boolean online;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void create(String username, String password) {

        ConnectDB connectDB = new ConnectDB();


        String sql = "insert into player (username, hashed_password) values (?, ?)";

        try (Connection con = connectDB.getConnection(); PreparedStatement st = con.prepareStatement(sql);) {

            st.setString(1, username);
            st.setString(2, password);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        ConnectDB connectDB = new ConnectDB();

        String sql = "update player set username = ?, hashed_password = ?, wins = ?, losses = ?, score = ?, online = ? where player_id = ?";

        try (Connection con = connectDB.getConnection(); PreparedStatement st = con.prepareStatement(sql);) {

            st.setString(1, this.username);
            st.setString(2, this.hashedPassword);
            st.setInt(3, this.wins);
            st.setInt(4, this.losses);
            st.setInt(5, this.score);
            st.setBoolean(6, this.online);
            st.setInt(7, this.id);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Player findByUsername(String username) {

        ConnectDB connectDB = new ConnectDB();

        String sql = "select * from player where username = ?";

        Player player = null;

        try (Connection con = connectDB.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            player = new Player();

            while (rs.next()) {
                player.setUsername(rs.getString(1));        //username
                player.setId(rs.getInt(2));                //id
                player.setOnline(rs.getBoolean(3));        //online
                player.setHashedPassword(rs.getString(4)); //pw
                player.setWins(rs.getInt(5));              //wins
                player.setLosses(rs.getInt(6));            //losses
                player.setScore(rs.getInt(7));             //score
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return player;
    }

    public ArrayList<Player> findOnlinePlayers() {

        ConnectDB connectDB = new ConnectDB();

        String sql = "select * from player where online = true";

        ArrayList<Player> onlinePlayers = new ArrayList<>();

        try (Connection con = connectDB.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql);) {

            while (rs.next()) {
                Player player = new Player();

                player.setUsername(rs.getString(1));        //username
                player.setId(rs.getInt(2));                //id
                player.setOnline(rs.getBoolean(3));        //online
                player.setHashedPassword(rs.getString(4)); //pw
                player.setWins(rs.getInt(5));              //wins
                player.setLosses(rs.getInt(6));            //losses
                player.setScore(rs.getInt(7));

                onlinePlayers.add(player);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return onlinePlayers;
    }

    public ArrayList<Player> findOfflinePlayers() {

        ConnectDB connectDB = new ConnectDB();

        String sql = "select * from player where online = false";

        ArrayList<Player> offlinePlayers = new ArrayList<>();

        try (Connection con = connectDB.getConnection(); Statement st = con.createStatement();) {

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Player player = new Player();

                player.setUsername(rs.getString(1));        //username
                player.setId(rs.getInt(2));                //id
                player.setOnline(rs.getBoolean(3));        //online
                player.setHashedPassword(rs.getString(4)); //pw
                player.setWins(rs.getInt(5));              //wins
                player.setLosses(rs.getInt(6));            //losses
                player.setScore(rs.getInt(7));

                offlinePlayers.add(player);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return offlinePlayers;
    }

}
