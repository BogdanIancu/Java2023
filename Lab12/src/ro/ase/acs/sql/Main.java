package ro.ase.acs.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            connection.setAutoCommit(false);
            createTable(connection);
            insertValues(connection);
            selectData(connection);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void createTable(Connection connection) throws SQLException {
        String sqlDrop = "DROP TABLE IF EXISTS employees";
        String sqlCreate = "CREATE TABLE employees(id INTEGER PRIMARY KEY, " +
                "name TEXT, birthdate DATE, salary REAL)";

        Statement statement;
        statement = connection.createStatement();
        statement.executeUpdate(sqlDrop);
        statement.executeUpdate(sqlCreate);
        statement.close();
        connection.commit();
    }

    public static void insertValues(Connection connection) throws SQLException {
        String sqlInsert = "INSERT INTO employees VALUES(1, 'Ionel Popescu', 1589874134752, 2000)";

        String sqlInsertWithParams = "INSERT INTO employees(name, birthdate, salary) " +
                "VALUES(?, ?, ?)";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlInsert);
        statement.close();
        connection.commit();

        PreparedStatement preparedStatement =
                connection.prepareStatement(sqlInsertWithParams);
        preparedStatement.setString(1, "Gigel Ionescu");
        preparedStatement.setDate(2, Date.valueOf("1995-05-17"));
        preparedStatement.setDouble(3, 4000);

        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.commit();
    }

    public static void selectData(Connection connection) throws SQLException {
        String sqlSelect = "SELECT * FROM employees";

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sqlSelect);
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString(2);
            Date birthDate = rs.getDate(3);
            double salary = rs.getDouble("salary");

            System.out.println(id + " " + name + " " + birthDate + " " + salary);
        }
        rs.close();
        statement.close();
    }
}