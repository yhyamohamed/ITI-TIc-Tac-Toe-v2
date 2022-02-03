package Models;

import application.AppConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectDB {
    String databaseName = AppConfig.databaseName;
    String databasePassword = AppConfig.databasePassword;

    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/tik_tak_tok", databaseName, databasePassword);

    }

}





