package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectDB {

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/tik_tak_tok", "root", "root");
    }

}





