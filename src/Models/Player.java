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

    public String getUsername() {return username;}

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

    // Create new player -Sign up-
    public boolean create(String username, String password) {
        boolean isAdded = false;        // check on added or not in database

        if (!(checkUserName(username))) {
            ConnectDB connectDB = new ConnectDB();
            String sql = "insert into player (username, hashed_password) values (?, ?)";

            try (Connection con = connectDB.getConnection(); PreparedStatement st = con.prepareStatement(sql);) {

                st.setString(1, username);
                st.setString(2, password);
                st.executeUpdate();
                isAdded = true;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isAdded;
    }

    // return true is player username exist
    public boolean checkUserName(String username) {

        ConnectDB connectDB = new ConnectDB();

        String sql = "select username from player where username=?";

        try (Connection con = connectDB.getConnection(); PreparedStatement st = con.prepareStatement(sql);) {

            st.setString(1, username);
            return st.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Player login(String username, String password) {
        Player player = new Player();
        player = player.findByUsername(username);
        if(player != null) {
            if(password.equals(player.getHashedPassword())) {
                player.setOnline(true);
                player.save(player);
            } else {
                player = null;
            }
        }
        return player;
    }

    public void logout(String username) {
        Player player = new Player();
        player = player.findByUsername(username);
        player.setOnline(false);
        player.save(player);
    }

    public void save(Player player) {
        ConnectDB connectDB = new ConnectDB();

        String sql = "update player set username = ?, hashed_password = ?, wins = ?, losses = ?, score = ?, online = ? where player_id = ?";

        try (Connection con = connectDB.getConnection(); PreparedStatement st = con.prepareStatement(sql);) {

            st.setString(1, player.getUsername());
            st.setString(2, player.getHashedPassword());
            st.setInt(3, player.getWins());
            st.setInt(4, player.getLosses());
            st.setInt(5, player.getScore());
            st.setBoolean(6, player.isOnline());
            st.setInt(7, player.getId());
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
