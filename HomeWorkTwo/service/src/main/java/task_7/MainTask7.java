package task_7;

import task7.DAO.Dao;
import task7.DAO.DaoImpl;
import task7.DTO.Expense;
import task7.DTO.Receiver;

import java.util.List;

public class MainTask7 {
    public static void main(String[] args) {
        Dao dao = DaoImpl.getMyDao();
        int num = 1;

        Expense exp = dao.getExpense(num);
        System.out.println("Платеж номер " + num + ":");
        System.out.printf("%-5s %-12s %-4s %-15s\n", exp.getNum(),
                exp.getDate(), exp.getReceiver(), exp.getValue());
        System.out.println();

        Receiver rec = dao.getReceiver(num);
        System.out.println("Получатель платежа с номером " + num + ":");
        System.out.println(rec.getNum() + " " + rec.getName());
        System.out.println();

        Receiver receiver = new Receiver();
        receiver.setName("A-1");
        System.out.println("Добавление receiver: " + dao.addReceiver(receiver));

        Expense expense = new Expense();
        expense.setPaydate("19.02.2022");
        expense.setValue(25.7f);
        expense.setReceiver(3);
        System.out.println("Добавление expense: " + dao.addExpense(expense));
        System.out.println();

        List<Receiver> receivers = dao.getReceivers();
        System.out.println("Таблица receivers:");
        receivers.stream().map(r -> r.getNum() + " " + r.getName()).forEach(System.out::println);
        System.out.println();

        List<Expense> expenses = dao.getExpenses();
        System.out.println("Таблица expenses:");
        expenses.forEach(e -> System.out.printf("%-5s %-12s %-4s %-15s\n", e.getNum(),
                e.getDate(), e.getReceiver(), e.getValue()));
    }

}
