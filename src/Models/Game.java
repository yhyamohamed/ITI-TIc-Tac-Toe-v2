package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Game {
    private int id;
    private String winner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void create() {

        ConnectDB connectDB = new ConnectDB();

        String sql = "insert into game (winner) values (null)";

        try (Connection con = connectDB.getConnection(); Statement st = con.createStatement()) {

            st.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save() {

        ConnectDB connectDB = new ConnectDB();

        String sql = "update game set winner = ? where game_id = ?";

        try (Connection con = connectDB.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, this.winner);
            st.setInt(2, this.id);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}