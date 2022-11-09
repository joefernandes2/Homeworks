package task7.DAO;

import DataSourse.MySQLDataSource;
import task7.DTO.Expense;
import task7.DTO.Receiver;

import java.sql.*;
import java.util.ArrayList;

public class DaoImpl implements Dao{

    private static DaoImpl instance;
    private MySQLDataSource dataSource;

    private DaoImpl() {
        try {
            this.dataSource = new MySQLDataSource();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DaoImpl getMyDao() {
        if (instance == null) {
            instance = new DaoImpl();
        }
        return instance;
    }

    @Override
    public Receiver getReceiver(int num) {
        Receiver receiver = new Receiver();
        try (final Connection connection = dataSource.getConnection()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * " +
                    "FROM torop_dmitriy.receivers WHERE num=" + num);
            while (resultSet.next()) {
                receiver.setNum(resultSet.getInt("num"));
                receiver.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receiver;
    }

    @Override
    public ArrayList<Receiver> getReceivers() {
        ArrayList<Receiver> receivers = new ArrayList<>();
        try {
            final Connection connection = dataSource.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM receivers " +
                    "ORDER BY num");
            while (resultSet.next()) {
                final Receiver receiver = new Receiver();
                receiver.setNum(resultSet.getInt("num"));
                receiver.setName(resultSet.getString("name"));
                receivers.add(receiver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receivers;
    }

    @Override
    public Expense getExpense(int num) {
        Expense expense = new Expense();
        try (final Connection connection = dataSource.getConnection()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * " +
                    "FROM torop_dmitriy.expenses WHERE num=" + num);
            while (resultSet.next()) {
                expense.setNum(resultSet.getInt("num"));
                expense.setPaydate(resultSet.getString("paydate"));
                expense.setReceiver(resultSet.getInt("receiver"));
                expense.setValue(resultSet.getFloat("value"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expense;
    }

    @Override
    public ArrayList<Expense> getExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();
        try {
            final Connection connection = dataSource.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM expenses");
            while (resultSet.next()) {
                final Expense expense = new Expense();
                expense.setNum(resultSet.getInt("num"));
                expense.setPaydate(resultSet.getString("paydate"));
                expense.setReceiver(resultSet.getInt("receiver"));
                expense.setValue(resultSet.getFloat("value"));
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    @Override
    public int addReceiver(Receiver receiver) {
        int result = 0;
        try (final Connection connection = dataSource.getConnection()) {
            String receiversSQL = "INSERT INTO torop_dmitriy.receivers (name) " +
                    "SELECT * " +
                    "FROM (SELECT ? AS name) AS temp " +
                    "WHERE NOT EXISTS (SELECT name FROM torop_dmitriy.receivers WHERE name = ?) " +
                    "LIMIT 1";
            PreparedStatement pStatement = connection.prepareStatement(receiversSQL);
            pStatement.setString(1, receiver.getName());
            pStatement.setString(2, receiver.getName());
            result = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int addExpense(Expense expense) {
        int result = 0;
        try (final Connection connection = dataSource.getConnection()) {
            String expensesSQL = "INSERT INTO torop_dmitriy.expenses (paydate, receiver, value) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement pStatement = connection.prepareStatement(expensesSQL);
            pStatement.setString(1, expense.getDate());
            pStatement.setInt(2, expense.getReceiver());
            pStatement.setFloat(3, expense.getValue());
            result = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
