package task_6;

import DataSourse.MySQLDataSource;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Task6 {
    private MySQLDataSource dataSource;

    {
        try {
            dataSource = new MySQLDataSource();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readNames() {
        try (final Connection connection = dataSource.getConnection()) {
            final Statement statement = connection.createStatement();
            String query = "select r.name, sum(e.value) as sum " +
                    "from torop_dmitriy.receivers as r " +
                    "join torop_dmitriy.expenses as e " +
                    "on e.receiver=r.num " +
                    "group by r.num";
            ResultSet result = statement.executeQuery(query);
            System.out.println("Список получателей платежей и сумма по каждому из них:");
            while (result.next()) {
                System.out.printf("%-30s %-15s\n", result.getString("name"),
                        result.getString("sum"));
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readSum() {
        try (final Connection connection = dataSource.getConnection()) {
            final Statement statement = connection.createStatement();
            String query = "select paydate, sum(value) as sum " +
                    "from torop_dmitriy.expenses as e, " +
                    "(select max(value) as m " +
                    "from torop_dmitriy.expenses) as t " +
                    "where e.value=t.m " +
                    "group by paydate";
            ResultSet result = statement.executeQuery(query);
            System.out.println("Сумма платежей за тот день, когда был наибольший платеж:");
            while (result.next()) {
                System.out.println(result.getString("paydate") + " " +
                        result.getFloat("sum"));
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readValue() {
        try (final Connection connection = dataSource.getConnection()) {
            final Statement statement = connection.createStatement();
            String query = "select paydate, name, value " +
                    "from torop_dmitriy.expenses as e, " +
                    "(select paydate as p, sum(value) as s " +
                    "from torop_dmitriy.expenses " +
                    "group by p " +
                    "order by s desc " +
                    "limit 1) as t, " +
                    "torop_dmitriy.receivers as r " +
                    "where e.paydate = t.p and receiver = r.num " +
                    "order by value desc " +
                    "limit 1";
            ResultSet result = statement.executeQuery(query);
            System.out.println("Наибольший платеж за тот день, когда сумма платежей была наибольшей:");
            while (result.next()) {
                System.out.println(result.getString("paydate") + " " +
                        result.getString("name") + " " +
                        result.getFloat("value"));
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

