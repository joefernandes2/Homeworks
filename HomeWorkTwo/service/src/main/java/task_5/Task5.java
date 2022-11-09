package task_5;

import DataSourse.MySQLDataSource;

import java.sql.*;

public class Task5 {
    private MySQLDataSource dataSource;
    {
        try {
            dataSource = new MySQLDataSource();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void create(String[] args) {
        if (args.length == 3) {
            try (final Connection connection = dataSource.getConnection()) {
                String receiversSQL = "INSERT INTO torop_dmitriy.receivers (name) " +
                        "SELECT * " +
                        "FROM (SELECT ? AS name) AS temp " +
                        "WHERE NOT EXISTS (SELECT name FROM torop_dmitriy.receivers WHERE name = ?) " +
                        "LIMIT 1";
                PreparedStatement pStatement = connection.prepareStatement(receiversSQL);
                pStatement.setString(1, args[1]);
                pStatement.setString(2, args[1]);
                pStatement.executeUpdate();
                String expensesSQL = "INSERT INTO torop_dmitriy.expenses (paydate, receiver, value) " +
                        "SELECT ?, num, ? " +
                        "FROM torop_dmitriy.receivers " +
                        "WHERE name=?";
                pStatement = connection.prepareStatement(expensesSQL);
                pStatement.setString(1, args[0]);
                pStatement.setFloat(2, Float.parseFloat(args[2]));
                pStatement.setString(3, args[1]);
                pStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Данные введены неверно");
        }
    }


    public void readAll() {
        try (final Connection connection = dataSource.getConnection()) {
            final Statement statement = connection.createStatement();
            String query = "SELECT e.num, paydate, name, value " +
                    "FROM torop_dmitriy.expenses as e, torop_dmitriy.receivers as r " +
                    "WHERE receiver=r.num " +
                    "ORDER BY num";
            ResultSet result = statement.executeQuery(query);
            System.out.println("Таблица расходов:");
            while (result.next()) {
                System.out.printf("%-5s %-12s %-30s %-15s\n", result.getString("num"),
                        result.getString("paydate"), result.getString("name"),
                        result.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
