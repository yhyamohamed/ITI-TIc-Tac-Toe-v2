package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectDB {
/*
* change user: username (it might be root)
* password: add your password
* */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/tik_tak_tok", "root", "Hegabo192");
    }

}





