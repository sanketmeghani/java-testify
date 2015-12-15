package dev.sanket.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo
{
    private Connection connection;

    public void loadDriver() throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
    }

    public void connect() throws SQLException
    {
        connection = DriverManager.getConnection("jdbc:mysql://localhost/of_dev?" + "user=root&password=root");
    }

    public void printData() throws SQLException
    {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM tickets");

        if (resultSet != null && resultSet.next())
        {
            System.out.println(resultSet.getInt(1));
        }
    }
}
